package models;


import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {
    private int id;
    private User rater;
    private User ratee;
    private RatingValue value;
    private String message;

    public Rating(){

    }

    public Rating(User rater, User ratee, RatingValue value, String message){
        this.rater = rater;
        this.ratee = ratee;
        this.value = value;
        this.message = message;
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

    @ManyToOne
    @JoinColumn(name = "rater_id", nullable = false)
    public User getRater() {
        return rater;
    }

    public void setRater(User rater) {
        this.rater = rater;
    }

    @ManyToOne
    @JoinColumn(name = "ratee_id", nullable = false)
    public User getRatee() {
        return ratee;
    }

    public void setRatee(User ratee) {
        this.ratee = ratee;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "value")
    public RatingValue getValue() {
        return value;
    }

    public void setValue(RatingValue value) {
        this.value = value;
    }

    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
