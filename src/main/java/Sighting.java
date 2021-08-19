import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public abstract class Sighting {
    public String animalSpecies;
    public static int id;
    public int rangerId;
    public String location ;
    public Timestamp timeSpotted;
    public String type;


    public String getAnimalSpecies() {
        return animalSpecies;
    }

    public void setAnimalSpecies(String animalSpecies) {
        this.animalSpecies = animalSpecies;
    }

    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRangerId() {
        return rangerId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getTimeSpotted() {
        return timeSpotted;
    }

    public void setTimeSpotted(Timestamp timeSpotted) {
        this.timeSpotted = timeSpotted;
    }


    @Override
    public boolean equals(Object o){
        if (!(o instanceof Sighting)) {
            return false;
        } else {
            Sighting newSighting = (Sighting) o;
            return this.getAnimalSpecies().equals(newSighting.getAnimalSpecies()) &&
                    this.getRangerId() == newSighting.getRangerId();
        }
    }



    @Override
    public int hashCode() {
        return Objects.hash(getAnimalSpecies(), getRangerId());
    }

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
}
