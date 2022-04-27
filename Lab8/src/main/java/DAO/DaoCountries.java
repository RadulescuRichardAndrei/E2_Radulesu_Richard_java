package DAO;

import Data.Continent;
import Data.Country;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DaoCountries implements Dao<Country>{

    private final String url="jdbc:oracle:thin:STUDENT/STUDENT@localhost:1521/XE";
    private final String insert="Insert INTO Countries(Id,Name,Code,Continent) Values(?, ?, ?, ?)";
    private final String select="select id, name, code, continent from Countries where id= ? and name = ?";
    //    private List<Country> countries=new LinkedList<>();


    @Override
    public Country get(String id,String name) {
        try{Connection conn= DriverManager.getConnection(url);
            PreparedStatement prStmt=conn.prepareStatement(select);
            prStmt.setString(1,id);
            prStmt.setString(2,name);
            ResultSet result= prStmt.executeQuery();

            result.absolute(1);
            Country foundContry= new Country(result.getString("id"),
                    result.getString("name"), result.getString("code"),
                    result.getString("continent"));

            return foundContry;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void create(Country country) {
        try{Connection conn= DriverManager.getConnection(url);
            PreparedStatement prStmt=conn.prepareStatement(insert);
            prStmt.setString(1,country.getId());
            prStmt.setString(2,country.getName());
            prStmt.setString(3,country.getCode());
            prStmt.setString(4,country.getContinent());
            prStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
