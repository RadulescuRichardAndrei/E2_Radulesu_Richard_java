import Tools.CSVImporter;
import Tools.ScreenMap;

import java.sql.SQLException;

public class Main1 {

    public static void main(String[] args) throws SQLException {
        CSVImporter csvImporter = new CSVImporter();
        csvImporter.readFromCSV("D:\\facultate\\E2_java_scripts\\GitHub\\Lab8\\cities_data.csv");

    }
}
