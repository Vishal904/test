/*
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class GetUsers132 {

    private static SessionFactory factory;
    public static void main(String[] args) {

        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        GetUsers132 GU = new GetUsers132();
       */
/* GU.insertUser("rishi@gmail.com","rishi","kumar","26","9865321040",
                "12345678",2,"M");*//*

        GU.listUsers();
    }


    */
/**
     * method to fetch all the users from user table of test database
     *//*

    public void listUsers() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List users = session.createQuery("from User").list();
            for (Iterator iterator = users.iterator(); iterator.hasNext();){
                User user = (User) iterator.next();
                System.out.print("First Name: " + user.getFirstName());
                System.out.print("  Last Name: " + user.getLastName());
                System.out.println("  age: " + user.getAge());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    */
/**
     * method to insert new user in the user database
     * @param email
     * @param firstname
     * @param lastname
     * @param age
     * @param phone
     * @param password
     * @param usertypeid
     * @param gender
     * @return
     *//*

    public Integer insertUser(String email, String firstname, String lastname, String age, String phone, String password,
                           int usertypeid, String gender) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer userid = null;
        try {
            tx = session.beginTransaction();
            User user = new User(email,firstname,lastname,age,phone,password,usertypeid,gender);
            userid = (Integer) session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userid;
    }
}
*/
