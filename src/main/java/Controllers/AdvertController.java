package Controllers;

import db.DBHelper;
import models.Advert;
import models.Category;
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

            Category[] allCategories = Category.values();

            HashMap<String, Object> model = new HashMap<>();
            model.put("allCategories", allCategories);
            model.put("template", "templates/adverts/new.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());
    }

}
