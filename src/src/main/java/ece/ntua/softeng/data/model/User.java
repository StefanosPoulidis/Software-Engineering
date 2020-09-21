package ece.ntua.softeng.data.model;

import java.util.Objects;

public class User {

    private final long id;
    private final String username;
    private final String password;
    private final String email;
    private short privilege;

    public User() {
        this.id = 0;
        this.username = "";
        this.password = "";
        this.email = "";
        this.privilege = 0;
    }

    public User(long id, String username, String password, String email, short privilege) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.privilege = privilege;
    }

    public long getId() {
        return id;
    }

    public int getPrivilege() {
        return privilege;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() { return email; }

    public void setPrivilege(short privilege) {
        this.privilege = privilege;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User usr = (User) o;
        return (this.id == usr.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id);}
}
