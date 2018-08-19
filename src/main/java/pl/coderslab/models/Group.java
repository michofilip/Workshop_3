package pl.coderslab.models;

public class Group {
    private int id;
    private String name;

    public Group() {
        this.id = 0;
    }

    public Group(String name) {
        this(0, name);
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // getters & setters
    public int getId() {
        return id;
    }

    public Group setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }
}
