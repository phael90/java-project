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

        });

        //    SHOW
        get("/users/:id", (req, res) -> {

            int userId = Integer.parseInt(req.params(":id"));
            User user = DBHelper.findById(User.class, userId);

            List<Advert> userActiveAdverts = DBUser.getAllActiveAdverts(user);
            List<Advert> userArchivedAdverts = DBUser.getAllArchivedAdverts(user);

            HashMap<String, Object> model = new HashMap<>();
            model.put("user", user);
            model.put("userActiveAdverts", userActiveAdverts);
            model.put("userArchivedAdverts", userArchivedAdverts);
            model.put("template", "templates/users/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //    EDIT
        get("/users/:id/edit", (req, res) -> {

            int userId = Integer.parseInt(req.params(":id"));
            User editingUser = DBHelper.findById(User.class, userId);

            HashMap<String, Object> model = new HashMap<>();
            model.put("editingUser", editingUser);
            model.put("template", "templates/users/edit.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //    UPDATE
        post("/users/:id", (req, res) -> {

            String username = req.queryParams("username");
            String firstName = req.queryParams("firstName");
            String lastName = req.queryParams("lastName");
            String eMail = req.queryParams("eMail");

            int userId = Integer.parseInt(req.params(":id"));
            User editingUser = DBHelper.findById(User.class, userId);

            editingUser.setUsername(username);
            editingUser.setFirstName(firstName);
            editingUser.setLastName(lastName);
            editingUser.seteMail(eMail);

            DBHelper.update(editingUser);

            res.redirect("/users/" + userId);
            return null;
        });

        //    DELETE
        post("/users/:id/delete", (req, res) -> {

            int userId = Integer.parseInt(req.params(":id"));
            User deletingUser = DBHelper.findById(User.class, userId);

            DBHelper.delete(deletingUser);

            res.redirect("/users");
            return null;
        });
    }




}
