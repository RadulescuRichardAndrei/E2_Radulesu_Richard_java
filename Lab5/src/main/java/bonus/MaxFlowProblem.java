package bonus;

import com.github.javafaker.Faker;
import lab5.Item;
import lab5.Main;
import org.jgrapht.Graph;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MaxFlowProblem {
    private List<String> Concepts;
    private List<Item> itemList;
    private Graph<String, DefaultEdge> graph;

    public MaxFlowProblem() {
        graph = new SimpleGraph<>(DefaultEdge.class);

    }

    public Graph<String, DefaultEdge> getGraph() {
        return graph;
    }

    public void setGraph(Graph<String, DefaultEdge> graph) {
        this.graph = graph;
    }

    public void generateProblem(int nbItems, int nbConcepts) {

        Concepts = new LinkedList<>();

        for (int i = 0; i < nbConcepts; i++) {
            Faker fk = new Faker();
            Concepts.add(fk.book().genre());
            graph.addVertex(Concepts.get(i));
        }

        itemList = new LinkedList<>();
        for (int i = 0; i < nbItems; i++) {
            Faker fk = new Faker();
            itemList.add(new Item(fk.book().title(), fk.book().title(), fk.internet().url(),
                    fk.book().author(), "site", String.valueOf((int) (Math.random() * (2022 - 1500) + 1500))));
            graph.addVertex(itemList.get(i).getId());
        }

        for (int i = 0; i < nbItems; i++)
            for (int j = 0; j < nbConcepts; j++)
                if (Math.random() <= 0.5) {
                    graph.addEdge(itemList.get(i).getId(), Concepts.get(j));
                }


    }

    public Set<DefaultEdge> maximumCardinalityMatching() {
        Set<String> Con = new HashSet<>(Concepts);
        Set<String> IL = new HashSet<>();
        itemList.stream().forEach(item -> IL.add(item.getId()));

        HopcroftKarpMaximumCardinalityBipartiteMatching<String, DefaultEdge> alg =
                new HopcroftKarpMaximumCardinalityBipartiteMatching<String, DefaultEdge>(graph, IL, Con);

        return alg.getMatching().getEdges();
    }

    public Set<DefaultEdge> minimumEdgeCovering() {

        Set<DefaultEdge> match = maximumCardinalityMatching();
        List<DefaultEdge> remainingEdges = graph.edgeSet().stream().filter(defaultEdge -> !match.contains(defaultEdge)).collect(Collectors.toList());
        List<String> usedNodes = new LinkedList<>();
        match.stream().forEach(defaultEdge -> usedNodes.add(graph.getEdgeSource(defaultEdge)));
        for (DefaultEdge edge : remainingEdges)
            if (!usedNodes.contains(graph.getEdgeSource(edge))) {
                match.add(edge);
                usedNodes.add(graph.getEdgeSource(edge));
            }

        return match;


    }

}
