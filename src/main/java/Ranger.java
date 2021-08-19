import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Ranger implements DatabaseManagement{
    private int id;
    private  String name;

    public Ranger(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Ranger> all() {
        String sql = "SELECT * FROM rangers";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Ranger.class);
        }
    }

    public static Ranger find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM rangers where id=:id";
            Ranger person = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Ranger.class);
            return person;
        }

    }

    @Override
    public boolean equals(Object otherRanger) {
        if (!(otherRanger instanceof Ranger)) {
            return false;
        } else {
            Ranger newRanger = (Ranger) otherRanger;
            return this.getName().equals(newRanger.getName());

        }
    }



    @Override
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO rangers (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM rangers WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }
}
