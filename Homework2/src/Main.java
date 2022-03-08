import java.util.*;

public class Main {
    /**
     * This method uses a greedy algorithm (events that end earlier takes priority and as a second criteria the size of the event takes priority) to assigns a room for as many events given as parameters.
     *
     * @param listE a arrayList of the object type Event
     * @param listR a arrayList of the object type Room
     */
    public static void assignRoom(ArrayList <Event> listE, ArrayList <Room> listR){

    int[] lastUsed = new int[listR.size()];
        listE.sort(Comparator.comparing(Event::getEnd).thenComparing(Event::getSize));
        listR.sort(Comparator.comparing(Room::getCapacity));

        for(int i=0; i< listE.size(); i++){
            int j;
            for (j=0; j < listR.size(); j++)
            if(listE.get(i).getSize()<=listR.get(j).getCapacity() && listE.get(i).getStart()>=lastUsed[j]){
                System.out.println(listR.get(j).getName() + ": " + listE.get(i).getName());
                lastUsed[j]=listE.get(i).getEnd();
                break;
            }

        if(j==listR.size())
            System.out.println(listE.get(i).getName() + " not assigned");
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
    listE.add(new Event("L4",70,12,13));

    listR.add(new Laboratory("401",30));
    listR.add(new Laboratory("403",30));
    listR.add(new Laboratory("405",30));
    listR.add(new LectureHall("309",100));


    assignRoom(listE,listR);


    }
}
