import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ProblemTSP {

    private City cityTsp;
    private List<Street> Route;

    public List<Street> getRoute() {
        return Route;
    }

    public void setRoute(List<Street> route) {
        Route = route;
    }

    public ProblemTSP() {
        cityTsp = new City();
        Route = new LinkedList<>();
    }

    public ProblemTSP(City cytyTsp) {
        this.cityTsp = cytyTsp;
    }

    public City getCityTsp() {
        return cityTsp;
    }

    public void setCityTsp(City cityTsp) {
        this.cityTsp = cityTsp;
    }

    public void generateRandomProb(int nbIn, int nbStr) {
        cityTsp.generateCity(nbIn, nbStr);
    }

    public void solveProb() {
        boolean[] used = new boolean[getCityTsp().getListSt().size()];

        used[0] = true;
        getRoute().add(getCityTsp().getListSt().get(0));
        findHamilCycle(used, getCityTsp().getListSt().get(0).getInter2(), 0);
        int ans = Route.stream().mapToInt(Street::getLength).sum();
        System.out.println("Cost of path is: " + ans + "\n" + "Path is:");
        System.out.println(getRoute());
    }

    public boolean findHamilCycle(boolean[] used, Intersect lastIn, int count) {

        if (count == cityTsp.getSetIn().size() - 2) {
            for (Street s : getCityTsp().getListSt())
                if (s.equalStByIn(lastIn, getCityTsp().getListSt().get(0).getInter1())) {
                    getRoute().add(s);
                    return true;
                }
        } else
            for (int i = 1; i < cityTsp.getListSt().size(); i++) {
                if (!used[i] && cityTsp.getListSt().get(i).containIn(lastIn) &&
                        cityTsp.getListSt().get(i).otherIn(lastIn) != getCityTsp().getListSt().get(0).getInter1()) {

                    used[i] = true;
                    getRoute().add(cityTsp.getListSt().get(i));

                    if(!findHamilCycle(used, cityTsp.getListSt().get(i).otherIn(lastIn), count + 1)) {
                        getRoute().remove(cityTsp.getListSt().get(i));
                        used[i] = false;
                    }
                    else return true;

                }
            }
            return false;

    }
    public void generateKnownProb(){
        List<Street> streetList = new LinkedList<>();
        Set<Intersect> interSet = new HashSet<>();
        for (int i=1;i<=9;i++){
            interSet.add(new Intersect("In"+i));
        }
        Intersect i1, i2;

        i1=interSet.stream().skip(1).findFirst().orElse(null);
        i2=interSet.stream().skip(2).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street1",2));

        i1=interSet.stream().skip(1).findFirst().orElse(null);
        i2=interSet.stream().skip(4).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street2",2));

        i1=interSet.stream().skip(1).findFirst().orElse(null);
        i2=interSet.stream().skip(3).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street3",2));

        i1=interSet.stream().skip(3).findFirst().orElse(null);
        i2=interSet.stream().skip(5).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street4",3));

        i1=interSet.stream().skip(5).findFirst().orElse(null);
        i2=interSet.stream().skip(6).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street5",5));

        i1=interSet.stream().skip(6).findFirst().orElse(null);
        i2=interSet.stream().skip(7).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street6",1));

        i1=interSet.stream().skip(7).findFirst().orElse(null);
        i2=interSet.stream().skip(8).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street7",1));

        i1=interSet.stream().skip(8).findFirst().orElse(null);
        i2=interSet.stream().skip(2).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street8",3));

        i1=interSet.stream().skip(2).findFirst().orElse(null);
        i2=interSet.stream().skip(4).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street9",2));

        i1=interSet.stream().skip(4).findFirst().orElse(null);
        i2=interSet.stream().skip(5).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street10",2));

        i1=interSet.stream().skip(8).findFirst().orElse(null);
        i2=interSet.stream().skip(5).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street11",1));

        i1=interSet.stream().skip(4).findFirst().orElse(null);
        i2=interSet.stream().skip(9).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street12",2));

        i1=interSet.stream().skip(7).findFirst().orElse(null);
        i2=interSet.stream().skip(9).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street13",1));

        i1=interSet.stream().skip(6).findFirst().orElse(null);
        i2=interSet.stream().skip(9).findFirst().orElse(null);
        streetList.add(new Street(i1,i2,"Street14",1));

        setCityTsp(new City("TestCity",streetList,interSet));
    }

}
