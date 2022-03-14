import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class Network {
    public ArrayList<Node> listN;

    public void printIdentifiable(){
        ArrayList<Identifiable> listNIdent = new ArrayList<Identifiable>();
        for(int i=0;i<listN.size();i++){
            if(listN.get(i) instanceof Identifiable){
                    listNIdent.add((Identifiable) listN.get(i));
            }
        }
        listNIdent.sort(Comparator.comparing(Identifiable::getIp));

        System.out.println(listNIdent);
    }
    public void printNetworkCosts(){
        for(int i=0;i<listN.size();i++)
        if(Objects.nonNull(listN.get(i).getCost()))
        {
            System.out.println("From " + listN.get(i).getName() + " to: ");
            System.out.println(listN.get(i).getCost());
        }

    }

}
