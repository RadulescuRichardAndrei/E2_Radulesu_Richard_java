import java.util.*;

public class Network {
    public ArrayList<Node> listN;
    static final int infinity = 1000;

    public void printIdentifiable() {
        ArrayList<Identifiable> listNIdent = new ArrayList<Identifiable>();
        for (int i = 0; i < listN.size(); i++) {
            if (listN.get(i) instanceof Identifiable) {
                listNIdent.add((Identifiable) listN.get(i));
            }
        }
        listNIdent.sort(Comparator.comparing(Identifiable::getIp));

        System.out.println(listNIdent);
    }

    public void printNetworkCosts() {
        for (int i = 0; i < listN.size(); i++)
            if (Objects.nonNull(listN.get(i).getCost())) {
                System.out.println("From " + listN.get(i).getName() + " to: ");
                System.out.println(listN.get(i).getCost());
            }

    }

    public void floydWasrshall() {
        int[][] dist = new int[listN.size()][listN.size()];

        for (int[] row : dist)
            Arrays.fill(row, infinity);

        for (int i = 0; i < listN.size(); i++)
            if(listN.get(i) instanceof Identifiable) {
                for (int j = 0; j < listN.size(); j++)
                    if (i != j && listN.get(j) instanceof Identifiable && Objects.nonNull(listN.get(i).getCost()) &&
                            listN.get(i).getCost().containsKey(listN.get(j).getName())) {
                        dist[i][j] = listN.get(i).getCost().get(listN.get(j).getName());
                    }
            }
        for (int i = 0; i < listN.size(); i++)
            dist[i][i] = 0;

        for (int k = 0; k < listN.size(); k++)
            for (int i = 0; i < listN.size(); i++)
                for (int j = 0; j < listN.size(); j++)
                    if (dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];

        System.out.println(Arrays.deepToString(dist));

    }

    public int dikstra(int source, int target) {
        int[] dist = new int[listN.size()];
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparing(Map.Entry::getValue));

        dist[source] = 0;
        for (int i = 0; i < listN.size(); i++)
            if (i != source)
                dist[i] = infinity;
        pq.add(new AbstractMap.SimpleEntry<>(source, 0));
        while (!pq.isEmpty()) {
            Map.Entry<Integer, Integer> node = pq.poll();

            for (int j = 0; j < listN.size(); j++)
                if (node.getKey() != j && Objects.nonNull(listN.get(node.getKey()).getCost()) &&
                        listN.get(node.getKey()).getCost().containsKey(listN.get(j).getName())) {
                    int weight = listN.get(node.getKey()).getCost().get(listN.get(j).getName());
                    if (dist[j] > dist[node.getKey()] + weight) {
                        dist[j] = dist[node.getKey()] + weight;
                        pq.add(new AbstractMap.SimpleEntry<>(j, dist[j]));
                    }
                }

        }
        return dist[target];
    }

    public void generateNetwork() {
        listN = new ArrayList<Node>();
        int size = MyRandom.generateSize();
        for (int i = 0; i < size; i++) {
            listN.add(new Computer(MyRandom.generateString(), MyRandom.generateString(), MyRandom.generateString(), MyRandom.generateIp(), MyRandom.generateCost()));
        }
        for (int i = 0; i < size; i++) {
            Map<String, Integer> cost = new HashMap<>();
            int numberNeighbour = MyRandom.generateRange(1, size - 3);
            for (int j = 0; j < numberNeighbour; j++) {
                int neighbour = MyRandom.generateRange(0, size - 1);
                while (neighbour == i) neighbour = MyRandom.generateRange(0, size - 1);
                cost.put(listN.get(neighbour).getName(), MyRandom.generateCost());
            }
            listN.get(i).setCost(cost);
        }

    }

    public void initialize() {

        listN = new ArrayList<Node>();
        listN.add(new Computer("ComputerA", "84-3A-4B-C8-E9-00", "Romania", "127.0.0.1", 240));
        listN.add(new Router("RouterA", "84-3A-4B-C8-E9-00", "Romania", "127.0.0.5"));
        listN.add(new Switch("SwitchA", "84-3A-4B-C8-E9-00", "Romania"));
        listN.add(new Switch("SwitchB", "84-3A-4B-C8-E9-00", "Ungaria"));
        listN.add(new Router("RouterB", "84-3A-4B-C8-E9-00", "Bulgaria", "100.1.5.7"));
        listN.add(new Computer("ComputerB", "84-3A-4B-C8-E9-00", "Bulgaria", "100.1.5.6", 150));

        Map<String, Integer> cost = new HashMap<>();
        cost.put(listN.get(1).getName(), 10);
        cost.put(listN.get(2).getName(), 50);
        listN.get(0).setCost(cost);

        cost = new HashMap<>();
        cost.put(listN.get(2).getName(), 20);
        cost.put(listN.get(3).getName(), 20);
        cost.put(listN.get(4).getName(), 20);
        listN.get(1).setCost(cost);

        cost = new HashMap<>();
        cost.put(listN.get(3).getName(), 10);
        listN.get(2).setCost(cost);

        cost = new HashMap<>();
        cost.put(listN.get(4).getName(), 30);
        cost.put(listN.get(5).getName(), 10);
        listN.get(3).setCost(cost);

        cost = new HashMap<>();
        cost.put(listN.get(5).getName(), 20);
        listN.get(4).setCost(cost);
    }



}
