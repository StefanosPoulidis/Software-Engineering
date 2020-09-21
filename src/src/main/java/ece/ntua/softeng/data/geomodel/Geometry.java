package ece.ntua.softeng.data.geomodel;

public class Geometry {

    private final String type = "Point";
    private float[] coordinates = new float[2];

    public Geometry(float latitude, float longitude) {
        this.coordinates[0] = latitude;
        this.coordinates[1] = longitude;
    }

    public String getType() {
        return type;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

}
