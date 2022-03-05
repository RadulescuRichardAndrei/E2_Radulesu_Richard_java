public class Event {
    private String name;
    private int size, start, end;

    public Event(String n, int si, int st, int en) {
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

    public void setName(String n) {
        name = n;
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
