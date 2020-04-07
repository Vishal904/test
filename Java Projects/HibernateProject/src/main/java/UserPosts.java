import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


@WebServlet(urlPatterns = "/userposts.do")
public class UserPosts extends HttpServlet {

    private static SessionFactory factory;
    // arraylist to store the all posts of an user
    List<Post> userposts;

    /**
     * method to get all the posts posted by the user
     * @param userid
     */
    private void getUserPosts(int userid) {
        Session session = null;
        Transaction tx = null;
        try{
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from Post where userid = :userid") ;
            query.setParameter("userid",userid);
            userposts = ((org.hibernate.query.Query) query).list();
            for(Iterator iterator = userposts.iterator(); iterator.hasNext();) {
                Post post = (Post) iterator.next();
                System.out.println("Date: "+post.getDate());
            }
        }catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userid = 0;

        //if normal user logged in then receiving userid or an user as a session attribut from LoginService Servlet
        if( request.getSession().getAttribute("userid")!= null) {
            System.out.println("xyz "+request.getSession().getAttribute("userid"));
            userid = (Integer) request.getSession().getAttribute("userid");
        }
        // if admin/super user logged in then receiving userid or an user as a parameter from GetUsers Servlet
        else {
            System.out.println("abc "+request.getParameter("userid"));
            userid = Integer.parseInt(request.getParameter("userid"));
            System.out.println("xyz");
            request.setAttribute("userid",userid);
        }
        getUserPosts(userid);
        request.setAttribute("userposts", userposts);
        request.getRequestDispatcher("/WEB-INF/views/posts.jsp").forward(request,response);
    }
}
