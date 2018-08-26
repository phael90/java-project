package Controllers;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import static spark.Spark.get;

public class MainController {

    public static void main(String[] args) {

        AdvertController advertController = new AdvertController();
        UserController userController = new UserController();
        CommentController commentController = new CommentController();
        RatingsController ratingsController = new RatingsController();

//        HOME PAGE
        get("/", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

    }

}
