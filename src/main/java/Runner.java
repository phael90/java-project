import db.DBAdvert;
import db.DBHelper;
import models.Advert;
import models.Category;
import models.Comment;
import models.User;

import java.util.List;

public class Runner {

    public static void main(String[] args) {

        DBHelper.deleteAll(User.class);
        DBHelper.deleteAll(Advert.class);

        User user1 = new User("phael90", "Raphael", "Ugha", "raph@outlook.com");
        User user2 = new User("robbieisthebest", "Robbie", "Dumbrell", "robbie@outlook.com");

        DBHelper.save(user1);
        DBHelper.save(user2);

        Advert advert1 = new Advert("An amazing car", "This is a car that is amazing", Category.VEHICLES, 10000, user1);
        Advert advert4 = new Advert("A bad car", "This is a car that is amazing", Category.VEHICLES, 10000, user1);
        Advert advert2 = new Advert("An incredible laptop", "This is a laptop that is incredible", Category.ELECTRONICS, 1000, user1);
        Advert advert3 = new Advert("A beautiful cat", "This is a cat that is beautiful", Category.ANIMALS, 200, user2);

        DBHelper.save(advert1);
        DBHelper.save(advert2);
        DBHelper.save(advert3);
        DBHelper.save(advert4);

//        List<Advert> user1Adverts = DBUser.getAllAdverts(user1);
//
//        User foundUser = DBHelper.findById(User.class, 1);
//        foundUser.setUsername("digory");
//        DBHelper.update(foundUser);

//        DBHelper.delete(foundUser);

//        Advert foundAdvert = DBHelper.findById(Advert.class, 1);
//        DBHelper.delete(foundAdvert);

//        List<User> allUsers = DBHelper.getAll(User.class);
//        List<Advert> allAdverts = DBHelper.getAll(Advert.class);

//        List<Advert> advertsVehicleByCategory = DBAdvert.getAllAdvertsByCategory(Category.VEHICLES);

//        Advert foundAdvert = DBHelper.findById(Advert.class, 9);
//        DBAdvert.archiveAdvert(foundAdvert);

//        List<Advert> allArchivedAdverts = DBAdvert.getAllArchivedAdverts();
//        List<Advert> allActiveAdverts = DBAdvert.getAllActiveAdverts();
//
//        List<Advert> searchedAdverts = DBAdvert.getAllSearchedActiveAdverts("car");

        Comment comment1 = new Comment(user1, advert1, "Hello, I really like the look of this!");
        Comment comment2 = new Comment(user1, advert2, "Hello, I really like the look of this!");
        Comment comment3 = new Comment(user1, advert3, "Hello, I really like the look of this!");
        Comment comment4 = new Comment(user2, advert1, "Hello, I really like the look of this!");
        Comment comment5 = new Comment(user2, advert4, "Hello, I really like the look of this!");

        DBHelper.save(comment1);
        DBHelper.save(comment2);
        DBHelper.save(comment3);
        DBHelper.save(comment4);
        DBHelper.save(comment5);

    }
    
}
