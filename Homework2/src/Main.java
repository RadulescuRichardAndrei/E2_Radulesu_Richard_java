import java.util.*;

public class Main {


    public static void assignRoom(ArrayList <Event> listE, ArrayList <Room> listR){
    int[] lastUsed = new int[listR.size()];
    int j;

    for(int i=0; i< listE.size(); i++){
        for (j=0; j < listR.size(); j++)
            if(listE.get(i).getSize()<=listR.get(j).getCapacity() && listE.get(i).getStart()>=lastUsed[j]){
                System.out.println(listR.get(j).getName() + ": " + listE.get(i).getName());
                lastUsed[j]=listE.get(i).getEnd();
                break;
            }

        if(j==listR.size())
            System.out.println(listE.get(i).getName() + "not assigned");
        }


    }
    public static void main(String[] args){
    ArrayList <Event> listE = new ArrayList<Event>();
    ArrayList <Room> listR = new ArrayList<Room>();


    listE.add(new Event("C1",100,8,10));
    listE.add(new Event("L1",30,8,10));
    listE.add(new Event("L2",30,8,10));
    listE.add(new Event("C2",100,10,12));
    listE.add(new Event("L3",30,10,12));

    listR.add(new Laboratory("401",30));
    listR.add(new Laboratory("403",30));
    listR.add(new Laboratory("405",30));
    listR.add(new LectureHall("309",100));



    listE.sort(Comparator.comparing(Event::getEnd).thenComparing(Event::getSize));
    listR.sort(Comparator.comparing(Room::getCapacity));

        System.out.println(listE);
        System.out.println(listR);
        assignRoom(listE,listR);


    }
}
