package pl.coderslab.dao;

import pl.coderslab.models.Group;
import pl.coderslab.service.DbService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {
    public static Group makeGroup(String[] strs) {
        return new Group()
                .setId(Integer.parseInt(strs[0]))
                .setName(strs[1]);
    }

    private static Group loadOne(String query, String... parameters) {
        try {
            List<String[]> list = DbService.getData(query, parameters);
            if (!list.isEmpty()) {
                return makeGroup(list.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Group> loadMany(String query, String... parameters) {
        ArrayList<Group> groups = new ArrayList<>();
        try {
            List<String[]> list = DbService.getData(query, parameters);
            list.forEach(item -> groups.add(makeGroup(item)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    // crud
    public static ArrayList<Group> loadAll() {
        //language=MySQL
        String query = "SELECT id, name FROM groups";
        return loadMany(query);
    }

    public static Group loadById(int id) {
        //language=MySQL
        String query = "SELECT id, name FROM groups WHERE id = ?";
        return loadOne(query, String.valueOf(id));
    }

    public static void save(Group group) {
        List<String> params = new ArrayList<>();
        params.add(group.getName());

        if (group.getId() == 0) {
            //language=MySQL
            String query = "INSERT INTO groups(name) VALUES(?)";

            try {
                Integer id = DbService.insertIntoDatabase(query, params);
                if (id != null) {
                    group.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            //language=MySQL
            String query = "UPDATE groups SET name = ? WHERE id = ?";
            params.add(String.valueOf(group.getId()));

            try {
                DbService.executeUpdate(query, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void delete(int id) {
        if (id != 0) {
            //language=MySQL
            String query = "DELETE FROM groups WHERE id = ?";

            try {
                DbService.executeUpdate(query, String.valueOf(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
