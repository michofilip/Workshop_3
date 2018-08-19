package pl.coderslab.dao;

import pl.coderslab.models.Exercise;
import pl.coderslab.service.DbService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDao {
    public static Exercise makeExercise(String[] strs) {
        return new Exercise()
                .setId(Integer.parseInt(strs[0]))
                .setTitle(strs[1])
                .setDescription(strs[2]);
    }

    private static Exercise loadOne(String query, String... parameters) {
        try {
            List<String[]> list = DbService.getData(query, parameters);
            if (!list.isEmpty()) {
                return makeExercise(list.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Exercise> loadMany(String query, String... parameters) {
        ArrayList<Exercise> exercises = new ArrayList<>();
        try {
            List<String[]> list = DbService.getData(query, parameters);
            list.forEach(item -> exercises.add(makeExercise(item)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercises;
    }

    // crud
    public static ArrayList<Exercise> loadAll() {
        //language=MySQL
        String query = "SELECT id, title, description FROM exercises";
        return loadMany(query);
    }

    public static ArrayList<Exercise> loadAllUnsolvedByUserId(int userId) {
        ArrayList<Exercise> list = new ArrayList<>();
        //language=MySQL
        String query = "SELECT id, title, description\n" +
                "FROM exercises\n" +
                "       LEFT JOIN solutions ON exercises.id = solutions.exercise_id\n" +
                "       JOIN users ON solutions.user_id = users.id\n" +
                "WHERE user_id = ? AND exercises IS NULL ";

        return loadMany(query, String.valueOf(userId));
    }

    public static Exercise loadById(int id) {
        //language=MySQL
        String query = "SELECT id, title, description FROM exercises WHERE id = ?";
        return loadOne(query, String.valueOf(id));
    }

    public static void save(Exercise exercise) {
        List<String> params = new ArrayList<>();
        params.add(exercise.getTitle());
        params.add(exercise.getDescription());

        if (exercise.getId() == 0) {
            //language=MySQL
            String query = "INSERT INTO exercises(title, description) VALUES(?, ?)";

            try {
                Integer id = DbService.insertIntoDatabase(query, params);
                if (id != null) {
                    exercise.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            //language=MySQL
            String query = "UPDATE exercises SET title = ?, description = ? WHERE id = ?";
            params.add(String.valueOf(exercise.getId()));

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
            String query = "DELETE FROM exercises WHERE id = ?";

            try {
                DbService.executeUpdate(query, String.valueOf(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
