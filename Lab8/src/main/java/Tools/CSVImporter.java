package Tools;

import DAO.DaoCities;
import Data.City;
import com.github.javafaker.Faker;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVImporter {
    DaoCities daoC;

    public CSVImporter() {
        this.daoC = new DaoCities();
    }

    public void readFromCSV(String pathToFile){

        try(BufferedReader br = Files.newBufferedReader(Path.of(pathToFile), StandardCharsets.UTF_8)) {
            String line=  br.readLine();
            line=  br.readLine();
            while (line!= null){
                String[] attributes= line.split(",");
                addToDB(attributes);
                line=br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void addToDB(String[] attributes){
        Faker faker= new Faker();
        City c= new City(String.valueOf(faker.number().numberBetween(1,10000)),attributes[1],
                attributes[0],"y",attributes[2],attributes[3]);
        daoC.create(c);
    }
}
