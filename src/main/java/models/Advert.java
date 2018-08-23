package models;

public class Advert {
    private int id;
    private String title;
    private String description;
    private Category category;
    private double price;

    public Advert(String title, String description, Category category, double price){
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public Advert(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
