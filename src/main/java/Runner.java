import db.DBHelper;
import models.Advert;
import models.Category;
import models.User;

public class Runner {

    public static void main(String[] args) {

        User user1 = new User("phael90", "Raphael", "Ugha", "raph@outlook.com");
        User user2 = new User("robbieisthebest", "Robbie", "Dumbrell", "robbie@outlook.com");

        DBHelper.save(user1);
        DBHelper.save(user2);

        Advert advert1 = new Advert("An amazing car", "This is a car that is amazing", Category.VEHICLES, 10000, user1);
        Advert advert2 = new Advert("An incredible laptop", "This is a laptop that is incredible", Category.ELECTRONICS, 1000, user1);
        Advert advert3 = new Advert("A beautiful cat", "This is a cat that is beautiful", Category.ANIMALS, 200, user2);

        DBHelper.save(advert1);
        DBHelper.save(advert2);
        DBHelper.save(advert3);

    }
    
}
