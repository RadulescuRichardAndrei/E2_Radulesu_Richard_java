import java.util.LinkedList;
import java.util.List;

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

}
