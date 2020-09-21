package ece.ntua.softeng.data.geomodel;

import ece.ntua.softeng.data.model.Fields;

public class Property {

    private long id;
    private Fields field;
    private short price;
    private String street;
    private int street_number;
    private final String frontisthrio_name;

    public Property(long id, Fields field, short price, String street, int street_number, String frontisthrio_name) {
        this.id = id;
        this.field = field;
        this.price = price;
        this.street = street;
        this.street_number = street_number;
        this.frontisthrio_name = frontisthrio_name;
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

    public String getStreet() {
        return street;
    }

    public int getStreet_number() {
        return street_number;
    }

    public String getFrontisthrio_name() {
        return frontisthrio_name;
    }


}
