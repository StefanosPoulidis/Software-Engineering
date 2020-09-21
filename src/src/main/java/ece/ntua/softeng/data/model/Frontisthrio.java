package ece.ntua.softeng.data.model;

import java.lang.Object;
import java.sql.Timestamp;
import java.util.Objects;

public class Frontisthrio {

    private final long id;
    private final String name;
    private final String description;
    private final Timestamp timestamp;
    private final String phonenumber;
    private final long user_id;

    public Frontisthrio (long id, String name, String description, Timestamp timestamp, String phonenumber, long user_id) {
        this.id = id;
        this.name = name;
        this.description  = description;
        this.timestamp = timestamp;
        this.phonenumber = phonenumber;
        this.user_id = user_id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getPhonenumber() { return phonenumber; }

    public long getUserId() { return user_id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frontisthrio fr = (Frontisthrio) o;
        return (this.id == fr.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
