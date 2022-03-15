import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args){
        List<Street> streetList=new LinkedList<>();
        Set<Intersect> interSet=new HashSet<>();

        //interSet= Stream.of("i1","i2","i3","i4","i5","i6","i7","i8","i9");
        //take a break


        streetList.sort(Comparator.comparing(Street::getLength));

    }
}
