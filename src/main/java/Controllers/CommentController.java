package Controllers;

import db.DBHelper;
import models.Advert;
import models.Comment;
import models.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class CommentController {

    public CommentController() {
        setUpRoutes();
    }

    public static void setUpRoutes() {

//        NEW
        get("/adverts/:id/comments/new", (req, res) -> {

            List<User> allUsers = DBHelper.getAll(User.class);

            int advertId = Integer.parseInt(req.params(":id"));
            Advert commentingAdvert = DBHelper.findById(Advert.class, advertId);

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/comments/new.vtl");
            model.put("allUsers", allUsers);
            model.put("commentingAdvert", commentingAdvert);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//        CREATE
        post("/adverts/:id/comments", (req, res) -> {

            int userId = Integer.parseInt(req.queryParams("user"));
            User user = DBHelper.findById(User.class, userId);

            int advertId = Integer.parseInt(req.params(":id"));
            Advert commentingAdvert = DBHelper.findById(Advert.class, advertId);

            String message = req.queryParams("message");

            Comment newComment = new Comment(user, commentingAdvert, message);
            DBHelper.save(newComment);

            res.redirect("/adverts/" + commentingAdvert.getId());
            return null;

        });


    }
}
