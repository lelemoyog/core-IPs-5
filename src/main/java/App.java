import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> Animals = Animal.all();
            List<EndangeredAnimal> EndangeredAnimals = EndangeredAnimal.all();
            model.put("Animals", Animals);
            model.put("EndangeredAnimals",EndangeredAnimals);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        post("/endangeredAnimals/new", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("animal");
            String location = request.queryParams("location");
            String ranger = request.queryParams("ranger");
            int id = Integer.parseInt(request.queryParams("rangerId"));
            String health = request.queryParams("Health");
            String age = request.queryParams("age");
            EndangeredAnimal newAnimal = new EndangeredAnimal(name,id,location,health,age);
            System.out.println(name);
            System.out.println(location);
            System.out.println(ranger);
            System.out.println(health);
            System.out.println(age);
            newAnimal.save();
            model.put("animals", newAnimal);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings/new", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("animal");
            String location = request.queryParams("location");
            String ranger = request.queryParams("ranger");
            int id = Integer.parseInt(request.queryParams("rangerId"));
            Animal  newAnimal= new Animal(name,location,id);
            System.out.println(name);
            System.out.println(location);
            System.out.println(ranger);
            newAnimal.save();
            model.put("EndangeredAnimals", newAnimal);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        get("/sightings/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/endangeredAnimals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "EndengeredAnimalForm.hbs");
        }, new HandlebarsTemplateEngine());

//        get("/animals-delete/:id",(req,res)-> {
//            int id = Integer.parseInt( req.params(":id"));
//            EndangeredAnimal.delete(id);
//            res.redirect("/");
//            return null;
//        });

    }
}
