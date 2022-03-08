public class Event {
    private String name;
    private int size, start, end;
    /**
     * This is the constructor of the Event class
     * @param n a string with the name of the event
     * @param si the size of the event
     * @param st the starting time of the event
     * @param en the ending time of the event
     *
    * */
    public Event(String n, int si, int st, int en) {
        name=n;
        size=si;
        start=st;
        end=en;
    }
    /**
     * This method returns the name of the event
     * */
    public String getName() {
        return name;
    }
    /**
     * This method returns the starting time of the event
     * */
    public int getStart() {
        return start;
    }
    /**
     * This method returns the ending time of the event
     * */
    public int getEnd() {
        return end;
    }
    /**
     * This method returns the size of the event
     * */
    public int getSize() {
        return size;
    }
    /**
     * This method sets the name of the event with the given parameter
     * @param n a string with the name of the event
     * */
    public void setName(String n) {
        name = n;
    }
    /**
     * This method sets the starting time of the event with the given parameter
     * @param st the starting time of the event
     * */
    public void setStart(int st) {
        start = st;
    }
    /**
     * This method sets the ending time of the event with the given parameter
     * @param en the ending time of the event
     * */
    public void setEnd(int en) {
        end = en;
    }
    /**
     * This method sets the size of the event with the given parameter
     * @param sz the size of the event
     * */
    public void setSize(int sz) {
        size = sz;
    }

    /**
     * This method is overridden.
     * @return a string that contains the name, size, starting and ending time of the event.
     */

    @Override
    public String toString() {
        return "Event{ Name= " + name + " Size= " + String.valueOf(size) + " Starts at= " + String.valueOf(start)
                + " Ends at= " + String.valueOf(end) +
                "}";

    }
    /**
     * This method is overridden to check if two objects of type Event are the same
     * @param obj an object
     * @return true if the given object is of type Event and has the same name or false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if( obj == this){
            return true;
        }
        if( !(obj instanceof Room)){
            return false;
        }
        Event e =(Event) obj;

        return e.getName().equals(this.getName());

    }
}
