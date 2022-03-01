public class Rooms {
    private static String name;
    private static int capacity;
    private static type roomType;

    public Rooms(String n, int cap, type rT) {
        name=n;
        capacity=cap;
        roomType = rT;
    }

    public static void setCapacity(int capacity) {
        Rooms.capacity = capacity;
    }

    public static void setName(String name) {
        Rooms.name = name;
    }

    public static int getCapacity() {
        return capacity;
    }

    public static String getName() {
        return name;
    }

    public void setRoomType(type roomType) {
        this.roomType = roomType;
    }

    public static type getroomType(){
        return roomType;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "roomType= " + roomType + " Name= " + name + " Capacity= " + String.valueOf(capacity) +
                '}';
    }
}
