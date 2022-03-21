import java.util.List;

public class ProblemTSP {

    private City cityTsp;

    public ProblemTSP() {}

    public ProblemTSP(City cytyTsp) {
        this.cityTsp = cytyTsp;
    }

    public City getCytyTsp() {
        return cityTsp;
    }

    public void setCytyTsp(City cytyTsp) {
        this.cityTsp = cytyTsp;
    }
    public void generateProb(){
        cityTsp.generateCity();
    }

    public int findHamilCycle(boolean[] used, int cost, Street lastSt, int count, int answer, List<Street> Route) {
        int value = cityTsp.costStreetBetween(lastSt, cityTsp.getListSt().get(0));
        if (count == cityTsp.getSetIn().size() && value > 0)
            return Math.min(answer, cost + value);
        for (int i = 0; i < cityTsp.getListSt().size(); i++) {
            if (used[i] == false && lastSt.isIncident(cityTsp.getListSt().get(i))) {

                used[i] = true;
                Route.add(cityTsp.getListSt().get(i));
                answer = findHamilCycle(used, cost + cityTsp.getListSt().get(i).getLength(),
                        cityTsp.getListSt().get(i), count + 1, answer, Route);
                Route.remove(cityTsp.getListSt().get(i));
                used[i] = false;

            }
        }
        return answer;
    }

}
