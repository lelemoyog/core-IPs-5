import org.sql2o.*;

import java.util.List;

public class Animal {
 private int id;
 private String name;

    public Animal(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM animals";
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

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.getName().equals(newAnimal.getName()) &&
                    this.getId() == newAnimal.getId();
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (id, name) VALUES (:id, :name)";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .addParameter("name", this.name)
                    .executeUpdate();
        }
    }
}
