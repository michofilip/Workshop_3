package pl.coderslab.models;

import java.time.LocalDateTime;

public class Solution {
    private int id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String description;
    private Exercise exercise;
    private User user;

    public Solution() {
        this.id = 0;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                ", exercise=" + exercise.getId() +
                ", user=" + user.getId() +
                '}';
    }

    // getters & setters
    public int getId() {
        return id;
    }

    public Solution setId(int id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Solution setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public Solution setUpdated(LocalDateTime updated) {
        this.updated = updated;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Solution setDescription(String description) {
        this.description = description;
        return this;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public Solution setExercise(Exercise exercise) {
        this.exercise = exercise;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Solution setUser(User user) {
        this.user = user;
        return this;
    }
}
