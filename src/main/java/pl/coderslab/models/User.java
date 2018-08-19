package pl.coderslab.models;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private Group group;

    public User() {
        this.id = 0;
    }

    public User(String username, String password, String email, Group group) {
        this(0, username, email, password, group);
    }

    public User(int id, String username, String email, String password, Group group) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.group = group;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", group_id=" + group.getId() +
                ", group=" + group.getName() +
                '}';
    }

    // getters & setters
    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Group getGroup() {
        return group;
    }

    public User setGroup(Group group) {
        this.group = group;
        return this;
    }
}
