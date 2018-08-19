package pl.coderslab.dao;

import pl.coderslab.models.Solution;
import pl.coderslab.service.DbService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {
    public static Solution makeSolution(String[] strs) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return new Solution()
                .setId(Integer.parseInt(strs[0]))
                .setCreated(LocalDateTime.parse(strs[1], formatter))
                .setUpdated(strs[2] != null ? LocalDateTime.parse(strs[2], formatter) : null)
                .setDescription(strs[3])
                .setExercise(ExerciseDao.loadById(Integer.parseInt(strs[4])))
                .setUser(UserDao.loadById(Integer.parseInt(strs[5])));
    }

    private static Solution loadOne(String query, String... parameters) {
        try {
            List<String[]> list = DbService.getData(query, parameters);
            if (!list.isEmpty()) {
                return makeSolution(list.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Solution> loadMany(String query, String... parameters) {
        ArrayList<Solution> solutions = new ArrayList<>();
        try {
            List<String[]> list = DbService.getData(query, parameters);
            list.forEach(item -> solutions.add(makeSolution(item)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return solutions;
    }

    // crud
    public static ArrayList<Solution> loadAll() {
        //language=MySQL
        String query = "SELECT id, created, updated, description, exercise_id, user_id FROM solutions";
        return loadMany(query);
    }

//    public static ArrayList<Solution> loadAll(int limit) {
//        //language=MySQL
//        String query = "SELECT id, created, updated, description, exercise_id, user_id\n" +
//                "FROM solutions\n" +
//                "ORDER BY updated DESC, created DESC\n" +
//                "LIMIT ? ";
//        return loadMany(query, String.valueOf(limit));
//    }

    public static ArrayList<Solution> loadLast5() {
        //language=MySQL
        String query = "SELECT id, created, updated, description, exercise_id, user_id\n" +
                "FROM solutions\n" +
                "ORDER BY created DESC, updated DESC\n" +
                "LIMIT 5 ";
        return loadMany(query);
    }

    public static ArrayList<Solution> loadAllByUserId(int userId) {
        //language=MySQL
        String query = "SELECT id, created, updated, description, exercise_id, user_id\n" +
                "FROM solutions\n" +
                "WHERE user_id = ?\n" +
                "ORDER BY created DESC ";
        return loadMany(query, String.valueOf(userId));
    }

    public static ArrayList<Solution> loadAllByExerciseId(int exerciseId) {
        //language=MySQL
        String query = "SELECT id, created, updated, description, exercise_id, user_id FROM solutions WHERE exercise_id = ?";
        return loadMany(query, String.valueOf(exerciseId));
    }

    public static Solution loadById(int id) {
        //language=MySQL
        String query = "SELECT id, created, updated, description, exercise_id, user_id FROM solutions WHERE id = ?";
        return loadOne(query, String.valueOf(id));
    }

    public static void save(Solution solution) {
        List<String> params = new ArrayList<>();
        params.add(solution.getCreated().toString());
        params.add(solution.getUpdated() != null ? solution.getUpdated().toString() : null);
        params.add(solution.getDescription());
        params.add(String.valueOf(solution.getExercise().getId()));
        params.add(String.valueOf(solution.getUser().getId()));

        if (solution.getId() == 0) {
            //language=MySQL
            String query = "INSERT INTO solutions(created, updated, description, exercise_id, user_id) VALUES(?,?,?,?,?)";

            try {
                Integer id = DbService.insertIntoDatabase(query, params);
                if (id != null) {
                    solution.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            //language=MySQL
            String query = "UPDATE solutions SET created = ?, updated = ?, description = ?, exercise_id = ?, user_id = ? WHERE id = ?";
            params.add(String.valueOf(solution.getId()));

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
            String query = "DELETE FROM solutions WHERE id = ?";

            try {
                DbService.executeUpdate(query, String.valueOf(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


