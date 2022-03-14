import java.util.*;

public class Main {
    public static void initialize(Network N){

        N.listN = new ArrayList<Node>();
        N.listN.add(new Computer("ComputerA","84-3A-4B-C8-E9-00","Romania","127.0.0.1",240));
        N.listN.add(new Router("RouterA","84-3A-4B-C8-E9-00","Romania","127.0.0.5"));
        N.listN.add(new Switch("SwitchA","84-3A-4B-C8-E9-00","Romania"));
        N.listN.add(new Switch("SwitchB","84-3A-4B-C8-E9-00","Ungaria"));
        N.listN.add(new Router("RouterB","84-3A-4B-C8-E9-00","Bulgaria","100.1.5.7"));
        N.listN.add(new Computer("ComputerB","84-3A-4B-C8-E9-00","Bulgaria","100.1.5.6",150));

        Map<String,Integer> cost =new HashMap<>();
        cost.put(N.listN.get(1).getName(),10);
        cost.put(N.listN.get(2).getName(),50);
        N.listN.get(0).setCost(cost);

        cost=new HashMap<>();
        cost.put(N.listN.get(2).getName(),20);
        cost.put(N.listN.get(3).getName(),20);
        cost.put(N.listN.get(4).getName(),20);
        N.listN.get(1).setCost(cost);

        cost=new HashMap<>();
        cost.put(N.listN.get(3).getName(),10);
        N.listN.get(2).setCost(cost);

        cost=new HashMap<>();
        cost.put(N.listN.get(4).getName(),30);
        cost.put(N.listN.get(5).getName(),10);
        N.listN.get(3).setCost(cost);

        cost=new HashMap<>();
        cost.put(N.listN.get(5).getName(),20);
        N.listN.get(4).setCost(cost);
    }

    public static void floydWasrshall(Network N){
        int[][] dist= new int[N.listN.size()][N.listN.size()];

        for(int[] row :dist)
            Arrays.fill(row,1000);

        for (int i=0;i<N.listN.size();i++)
            for(int j=0;j<N.listN.size();j++)
                if (i != j && Objects.nonNull(N.listN.get(i).getCost()) && N.listN.get(i).getCost().containsKey(N.listN.get(j).getName())){
                    dist[i][j]=N.listN.get(i).getCost().get(N.listN.get(j).getName());
                }
        for (int i=0;i<N.listN.size();i++)
            dist[i][i]=0;

        for (int k=0;k<N.listN.size();k++)
            for (int i=0;i<N.listN.size();i++)
                for (int j=0;j<N.listN.size();j++)
                    if(dist[i][j]>dist[i][k]+dist[k][j])
                        dist[i][j]=dist[i][k]+dist[k][j];

        System.out.println(Arrays.deepToString(dist));

    }

    public static void main(String[] args){
        Network N = new Network();
        initialize(N);
        N.printNetworkCosts();
        System.out.println(N.listN);
        N.printIdentifiable();
        floydWasrshall(N);

    }
}
