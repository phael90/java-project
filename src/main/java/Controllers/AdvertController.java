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

public class AdvertController {

    public AdvertController(){
        setUpRoutes();
    }

    public static void setUpRoutes() {

        //INDEX
        get("/adverts", (req, res) ->{
            List<Advert> allAdverts = DBHelper.getAll(Advert.class);
            HashMap<String, Object> model = new HashMap<>();
            model.put("allAdverts", allAdverts);
            model.put("template", "templates/adverts/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        //NEW

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


        //SHOW

        get("/adverts/:id", (req, res) ->{
            int advertId = Integer.parseInt(req.params(":id"));
            Advert advert = DBHelper.findById(Advert.class, advertId);
            HashMap<String, Object> model = new HashMap<>();
            model.put("advert", advert);
            model.put("template", "templates/adverts/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //EDIT

        get("/adverts/:id/edit", (req, res) ->{
            int advertId = Integer.parseInt(req.params(":id"));
            Advert advert = DBHelper.findById(Advert.class, advertId);
            Category[] allCategories = Category.values();
            HashMap<String, Object> model = new HashMap<>();
            model.put("advert", advert);
            model.put("template", "templates/adverts/edit.vtl");
            model.put("allCategories", allCategories);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //UPDATE

        post("adverts/:id", (req, res) ->{


            int advertId = Integer.parseInt(req.params(":id"));
            Advert advert = DBHelper.findById(Advert.class, advertId);
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            Category category = Category.valueOf(req.queryParams("category"));
            double price = Double.parseDouble(req.queryParams("price"));

            advert.setTitle(title);
            advert.setDescription(description);
            advert.setCategory(category);
            advert.setPrice(price);
            DBHelper.update(advert);
            res.redirect("/adverts");
            return null;
        });

        //DELETE

        post("adverts/:id/delete", (req, res) ->{
            int advertId = Integer.parseInt(req.params(":id"));
            Advert advert = DBHelper.findById(Advert.class, advertId);
            DBHelper.delete(advert);
            res.redirect("/adverts");
            return null;
        });

    }

}
