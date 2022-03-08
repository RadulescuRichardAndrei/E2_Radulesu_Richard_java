public class Main {

    public static void main(String[] args){
        Events C1= new Events("C1", 100, 8, 10);
        Events C2= new Events("C2", 100, 10, 12);
        Events L1= new Events("L1", 30, 8, 10);
        Events L2= new Events("L2", 30, 8, 10);
        Events L3= new Events("L3", 30, 10, 12);
        Rooms r401= new Rooms("401", 30, type.Lab);
        Rooms r403= new Rooms("403", 30, type.Lab);
        Rooms r405= new Rooms("405", 30, type.Lab);
        Rooms r309= new Rooms("309",100, type.Lecture_hall);

        System.out.println(C1.toString());
        System.out.println(C2.toString());
        System.out.println(r401.toString());
        r401.setCapacity(35);
        System.out.println(r401.toString());
    }
}
