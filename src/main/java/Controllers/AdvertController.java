package Controllers;

import db.DBHelper;
import models.Advert;
import models.Category;
import models.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.redirect;

public class AdvertController {

    public AdvertController(){
        setUpRoutes();
    }

    public static void setUpRoutes() {

        //TODO: INDEX
        get("/adverts", (req, res) ->{
            List<Advert> allAdverts = DBHelper.getAll(Advert.class);
            HashMap<String, Object> model = new HashMap<>();
            model.put("allAdverts", allAdverts);
            model.put("template", "templates/adverts/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        //TODO: NEW

        get("/adverts/new", (req, res) -> {
            List<User> allUsers = DBHelper.getAll(User.class);
            Category[] allCategories = Category.values();

            HashMap<String, Object> model = new HashMap<>();
            model.put("allCategories", allCategories);
            model.put("template", "templates/adverts/new.vtl");
            model.put("allUsers", allUsers);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //CREATE

        post("/adverts", (req, res) ->{
            int user_id = Integer.parseInt(req.queryParams("user"));

            User user = DBHelper.findById(User.class, user_id);
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            Category category = Category.valueOf(req.queryParams("category"));
            double price = Double.parseDouble(req.queryParams("price"));

            Advert newAdvert = new Advert(title, description, category, price, user);
            DBHelper.save(newAdvert);
            res.redirect("/adverts");
            return null;
        });

    }

}
