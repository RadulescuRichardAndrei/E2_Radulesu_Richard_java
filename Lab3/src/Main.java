import java.util.*;

public class Main {

    public static void main(String[] args) {
        Network N = new Network();
        //N.generateNetwork();
        N.initialize();
        N.printNetworkCosts();
        System.out.println(N.listN);
        N.printIdentifiable();
        N.floydWasrshall();
        System.out.println(N.dikstra(0,N.listN.size()-2));


    }
}
