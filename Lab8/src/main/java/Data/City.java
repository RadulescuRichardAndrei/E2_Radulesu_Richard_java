package Data;

import java.util.Map;
import java.util.Objects;

public class City {
    private String id, name, country;
    private char capital;
    private double latitude, longitude;

    public City(String id, String name, String country, String capital, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.capital = capital.charAt(0);
        this.latitude = Double.parseDouble(latitude);
        this.longitude =Double.parseDouble(longitude);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) && Objects.equals(name, city.name) && Objects.equals(country, city.country) && Objects.equals(capital, city.capital) && Objects.equals(latitude, city.latitude) && Objects.equals(longitude, city.longitude);
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", capital=" + capital +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, capital, latitude, longitude);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public char getCapital() {
        return capital;
    }

    public void setCapital(char capital) {
        this.capital = capital;
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

    public double distanceBetween(City otherCity){
        double distLat= Math.toRadians( latitude-otherCity.getLatitude() );
        double distLon= Math.toRadians( longitude-otherCity.getLongitude() );

        double a=Math.sin(distLat/2)* Math.sin(distLat/2)+
                Math.cos(Math.toRadians(otherCity.getLatitude()))*
                Math.cos(Math.toRadians( latitude))*
                Math.sin(distLon/2)*Math.sin(distLon/2);
        double c=2* Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        return c* 6371.0;
    }
}
