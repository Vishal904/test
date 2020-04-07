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
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = "/profile.do")
public class Profile extends HttpServlet {

    private static SessionFactory factory;
    User user = new User();
    String address;
    String district;
    String city;

    /**
     * method to get user address
     * @param userid
     */
    private void getUserAddress(int userid) {
        Session session = null;
        Transaction tx = null;
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = factory.openSession();
            Query query1 = session.createQuery("from Address where userid = :userid");
            query1.setParameter("userid",userid);
            List<Address> addresses = new ArrayList<Address>();
            addresses = ((org.hibernate.query.Query) query1).list();
            if(((org.hibernate.query.Query) query1).list().size()>0) {
                for (Iterator iterator = addresses.iterator(); iterator.hasNext(); ) {
                    Address ad = (Address) iterator.next();
                    address = ad.getAddress();
                    district = ad.getDistrict();
                    city = ad.getCity();
                }
            }
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            // Closing Connection
            session.close();
        }

    }

    /**
     * method to get user's details
     * @param userid
     */
    private void getUserDetails(int userid) {
        Session session =null;
        Transaction tx = null;
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = factory.openSession();
            Query query = session.createQuery("from User where id = :userid");
            query.setParameter("userid",userid);
            List users = ((org.hibernate.query.Query) query).list();
            if (users.size() > 0) {
                for (Iterator iterator = users.iterator(); iterator.hasNext(); ) {
                    user = (User) iterator.next();
                    int id = user.getId();
                    String useremail = user.getEmail();
                    String firstname = user.getFirstName();
                    String lastname = user.getLastName();
                    String age = user.getAge();
                    String phone = user.getPhone();
                    String userpassword = user.getPassword();
                    int usertypeid = user.getusertypeid();
                    String gender = user.getGender();
                    //Creating a user and storing it in users arraylist
                    System.out.println("38 Profile Getusers : " + id);
                    user = new User(useremail, firstname, lastname, age, phone, userpassword, 2, gender);
                    System.out.println("40 profile Getusers : " + user.getId());
                }
            }
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
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

        int userid = 0;
        //if normal user logged in then receiving userid or an user as a session attribut from LoginService Servlet
        if( request.getSession().getAttribute("userid")!= null) {
            System.out.println("1");
            userid = (Integer) request.getSession().getAttribute("userid");
        }
        // if admin/super user logged in then receiving userid or an user as a parameter from GetUsers Servlet
        else {
            System.out.print("2");
            userid = Integer.parseInt(request.getParameter("userid"));
        }

        System.out.println("3");
        getUserDetails(userid);
        getUserAddress(userid);
        System.out.println("User name : "+user.getFirstName());
        request.setAttribute("user",user);
        request.setAttribute("address",address);
        request.setAttribute("district",district);
        request.setAttribute("city",city);
        System.out.print("4");
        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }
}
