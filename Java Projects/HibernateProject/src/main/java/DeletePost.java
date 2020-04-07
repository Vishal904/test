import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/deletepost.do")
public class DeletePost extends HttpServlet {
    private static SessionFactory factory;

    /**
     * method to delete a post by its id
     * @param postid
     */
    private void deletePostById(int postid) {
        Session session = null;
        Transaction tx = null;
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            Post post = (Post) session.get(Post.class, postid);
            session.delete(post);
            tx.commit();
        }catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    /**
     * method to get request to delete a post by using its post id
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int postid = Integer.parseInt(request.getParameter("postid"));
        deletePostById(postid);
        response.sendRedirect("userposts.do");
    }
}
