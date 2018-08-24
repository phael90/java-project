package models;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "adverts")
public class Advert {
    private int id;
    private String title;
    private String description;
    private Category category;
    private double price;
    private User user;
    private Boolean archived;
    private byte[] image;

    public Advert(String title, String description, Category category, double price, User user){
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.user = user;
        this.archived = false;
        this.image = null;
    }

    public Advert(){}

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name  = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name  = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "archived")
    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    @Lob
    @Column(name = "image")
    @Type(type="org.hibernate.type.BinaryType")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void archive(){
        this.archived = true;
    }

}
