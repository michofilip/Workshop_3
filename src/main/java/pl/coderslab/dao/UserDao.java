package pl.coderslab.dao;

import pl.coderslab.models.User;
import pl.coderslab.service.DbService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static User makeUser(String[] strs) {
        return new User()
                .setId(Integer.parseInt(strs[0]))
                .setUsername(strs[1])
                .setEmail(strs[2])
                .setPassword(strs[3])
                .setGroup(strs[4] != null ? GroupDao.loadById(Integer.parseInt(strs[4])) : null);
    }

    private static User loadOne(String query, String... parameters) {
        try {
            List<String[]> list = DbService.getData(query, parameters);
            if (!list.isEmpty()) {
                return makeUser(list.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<User> loadMany(String query, String... parameters) {
        ArrayList<User> users = new ArrayList<>();
        try {
            List<String[]> list = DbService.getData(query, parameters);
            list.forEach(item -> users.add(makeUser(item)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // crud
    public static ArrayList<User> loadAll() {
        //language=MySQL
        String query = "SELECT id, username, email, password, group_id FROM users";
        return loadMany(query);
    }

    public static ArrayList<User> loadAllByGroupId(int groupId) {
        //language=MySQL
        String query = "SELECT id, username, email, password, group_id\n" +
                "FROM users\n" +
                "WHERE group_id = ?;";
        return loadMany(query, String.valueOf(groupId));
    }

    public static User loadById(int id) {
        //language=MySQL
        String query = "SELECT id, username, email, password, group_id FROM users WHERE id = ?";
        return loadOne(query, String.valueOf(id));
    }

    public static void save(User user) {
        List<String> params = new ArrayList<>();
        params.add(user.getUsername());
        params.add(user.getEmail());
        params.add(user.getPassword());
        params.add(user.getGroup() != null ? String.valueOf(user.getGroup().getId()) : null);

        if (user.getId() == 0) {
            //language=MySQL
            String query = "INSERT INTO users(username, email, password, group_id) VALUES(?, ?, ?, ?)";

            try {
                Integer id = DbService.insertIntoDatabase(query, params);
                if (id != null) {
                    user.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            //language=MySQL
            String query = "UPDATE users SET username = ?, email = ?, password = ?, group_id = ? WHERE id = ?";
            params.add(String.valueOf(user.getId()));

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
            String query = "DELETE FROM users WHERE id = ?";

            try {
                DbService.executeUpdate(query, String.valueOf(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
