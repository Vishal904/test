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

@WebServlet(urlPatterns = "/editpost.do")
public class EditPost extends HttpServlet {
    private static SessionFactory factory;
    int postid;

    /**
     * method to update the query
     * @param postid
     * @param title
     * @param description
     */
    private void updatePost(int postid, String title, String description) {
        Session session = null;
        Transaction tx = null;
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            Post post = (Post) session.get(Post.class,postid);
            post.setPosttitle(title);
            post.setDescription(description);
            session.update(post);
            tx.commit();
        }catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    /**
     * method to handle request to edit post jsp and call the addpost method to addNewPost for the user
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title",request.getParameter("title"));
        request.setAttribute("description",request.getParameter("description"));
        postid = Integer.parseInt(request.getParameter("postid"));
        request.getRequestDispatcher("/WEB-INF/views/editpost.jsp").forward(request,response);
    }

    /**
     * method to move back to post listing of an user
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        updatePost(postid, title, description);
        response.sendRedirect("userposts.do");
    }
}
