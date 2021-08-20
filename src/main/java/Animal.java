import org.sql2o.*;

import java.util.List;

public class Animal extends Sighting implements DatabaseManagement {

    public static int id;
    public static final String DATABASE_TYPE ="Animal";



    public Animal(String animalSpecies,String location, int rangerId) {
        this.animalSpecies=animalSpecies;
        this.rangerId = rangerId;
        this.location = location;
        this.id = id;
        type=DATABASE_TYPE;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM animals WHERE type= 'Animal';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }


    public static Animal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (animalSpecies, rangerId, location, timeSpotted, type) VALUES (:animalSpecies, :rangerId, :location, now(), :type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalSpecies", this.animalSpecies)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("location", this.location)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }


    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM animals WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }

}
