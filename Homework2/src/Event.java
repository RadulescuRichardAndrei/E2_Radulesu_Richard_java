public class Event {
    private static String name;
    private static int size, start, end;

    public Event(String n, int si, int st, int en) {
        name=n;
        size=si;
        start=st;
        end=en;
    }

    public static String getName() {
        return name;
    }

    public static int getStart() {
        return start;
    }

    public static int getEnd() {
        return end;
    }

    public static int getSize() {
        return size;
    }

    public static void setName(String name) {
        Event.name = name;
    }

    public static void setStart(int start) {
        Event.start = start;
    }

    public static void setEnd(int end) {
        Event.end = end;
    }

    public static void setSize(int size) {
        Event.size = size;
    }

    @Override
    public String toString() {
        return "Event{ Name= " + name + " Size= " + String.valueOf(size) + " Starts at= " + String.valueOf(start)
                + " Ends at= " + String.valueOf(end) +
                "}";

    }

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
