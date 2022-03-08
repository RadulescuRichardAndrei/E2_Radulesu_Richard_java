public class Rooms {
    private String name;
    private int capacity;
    private type roomType;

    public Rooms(String n, int cap, type rT) {
        name=n;
        capacity=cap;
        roomType = rT;
    }

    public void setCapacity(int cap) {
        capacity = cap;
    }

    public void setName(String nm) {
        name = nm;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public void setRoomType(type roomType) {
        this.roomType = roomType;
    }

    public type getroomType(){
        return roomType;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "roomType= " + roomType + " Name= " + name + " Capacity= " + String.valueOf(capacity) +
                '}';
    }
}
