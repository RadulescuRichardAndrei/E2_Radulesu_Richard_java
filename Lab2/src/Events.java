public class Events {
    private static String name;
    private static int size, start, end;

    public Events(String n, int si, int st, int en) {
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
        Events.name = name;
    }

    public static void setStart(int start) {
        Events.start = start;
    }

    public static void setEnd(int end) {
        Events.end = end;
    }

    public static void setSize(int size) {
        Events.size = size;
    }

    @Override
    public String toString() {
        return "Events{ Name= " + name + " Size= " + String.valueOf(size) + " Starts at= " + String.valueOf(start)
                + " Ends at= " + String.valueOf(end) +
                "}";

    }
}
