import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args){
        //List<Street> streetList=new LinkedList<>();
        //Set<Intersect> interSet=new HashSet<>();
        /*City myCity = new City();
        //interSet= Stream.of("i1","i2","i3","i4","i5","i6","i7","i8","i9");
        //take a break
        myCity.generateCity();
        System.out.println(myCity.getSetIn());
        System.out.println(myCity.getListSt());
        myCity.query(10);*/

        //streetList.sort(Comparator.comparing(Street::getLength));
        ProblemTSP Pb= new ProblemTSP();
        Pb.generateRandomProb(10,30);
        System.out.println(Pb.getCityTsp().getSetIn());
        System.out.println(Pb.getCityTsp().getListSt());
        //System.out.println(Pb.getCityTsp().getListSt());
        Pb.solveProb();


    }
}
