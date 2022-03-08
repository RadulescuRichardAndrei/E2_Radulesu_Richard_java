public class Room {
    private String name;
    private int capacity;

    /**
     * This is the constructor of the Room class
     * @param n a string containing the name of the Room
     * @param cap the capacity of the Room
     */
    public Room(String n, int cap) {
        name=n;
        capacity=cap;
    }

    /**
     * This method sets the capacity of the Room
     * @param cap the capacity of the Room
     */

    public void setCapacity(int cap) {
        capacity = cap;
    }
    /**
     * This method sets the name of the Room
     * @param nm the capacity of the Room
     */

    public void setName(String nm) {
        name = nm;
    }

    /**
     * This method returns the capacity of the Room
     */
    public int getCapacity() {
        return capacity;
    }
    /**
     * This method returns the name of the Room
     */
    public String getName() {
        return name;
    }
    /**
     * This method is overridden.
     * @return a string that contains the name and capacity of the Room.
     */
    @Override
    public String toString() {
        return "Room{" +
                " Name= " + name + " Capacity= " + String.valueOf(capacity) +
                '}';
    }
    /**
     * This method is overridden to check if two objects of type Room are the same
     * @param obj an object
     * @return true if the given object is of type Room and has the same name or false otherwise
     */
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
