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


@WebServlet(urlPatterns = "/addPost.do")
public class AddPost extends HttpServlet {
    private static SessionFactory factory;
    /**
     * method to add new post in dbms
     * @param userid
     */
    public Integer addNewPost(int userid, String title, String description) {
        Session session = null;
        Transaction tx = null;
        Integer postid = null;
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            long miliseconds = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(miliseconds);
            Post post = new Post(userid, title, description,date);
            postid = (Integer) session.save(post);
            tx.commit();
        }catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return postid;
    }


    /**
     * method to navigate back to post listing jsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/addPost.jsp").forward(request,response);
    }


    /**
     * method to handle post request and call the addpost method to addNewPost for the user
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userid = (Integer) request.getSession().getAttribute("userid");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        addNewPost(userid, title, description);
        response.sendRedirect("userposts.do");
    }
}
