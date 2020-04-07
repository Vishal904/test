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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/getusers.do")
public class Getusers extends HttpServlet {

    private static SessionFactory factory;
    private static List<User> users;

    public void getUser() {
        users =  new ArrayList<User>();
        Session session = null;
        Transaction tx = null;
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            users = session.createQuery("from User where id != 1").list();
            tx.commit();
        }catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    /**
     * Method to get all the registered users
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        getUser();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/views/userlisting.jsp").forward(request, response);
    }

}
