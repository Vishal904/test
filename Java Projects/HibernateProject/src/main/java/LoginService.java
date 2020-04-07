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
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = "/loginservice.do")
public class LoginService extends HttpServlet {
    private static SessionFactory factory;
    int userid;
    String useremail;
    String firstname;
    String lastname;
    String age;
    String phone;
    String userpassword;
    int usertypeid;
    String gender;

    /**
     * method to fetch the data of logged in user from database and check if user is valid or not
     *
     * @param email
     */
    private boolean isValidUser(String email, String password) {
        Session session = null;
        Transaction tx = null;
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = factory.openSession();
            Query query = session.createQuery("from User where email = :email and password = :password");
            query.setParameter("email",email);
            query.setParameter("password",password);
            List users = ((org.hibernate.query.Query) query).list();
            if (users.size() > 0) {
                for(Iterator iterator = users.iterator(); iterator.hasNext();){
                    User user = (User) iterator.next();
                    userid = user.getId();
                    useremail = user.getEmail();
                    firstname = user.getFirstName();
                    lastname = user.getLastName();
                    age = user.getAge();
                    phone = user.getPhone();
                    userpassword = user.getPassword();
                    usertypeid = user.getusertypeid();
                    gender = user.getGender();
                    System.out.println("Phone number is : " + phone);
                    System.out.println("Length : " + userpassword.length() + " " + password.length());
                    return true;
                }
            }else {
                System.out.println("User Exists");
                return false;
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("User Email is POST : " + request.getParameter("email") + " " + request.getParameter("password"));
        String entered_password = request.getParameter("password");
        boolean isvalid = isValidUser(request.getParameter("email"), entered_password);
        if (isvalid && entered_password.equals(userpassword)) {
            User loggedinuser = new User(useremail, firstname, lastname, age, phone, userpassword, usertypeid,gender);
            request.getSession().setAttribute("email",useremail);
            request.getSession().setAttribute("loggedinuser", loggedinuser);
            request.getSession().setAttribute("usertypeid", usertypeid);
            System.out.println("User id in LoginService is : "+userid);

            System.out.println("Loggedin user is valid");

            // if loggedin user is of admin type
            if (usertypeid == 1) {
                System.out.println("Loggedin user is admin");
                response.sendRedirect("getusers.do");
            }

            // if loggedin user is not admin type i.e. user is normal user
            else {
                request.getSession().setAttribute("userid", userid);
                System.out.println("Loggedin user is normal user");
                response.sendRedirect("userposts.do");
            }
        } else {
            System.out.println("Loggedin user is invalid");
            String errormessage = "Invalid Credentials";
            request.setAttribute("errormessage", errormessage);
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
