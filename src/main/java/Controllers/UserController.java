package Controllers;

import db.DBHelper;
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

        //    CREATE

        //    SHOW

        //    EDIT

        //    UPDATE

        //    DELETE
    }




}
