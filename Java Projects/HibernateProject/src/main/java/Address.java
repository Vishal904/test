import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "userid")
    private int userid;
    @Column(name = "address")
    private String address;
    @Column(name = "district")
    private String district;
    @Column(name = "city")
    private String city;

    public Address() {}
    public Address(int userid, String address, String district, String city) {
        this.userid = userid;
        this.address = address;
        this.district = district;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict( String district ) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

}
