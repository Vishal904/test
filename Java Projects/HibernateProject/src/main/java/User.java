import sun.security.util.Password;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "age")
    private String age;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "usertypeid")
    private int usertpeid;
    @Column(name = "gender")
    private String gender;

    public User() {}
    public User(String email, String fname, String lname, String age, String phone, String password, int usertpeid, String gender) {
        this.email = email;
        this.firstName = fname;
        this.lastName = lname;
        this.age = age;
        this.phone = phone;
        this.password = password;
        this.usertpeid = usertpeid;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String first_name ) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String last_name ) {
        this.lastName = last_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge( String age ) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String Phone) { this.phone = phone; }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public int getusertypeid() {
        return usertpeid;
    }

    public void setUsertpeid( int usertpeid ) {
        this.usertpeid = usertpeid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }
}
