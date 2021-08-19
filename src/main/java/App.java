import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
          staticFileLocation("/public");

        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "issah", "issah9960");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> Animals = Animal.all();
            model.put("animals", Animals);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        post("/animals/new", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("animal");
            int id = Animal.getId();
            Animal newAnimal = new Animal(name,id);
            model.put("animals", newAnimal);
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings/new", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("animal");
            String location = request.queryParams("location");
            String ranger = request.queryParams("ranger");
            int id = Animal.getId();
            Animal  newSighting= new Animal(name,id);
            model.put("animals", newSighting);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        get("/animals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "AnimalForm.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
