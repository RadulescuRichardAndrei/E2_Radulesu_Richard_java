import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class City {
    private String name;
    private List<Street> listSt;
    private Set<Intersect> setIn;
    private Map<String, Integer> mapInSt;

    public City() {
    }

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

    public void printSortStr() {
        getListSt().stream().sorted(Comparator.comparing(Street::getLength)).forEach(System.out::println);
    }

    public void query(int stLength) {
        mapInSt=new HashMap<>();
        for (Street s : listSt) {
            if (!mapInSt.containsKey(s.getInter1().getName()))
                mapInSt.put(s.getInter1().getName(), 0);
            else
                mapInSt.put(s.getInter1().getName(), mapInSt.get(s.getInter1().getName()) + 1);

            if (!mapInSt.containsKey(s.getInter2().getName()))
                mapInSt.put(s.getInter2().getName(), 0);
            else
                mapInSt.put(s.getInter2().getName(), mapInSt.get(s.getInter2().getName()) + 1);


        }
        //System.out.println(mapInSt);

        getListSt().stream().filter(street -> street.getLength() > stLength)
                .filter(street -> mapInSt.get(street.getInter1().getName())>3 || mapInSt.get(street.getInter2().getName())>3)
                .forEach(System.out::println);

    }

    public void generateCity() {
        Faker fk = new Faker();
        setName(fk.address().cityName());
        List<Street> streetList = new LinkedList<>();
        Set<Intersect> interSet = new HashSet<>();

        int nbInter = (int) (Math.random() * 20 + 10);
        for (int i = 0; i < nbInter; i++) {
            fk = new Faker();
            interSet.add(new Intersect(fk.address().streetName()));
        }
        int nbStr = (int) (Math.random() * 40 + 20);
        for (int i = 0; i < nbStr; i++) {
            int in1 = (int) (Math.random() * nbInter);
            int in2 = (int) (Math.random() * nbInter);
            int length = (int) (Math.random() * 50 + 1);
            fk = new Faker();
            streetList.add(new Street(interSet.stream().skip(in1).findFirst().orElse(null),
                    interSet.stream().skip(in2).findFirst().orElse(null),
                    fk.address().streetName(), length));
        }
        setListSt(streetList);
        setSetIn(interSet);
    }


}
