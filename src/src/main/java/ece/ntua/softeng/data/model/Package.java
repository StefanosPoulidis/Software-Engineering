package ece.ntua.softeng.data.model;

import java.util.Objects;


public class Package {

    private final long id;
    private final Fields field;
    private final short price;
    private final int success_rate;
    private final String city;
    private final String street;
    private final int street_number;
    private final float latitude;
    private final float longitude;
    private final long frontisthrio_id;

    public Package (long id, Fields field, short price, int success_rate, String city, String street, int street_number, float latitude, float longitude, long frontisthrio_id ) {
        this.id = id;
        this.field = field;
        this.price = price;
        this.success_rate = success_rate;
        this.city = city;
        this.street = street;
        this.street_number = street_number;
        this.latitude = latitude;
        this.longitude = longitude;
        this.frontisthrio_id = frontisthrio_id;
    }

    public long getId() {
        return id;
    }

    public Fields getField() {
        return field;
    }

    public short getPrice() {
        return price;
    }

    public int getSuccessRate() {
        return success_rate;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getStreetNumber() {
        return street_number;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public long getFrontisthrioId() {
        return frontisthrio_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package pack = (Package) o;
        return (this.id == pack.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
