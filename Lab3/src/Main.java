import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        Network N = new Network();
        N.listN = new ArrayList<Node>();
        N.listN.add(new Computer("ComputerA","84-3A-4B-C8-E9-00","Romania","127.0.0.1",240));
        N.listN.add(new Router("RouterA","84-3A-4B-C8-E9-00","Romania","127.0.0.5"));
        N.listN.add(new Switch("SwitchA","84-3A-4B-C8-E9-00","Romania"));
        N.listN.add(new Switch("SwitchB","84-3A-4B-C8-E9-00","Ungaria"));
        N.listN.add(new Router("RouterB","84-3A-4B-C8-E9-00","Bulgaria","100.1.5.7"));
        N.listN.add(new Computer("ComputerB","84-3A-4B-C8-E9-00","Bulgaria","100.1.5.6",150));

        System.out.println(N.listN);
    }
}
