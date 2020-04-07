package com.vishal.PostManagement.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vishal.PostManagement.entities.Address;
import com.vishal.PostManagement.entities.Post;
import com.vishal.PostManagement.entities.User;
import com.vishal.PostManagement.repos.UserRepository;
import com.vishal.PostManagement.repos.AddressRepository;
import com.vishal.PostManagement.repos.PostRepository;


@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PostRepository postrepository;
	
	@RequestMapping("/showReg")
	public String showRegisterationPage() {
		return "signup";
	}
	
	@RequestMapping(value = "/usersignup", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user, @ModelAttribute("Address") Address address) {
		System.out.println("name "+user.getFname());
		userRepository.save(user);
		User registereduser = userRepository.findByEmail(user.getEmail());
		address.setUserid(registereduser.getId());
		addressRepository.save(address);
		return "login";
	}
	
	@RequestMapping("/showlogin")
	public String showLoginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {
		User user = userRepository.findByEmail(email);
		if(user.getPassword().equals(password)) {
			String display = "";
			int usertypeid = user.getUsertypeid();
			Long userid = user.getId();
			modelMap.addAttribute("usertypeid", usertypeid);
			modelMap.addAttribute("userid", userid);
			if(user.getUsertypeid() == 1) {
				display = "none";
				List<User> users = userRepository.findByusertypeid(2);
				modelMap.addAttribute("users",users);
				modelMap.addAttribute("display",display);
				System.out.println("ADMIN LOGGED IN");
				return "userlisting";
			}
			else {
				List<Post> userposts = postrepository.findByUserid(userid);
				modelMap.addAttribute("userposts", userposts);
				modelMap.addAttribute("display",display);
				System.out.println("NORMAL USER LOGGED IN");
				return "posts";
			}
		}else {
			modelMap.addAttribute("msg","Invalid Credentials. Please try again");
		}
		return "login";
	}
	
	@RequestMapping(value = "/profile")
	public String userprofile(@RequestParam("userid") Long userid, @RequestParam("usertypeid") int usertypeid, ModelMap modelMap) {
		User user = userRepository.getOne(userid);
		Address address = addressRepository.findByUserid(userid);
		System.out.println("USER TYPE ID IS : "+user.getUsertypeid());
		modelMap.addAttribute("usertypeid", usertypeid);
		modelMap.addAttribute("user", user);
		modelMap.addAttribute("address", address);
		return "profile";
	}
	
	@RequestMapping(value = "/userlisting")
	public String getUsers(ModelMap modelMap) {
		List<User> users = userRepository.findByusertypeid(2);
		modelMap.addAttribute("users",users);
		return "userlisting";
	}
	
}
