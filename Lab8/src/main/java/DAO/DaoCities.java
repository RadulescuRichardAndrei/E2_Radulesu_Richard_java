package DAO;

import Data.City;
import Data.Continent;
import Data.EarthPosition;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DaoCities implements Dao<City>{

    private final String url="jdbc:oracle:thin:STUDENT/STUDENT@localhost:1521/XE";
    private final String insert="Insert INTO Cities(Id,Country,Name,capital,latitude,longitude) Values(?, ?, ?, ?, ?, ?)";
    private final String select="select id, country, name, capital, latitude, longitude  from Cities where id= ? and name = ?";
    private final String selectLatLong="select latitude, longitude from Cities";


    @Override
    public City get(String id, String name) {
        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement prStmt = conn.prepareStatement(select);
            prStmt.setString(1, id);
            prStmt.setString(2, name);
            ResultSet result = prStmt.executeQuery();

            while (result.next()) {
                City foundCity = new City(result.getString("id"),
                        result.getString("name"), result.getString("country"),
                        result.getString("capital"), result.getString("latitude"),
                        result.getString("longitude"));
                return foundCity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<EarthPosition> getLocations(){
        List<EarthPosition> list=new LinkedList<>();
        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement prStmt = conn.prepareStatement(selectLatLong);
            ResultSet result = prStmt.executeQuery();

            while (result.next()) {
                EarthPosition ep=new EarthPosition(Double.valueOf(result.getString("latitude")),
                        Double.valueOf(result.getString("longitude")));
                list.add(ep);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

        @Override
    public void create(City city) {

            try{
                ComboPooledDataSource cpds= getDataSource();


                try (Connection conn= cpds.getConnection();
                     PreparedStatement prStmt=conn.prepareStatement(insert);){

                    prStmt.setString(1,city.getId());
                    prStmt.setString(2,city.getCountry());
                    prStmt.setString(3,city.getName());
                    prStmt.setString(4,String.valueOf(city.getCapital()));
                    prStmt.setString(5,String.valueOf(city.getLatitude()));
                    prStmt.setString(6,String.valueOf(city.getLongitude()));
                    prStmt.executeUpdate();
                    }
            } catch (SQLException e) {
                e.printStackTrace();

            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }

        }
}
