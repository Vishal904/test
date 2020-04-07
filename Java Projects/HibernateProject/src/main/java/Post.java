import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column(name = "userid")
    private int userid;
    @Column(name = "posttitle")
    private String posttitle;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private Date date;

    public Post() {}

    public Post(int userid, String posttitle, String description, Date date) {
        this.userid = userid;
        this.posttitle = posttitle;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosttitle() {
        return posttitle;
    }

    public void setPosttitle(String posttitle) {
        this.posttitle = posttitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() { return date;}

    public void setDate(Date date) { this.date = date;}

}
