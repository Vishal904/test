import org.hibernate.HibernateException;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/signup.do")
public class Signup extends HttpServlet{
    private static SessionFactory factory;

    String firstname = "mohan";
    String lastname = "mohan";
    String age = "30";
    String email = "mohan@gmail.com";
    String phone = "mohan";
    String password = "mohan123";
    String gender = "M";
    String address = "mohan";
    String district = "mohan";
    String city = "mohan";

   /* public static void main(String[] args) {
        try {
            factory = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch(Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Signup SU = new Signup();
        SU.checkIfEmailExists("mohan@gmail.com");
    }*/

    /**
     * Method to check if email exists or not in the database
     *
     * @param email
     * @return
     */
    boolean checkIfEmailExists(String email) {
        System.out.println("IN checkEmail Method");
        Session session = null;
        Transaction tx = null;
        List<User> users = new ArrayList<User>();
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = factory.openSession();
            Query query = session.createQuery("from User where email = :email");
            query.setParameter("email",email);
            if (((org.hibernate.query.Query) query).list().size() > 0) {
                System.out.println("Exists");
                return true;
            }
        } catch (HibernateException e) {
            if(tx !=null ) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        registerUser();
        return false;
    }

    /**
     * method to insert new user in the user database
     * @return
     */
    public Integer registerUser() {
        Session session = null;
        Transaction tx = null;
        Integer userid = null;
        try {
            //factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            User user = new User(email,firstname,lastname,age,phone,password,2,gender);
            userid = (Integer) session.save(user);
            System.out.println("Userid "+userid);
            Address address = new Address(userid, this.address, district, city);
            Integer addressid = (Integer) session.save(address);
            System.out.println("Addressid "+addressid);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userid;
    }

    /**
     * Method to handle get type request
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
    }

    /**
     * post method to get newly registered user's details
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // get newly registered user
        firstname = request.getParameter("firstname");
        lastname = request.getParameter("lastname");
        age = request.getParameter("age");
        email = request.getParameter("email");
        phone = request.getParameter("phone");
        password = request.getParameter("password");
        gender = request.getParameter("gender");
        address = request.getParameter("address");
        district = request.getParameter("district");
        city = request.getParameter("city");

        // if email does not exist in the database
        if (checkIfEmailExists(email) == false) {
            try {
                System.out.println("Data Stored");
                request.getRequestDispatcher("loginservice.do").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // if email exists in the database
        else {
            System.out.print("Email Already Exists");
            request.setAttribute("firstname", firstname);
            request.setAttribute("lastname", lastname);
            request.setAttribute("age", age);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("password", password);
            request.setAttribute("gender", gender);
            request.setAttribute("address", address);
            request.setAttribute("district", district);
            request.setAttribute("city", city);
            request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
        }
    }
}
