package Data;

public class EarthPosition {
    final static double RADIUS_MAJOR = 6378137.0;
    final static double RADIUS_MINOR = 6356752.3142;
    private double latitude, longitude;

    public EarthPosition(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double xAxisProjection(){
        return Math.toRadians(longitude)* RADIUS_MAJOR;
    }
    public double yAxisProjection(){
        return Math.log(Math.tan(Math.PI /4 +
                Math.toRadians(latitude)/2))* RADIUS_MAJOR;
    }

    @Override
    public String toString() {
        return "EarthPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
