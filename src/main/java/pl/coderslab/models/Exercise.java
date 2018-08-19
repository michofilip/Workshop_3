package pl.coderslab.models;

public class Exercise {
    private int id;
    private String title;
    private String description;

    public Exercise() {
        this.id = 0;
    }

    public Exercise(String title, String description) {
        this(0, title, description);
    }

    public Exercise(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    // getters & setters
    public int getId() {
        return id;
    }

    public Exercise setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Exercise setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Exercise setDescription(String description) {
        this.description = description;
        return this;
    }
}
