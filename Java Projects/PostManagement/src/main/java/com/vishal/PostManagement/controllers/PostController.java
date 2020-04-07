package com.vishal.PostManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vishal.PostManagement.entities.Post;
import com.vishal.PostManagement.repos.PostRepository;

@Controller
public class PostController {

	@Autowired
	private PostRepository postrepository;
	
	@RequestMapping(value = "/userposts")
	public String userposts(@RequestParam("userid") Long userid, @RequestParam("usertypeid") int usertypeid ,ModelMap modelMap) {
		List<Post> userposts = postrepository.findByUserid(userid);
		modelMap.addAttribute("usertypeid", usertypeid);
		if(usertypeid == 1) {
			modelMap.addAttribute("display", "none");
		} else {
			modelMap.addAttribute("display", "");
		}
		modelMap.addAttribute("userid", userid);
		modelMap.addAttribute("userposts", userposts);
		return "posts";
	}
	
	
	@RequestMapping(value = "/editpost")
	public String editPost(@RequestParam("postid") Long postid, @RequestParam("title") String title, 
			@RequestParam("description") String description, ModelMap modelMap) {
		Post post = postrepository.getOne(postid);
		modelMap.addAttribute("postid",postid);
		modelMap.addAttribute("userid",post.getUserid());
		modelMap.addAttribute("title",post.getPosttitle());
		modelMap.addAttribute("description",post.getDescription());
		return "editpost";
	}
	
	@RequestMapping(value = "/saveposts", method = RequestMethod.POST)
	public String saveposts(@RequestParam("userid") Long userid, @RequestParam("postid") Long postid, @RequestParam("posttitle") String title, 
			@RequestParam("description") String description, ModelMap modelMap) {
		int row = postrepository.updatePost(postid, title, description);
		List<Post> userposts = postrepository.findByUserid(userid);
		modelMap.addAttribute("usertypeid", 2);
		modelMap.addAttribute("userid", userid);
		modelMap.addAttribute("userposts", userposts);
		modelMap.addAttribute("display", "");
		return "posts";
	}
	
	@RequestMapping(value = "/deletepost") 
	public String deletePost(@RequestParam("postid") Long postid, @RequestParam("userid") Long userid, ModelMap modelMap) {
		
		postrepository.deleteById(postid);
		List<Post> userposts = postrepository.findByUserid(userid);
		modelMap.addAttribute("usertypeid", 2);
		modelMap.addAttribute("display", "");
		modelMap.addAttribute("userid", userid);
		modelMap.addAttribute("userposts", userposts);
		return "posts";
	}
	
	@RequestMapping(value = "/newPost")
	public String createNewPost(@RequestParam("userid") Long userid, ModelMap modelMap) {
		
		modelMap.addAttribute("userid",userid);
		return "addPost";
	}
	
	
	@RequestMapping(value = "/addPost", method = RequestMethod.POST) 
	public String addpost(@RequestParam("userid") Long userid, @RequestParam("posttitle") String posttitle, @RequestParam("description") String description,
			ModelMap modelMap) {
		
		long miliseconds = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(miliseconds);
        
		Post post = new Post();
		post.setUserid(userid);
		post.setPosttitle(posttitle);
		post.setDescription(description);
		post.setDate(date);
		postrepository.save(post);
		
		List<Post> userposts = postrepository.findByUserid(userid);
		modelMap.addAttribute("usertypeid", 2);
		modelMap.addAttribute("userid", userid);
		modelMap.addAttribute("userposts", userposts);
		modelMap.addAttribute("display", "");
		return "posts";
	}
	
}
