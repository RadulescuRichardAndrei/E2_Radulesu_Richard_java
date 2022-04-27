package DAO;

import Data.Continent;
import Data.Country;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DaoContinent implements Dao<Continent> {

    private final String url="jdbc:oracle:thin:STUDENT/STUDENT@localhost:1521/XE";
    private final String insert="Insert INTO Continent(Id,Name) Values(?, ?)";
    private final String select="select id, name from Continent where id= ? and name = ?";
    //private List<Continent> continents=new LinkedList<>();


    @Override
    public Continent get(String id, String name) {
        try{
            Connection conn= DriverManager.getConnection(url);
            PreparedStatement prStmt=conn.prepareStatement(select);
            prStmt.setString(1,id);
            prStmt.setString(2,name);
            ResultSet result= prStmt.executeQuery();

            while (result.next()) {
                Continent foundContinent = new Continent(result.getString("id"),
                        result.getString("name"));
                return foundContinent;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Continent continent) {
        try{Connection conn= DriverManager.getConnection(url);
            PreparedStatement prStmt=conn.prepareStatement(insert);
            prStmt.setString(1,continent.getId());
            prStmt.setString(2,continent.getName());
            prStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
