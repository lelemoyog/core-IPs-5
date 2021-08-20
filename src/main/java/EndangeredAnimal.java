import org.sql2o.*;

import java.util.List;

public class EndangeredAnimal extends Sighting implements DatabaseManagement{
    public static final String DATABASE_TYPE ="EndangeredAnimal";
    private int id;
    public String health;
    public String age;
    public String animalSpecies;
    public  String location;


    public EndangeredAnimal(String animalSpecies,int rangerId,String location,String health,String age) {
        this.animalSpecies=animalSpecies;
        this.rangerId = rangerId;
        this.health= health;
        this.age = age;
        this.location = location;
        this.id =id;
        type=DATABASE_TYPE;
    }

    public static List<EndangeredAnimal> all() {
        String sql = "SELECT * FROM endenderedAnimals  WHERE type= 'EndangeredAnimal';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
        }
    }

    public static EndangeredAnimal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM endenderedAnimals  where id=:id";
            EndangeredAnimal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return animal;
        }
    }




    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endenderedAnimals (animalSpecies,rangerId, location, timeSpotted, type, health, age) VALUES (:animalSpecies ,:rangerId, :location, now(), :type ,:health ,:age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalSpecies", this.animalSpecies)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("location", this.location)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }



    public static void delete(int id)  {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM animals WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }


}
