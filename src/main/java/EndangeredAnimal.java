import org.sql2o.*;

import java.util.List;

public class EndangeredAnimal extends Sighting implements DatabaseManagement{
    public static final String DATABASE_TYPE ="EndangeredAnimal";



    public EndangeredAnimal(String animalSpecies,int rangerId) {
        this.animalSpecies=animalSpecies;
        this.rangerId = rangerId;
        type=DATABASE_TYPE;
    }

    public static List<EndangeredAnimal> all() {
        String sql = "SELECT * FROM Sightings WHERE type= 'EndangeredAnimal';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
        }
    }

    public static EndangeredAnimal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            EndangeredAnimal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return animal;
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
