package Controllers;

import db.DBHelper;
import db.DBUser;
import models.Advert;
import models.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class UserController {

    public UserController() {
        setUpRoutes();
    }

    public static void setUpRoutes(){

        //    INDEX
        get("/users", (req, res) -> {

            List<User> allUsers = DBHelper.getAll(User.class);

            HashMap<String, Object> model = new HashMap<>();
            model.put("allUsers", allUsers);
            model.put("template", "templates/users/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //    NEW
        get("/users/new", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/users/new.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //    CREATE

        post("/users", (req, res) -> {

            String username = req.queryParams("username");
            String firstName = req.queryParams("firstName");
            String lastName = req.queryParams("lastName");
            String eMail = req.queryParams("eMail");

            User newUser = new User(username, firstName, lastName, eMail);
            DBHelper.save(newUser);

            res.redirect("/users");
            return null;

        }, new VelocityTemplateEngine());

        //    SHOW

        get("/users/:id", (req, res) -> {

            int userId = Integer.parseInt(req.params(":id"));
            User user = DBHelper.findById(User.class, userId);

            List<Advert> userAdverts = DBUser.getAllAdverts(user);

            HashMap<String, Object> model = new HashMap<>();
            model.put("user", user);
            model.put("userAdverts", userAdverts);
            model.put("template", "templates/users/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //    EDIT

        //    UPDATE

        //    DELETE
    }




}
