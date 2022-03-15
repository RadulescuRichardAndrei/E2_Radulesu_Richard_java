import java.util.*;

public class Main {

    public static void main(String[] args){
        List<Street> streetList=new LinkedList<>();
        Set<Intersect> interSet=new HashSet<>();

        streetList.sort(Comparator.comparing(Street::getName).thenComparing(Street::getLength));

    }
}
