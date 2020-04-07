/*
import javafx.geometry.Pos;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManagePost {
    private static SessionFactory factory;
    public static void main(String[] args) {
        try {
            factory = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch(Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManagePost MP = new ManagePost();
        //MP.deletePost(56);
        //MP.addPost(2,"myNewPost","abdbkkjjdbasdada");
        MP.getUserPosts(2);
       // MP.editPost(18,"newPost123","bvxtzr");
    }


    */
/**
     * method to get POSTS of an USER
     * @param userid
     * @return
     *//*

    public List<Post> getUserPosts(int userid) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Post> userposts = new ArrayList<Post>();
        try {
            tx = session.beginTransaction();
           // userposts = session.createQuery("from Post where userid = "+userid).list();
            Query query = session.createQuery("from Post where userid = :userid");
            query.setParameter("userid",userid);
            userposts = ((org.hibernate.query.Query) query).list();
            for(Iterator iterator = userposts.iterator(); iterator.hasNext();) {
                Post post = (Post) iterator.next();
                System.out.print("  PostId: " + post.getId());
                System.out.print("  Title: " + post.getPosttitle());
                System.out.println("  Description: " + post.getDescription());
            }
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userposts;
    }

    */
/**
     * method to add new Post
     * @param title
     * @param description
     *//*

    public Integer addPost(int userid,String title, String description) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer postid = null;
        try {
            tx = session.beginTransaction();
            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
            Post post = new Post(userid,title, description, date);
            postid = (Integer) session.save(post);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return postid;
    }

    */
/**
     * method to delete a post on the basis of PostId
     * @param postid
     *//*

    public void deletePost(int postid) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Post post = (Post) session.get(Post.class, postid);
            session.delete(post);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void editPost(int postid, String title, String description) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Post post = (Post) session.get(Post.class, postid);
            post.setPosttitle(title);
            post.setDescription(description);
            session.update(post);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
*/
