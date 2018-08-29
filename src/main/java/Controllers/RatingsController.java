package Controllers;

import db.DBHelper;
import db.DBRating;
import models.Rating;
import models.RatingValue;
import models.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class RatingsController {

    public RatingsController() {
        setUpRoutes();
    }

    public static void setUpRoutes() {

//        INDEX BY USER
        get("/users/:id/ratings", (req, res) -> {

            int userId = Integer.parseInt(req.params(":id"));
            User user = DBHelper.findById(User.class, userId);

            List<Rating> ratingsReceived = DBRating.getAllRatingsReceivedByUser(user);

            HashMap<String, Object> model = new HashMap<>();
            model.put("user", user);
            model.put("ratingsReceived", ratingsReceived);
            model.put("template", "templates/ratings/indexByUser.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        NEW

        get("/users/:id/ratings/new", (req, res) -> {
            List<User> allUsers = DBHelper.getAll(User.class);
            int rateeId = Integer.parseInt(req.params(":id"));
            User ratee = DBHelper.findById(User.class, rateeId);

            RatingValue[] allRatingValues = RatingValue.values();

            HashMap<String, Object> model = new HashMap<>();
            model.put("allUsers", allUsers);
            model.put("ratee", ratee);
            model.put("allRatingValues", allRatingValues);
            model.put("template", "templates/ratings/new.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        //CREATE

        post("/users/:id/ratings", (req, res) -> {
            int raterId = Integer.parseInt(req.queryParams("rater"));
            User rater = DBHelper.findById(User.class, raterId);

            int rateeId = Integer.parseInt(req.params(":id"));
            User ratee = DBHelper.findById(User.class, rateeId);

            RatingValue ratingValue = RatingValue.valueOf(req.queryParams("ratingValue"));

            String message = req.queryParams("message");

            Rating newRating = new Rating(rater, ratee, ratingValue, message);
            DBHelper.save(newRating);

            res.redirect("/users/" + ratee.getId());
            return null;
        });
    }

}
