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
        mapInSt = new HashMap<>();
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
                .filter(street -> mapInSt.get(street.getInter1().getName()) > 3 || mapInSt.get(street.getInter2().getName()) > 3)
                .forEach(System.out::println);

    }

    public int costStreetBetweenIn(Intersect i1, Intersect i2) {
        for (Street s : listSt) {
            if (s.equalStByIn(i1, i2))
                return s.getLength();
        }
        return 0;
    }

    public void generateCity(int nbInter, int nbStr) {
        Faker fk = new Faker();
        setName(fk.address().cityName());
        List<Street> streetList = new LinkedList<>();
        Set<Intersect> interSet = new HashSet<>();


        for (int i = 0; i < nbInter; i++) {
            fk = new Faker();
            interSet.add(new Intersect(fk.address().streetName()));
        }

        for (int i = 0; i < nbStr; i++) {
            Intersect i1, i2;

            while (true) {//no 2 edge between same nodes
                int in1 = (int) (Math.random() * nbInter);
                int in2 = (int) (Math.random() * nbInter);
                while (in2 == in1) in2 = (int) (Math.random() * nbInter);//no edge to self

                i1 = interSet.stream().skip(in1).findFirst().orElse(null);
                i2 = interSet.stream().skip(in2).findFirst().orElse(null);
                boolean exit = true;

                    for (Street st : streetList)
                        if (st.equalStByIn(i1, i2))
                            exit = false;

                if (exit) break;

            }
            int length = (int) (Math.random() * 50 + 1);
            fk = new Faker();
            streetList.add(new Street(i1, i2, fk.address().streetName(), length));
        }
        setListSt(streetList);
        setSetIn(interSet);
    }


}
