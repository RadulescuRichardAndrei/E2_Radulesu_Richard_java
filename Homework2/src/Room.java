public class Room {
    private static String name;
    private static int capacity;


    public Room(String n, int cap) {
        name=n;
        capacity=cap;
    }

    public static void setCapacity(int capacity) {
        Room.capacity = capacity;
    }

    public static void setName(String name) {
        Room.name = name;
    }

    public static int getCapacity() {
        return capacity;
    }

    public static String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomType= " + roomType + " Name= " + name + " Capacity= " + String.valueOf(capacity) +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if( obj == this){
            return true;
        }
        if( !(obj instanceof Room)){
            return false;
        }
        Room r = (Room) obj;
        return r.getName().equals(this.getName());
    }
}
