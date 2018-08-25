package models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User { 

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String eMail;
    private List<Advert> adverts;
    private List<Comment> commentsMade;
    private List<Rating> ratingsMade;
    private List<Rating> ratingsReceived;

    public User() {
    }

    public User(String username, String firstName, String lastName, String eMail) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.adverts = new ArrayList<Advert>();
        this.commentsMade = new ArrayList<>();
        this.ratingsMade = new ArrayList<>();
        this.ratingsReceived = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @OneToMany(mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    @OneToMany(mappedBy = "user")
    public List<Comment> getCommentsMade() {
        return commentsMade;
    }

    public void setCommentsMade(List<Comment> commentsMade) {
        this.commentsMade = commentsMade;
    }

    @OneToMany(mappedBy = "rater")
    public List<Rating> getRatingsMade() {
        return ratingsMade;
    }

    public void setRatingsMade(List<Rating> ratingsMade) {
        this.ratingsMade = ratingsMade;
    }

    @OneToMany(mappedBy = "ratee")
    public List<Rating> getRatingsReceived() {
        return ratingsReceived;
    }

    public void setRatingsReceived(List<Rating> ratingsReceived) {
        this.ratingsReceived = ratingsReceived;
    }
}
