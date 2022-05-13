package client;

import Data.ClientData;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedList;
import java.util.List;

public class test {
    private static Graph<ClientData, DefaultEdge> Clients = new SimpleGraph<>(DefaultEdge.class);

    public static void cohesionSocialNetwork() {
        Graph<String, DefaultWeightedEdge> graphFlow = new DefaultDirectedGraph<>(DefaultWeightedEdge.class);
        graphFlow.addVertex("S");
        graphFlow.addVertex("T");
        for (ClientData cl : Clients.vertexSet()) {
            graphFlow.addVertex("Left_" + cl.getUsername());
            Graphs.addEdge(graphFlow,"S", "Left_" + cl.getUsername(),1);
            graphFlow.addVertex(cl.getUsername());
            Graphs.addEdge(graphFlow,cl.getUsername(), "T",1);
        }
        for (ClientData cl : Clients.vertexSet()) {
            Graphs.neighborSetOf(Clients, cl).stream().forEach(clientData -> Graphs.addEdge(graphFlow,"Left_" + cl.getUsername(), clientData.getUsername(),1));
        }
        MaximumFlowAlgorithm<String, DefaultWeightedEdge> mf = new EdmondsKarpMFImpl<>(graphFlow);
        System.out.println(mf.getMaximumFlow("S", "T"));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        List<ClientData> clientDataList=new LinkedList<>();
        for (int i = 0; i <= 100; i++) {
            clientDataList.add(new ClientData(String.valueOf(i),String.valueOf(i)+"parola"));
        }
        clientDataList.forEach(clientData ->  Clients.addVertex(clientData));
        for(int i=0;i<=100;i++){

            int val= (int) ((0.4+Math.random())*20);
            for (int j=0;j<val;j++){
                int who= (int)(Math.random()*99);
                if(who!=i)
                Clients.addEdge(clientDataList.get(i),clientDataList.get(who));

            }

        }
        cohesionSocialNetwork();

    }

}
