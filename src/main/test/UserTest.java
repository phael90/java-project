import models.Rating;
import models.RatingValue;
import models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserTest {

    User user1;
    User user2;
    User user3;
    User user4;
    Rating rating1;
    Rating rating2;
    Rating rating3;

    @Before
    public void before(){
        user1 = new User("A", "B", "C", "D");
        user2 = new User("E", "F", "G", "H");
        user3 = new User("I", "J", "K", "L");
        user4 = new User("M", "N", "O", "P");

        rating1 = new Rating(user2, user1, RatingValue.FOUR, "Hi");
        rating2 = new Rating(user3, user1, RatingValue.THREE, "Hi");
        rating3 = new Rating(user4, user1, RatingValue.ONE, "Hi");

        List<Rating> user1RatingsReceived = new ArrayList<>();
        user1RatingsReceived.add(rating1);
        user1RatingsReceived.add(rating2);
        user1RatingsReceived.add(rating3);

        user1.setRatingsReceived(user1RatingsReceived);
    }

    @Test
    public void canCalculateAverageRatingReceived(){
        double actualAverage = user1.calculateAverageRatingReceived();
        assertEquals(2.67, actualAverage, 0);
    }

}


