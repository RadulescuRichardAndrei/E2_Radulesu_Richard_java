import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class City {
    private String name;
    private List<Street> listSt;
    private Set<Intersect> setIn;

    public City() {}

    public City(String name, List<Street> listSt, Set<Intersect> setIn) {
        this.name = name;
        this.listSt = listSt;
        this.setIn = setIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Street> getListSt() {
        return listSt;
    }

    public void setListSt(List<Street> listSt) {
        this.listSt = listSt;
    }

    public Set<Intersect> getSetIn() {
        return setIn;
    }

    public void setSetIn(Set<Intersect> setIn) {
        this.setIn = setIn;
    }

   /* public void query(int stLength) {
        List<Street> listFirstIn = getListSt().stream().filter(street -> street.getLength() > stLength)
                .map(street -> street.getInter1()).mapToInt()
        getListSt().stream().filter(street -> street.getLength() > stLength).filter(street -> !listFirstIn.contains(street)).map(street -> street.getInter2()).forEach(System.out::println);
    }*/

    public void generateCity() {
        Faker fk = new Faker();
        setName(fk.address().cityName());
        List<Street> streetList = new LinkedList<>();
        Set<Intersect> interSet = new HashSet<>();

        int nbInter = (int) (Math.random() * 20 + 10);
        for (int i = 0; i < nbInter; i++) {
            fk=new Faker();
            interSet.add(new Intersect(fk.address().streetName()));
        }
        int nbStr = (int) (Math.random() * 20 + 10);
        for (int i = 0; i < nbStr; i++) {
            int st1 = (int) (Math.random() * nbInter);
            int st2 = (int) (Math.random() * nbInter);

            fk=new Faker();
            streetList.add(new Street(interSet.stream().skip(st1).findFirst().orElse(null),
                    interSet.stream().skip(st2).findFirst().orElse(null),
                    fk.address().streetName()
                    ));
        }
        setListSt(streetList);
        setSetIn(interSet);
    }


}
