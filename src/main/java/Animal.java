import org.sql2o.*;

import java.util.List;

public class Animal extends Sighting implements DatabaseManagement {


    public static final String DATABASE_TYPE ="Animal";



    public Animal(String animalSpecies,int rangerId) {
        this.animalSpecies=animalSpecies;
        this.rangerId = rangerId;
        type=DATABASE_TYPE;
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM Sightings WHERE type= 'Animal';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

    public static Animal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }
    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings ( rangerId, location, timeSpotted, type) VALUES (:rangerId, :location, now(), :type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("location", this.location)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM sightings WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }

}
