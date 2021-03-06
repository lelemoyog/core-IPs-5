import java.sql.Timestamp;
import java.util.Objects;

public abstract class Sighting {
    public String animalSpecies;
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



}
