import DAO.DaoCities;
import DAO.DaoContinents;
import DAO.DaoCountries;
import Data.City;
import Data.Continent;
import Data.Country;

public class Main {
    public static void main(String[] args) {

        DaoCities dc = new DaoCities();
        City c1 = dc.get("3306", "Brussels");
        City c2 = dc.get("4208", "Minsk");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c1.distanceBetween(c2));

        DaoContinents continents= new DaoContinents();
        System.out.println(continents.get("16","Europe"));

        Continent cont=new Continent("17","Asia");
        continents.create(cont);

        DaoCountries countries=new DaoCountries();
        Country country=new Country("2","Franta","FR","Europe");
        countries.create(country);
        System.out.println(countries.get("2","Franta"));


    }
}

