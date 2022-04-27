import DAO.DaoContinent;
import Data.Continent;
import org.junit.jupiter.api.Test;
public class test1 {

    @Test
    void InsertAndSelect(){
        DaoContinent daoContinent = new DaoContinent();
        Continent testContinent= new Continent("13","Aspia");

        daoContinent.create(testContinent);
        Continent result= daoContinent.get(testContinent.getId(),testContinent.getName());
        assert result.equals(testContinent);

    }
}
