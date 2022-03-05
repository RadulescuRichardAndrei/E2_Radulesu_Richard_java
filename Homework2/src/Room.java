public class Room {
    private String name;
    private int capacity;


    public Room(String n, int cap) {
        name=n;
        capacity=cap;
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

    @Override
    public String toString() {
        return "Room{" +
                " Name= " + name + " Capacity= " + String.valueOf(capacity) +
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
