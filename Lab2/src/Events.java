public class Events {
    private String name;
    private int size, start, end;

    public Events(String n, int si, int st, int en) {
        name=n;
        size=si;
        start=st;
        end=en;
    }

    public String getName() {
        return name;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getSize() {
        return size;
    }

    public void setName(String nm) {
        name = nm;
    }

    public void setStart(int st) {
        start = st;
    }

    public void setEnd(int en) {
        end = en;
    }

    public void setSize(int sz) {
        size = sz;
    }

    @Override
    public String toString() {
        return "Events{ Name= " + name + " Size= " + String.valueOf(size) + " Starts at= " + String.valueOf(start)
                + " Ends at= " + String.valueOf(end) +
                "}";

    }
}
