import java.util.Objects;

public class Street {
    private Intersect inter1, inter2;

    public Intersect getInter1() {
        return inter1;
    }

    public void setInter1(Intersect inter1) {
        this.inter1 = inter1;
    }

    public Intersect getInter2() {
        return inter2;
    }

    public void setInter2(Intersect inter2) {
        this.inter2 = inter2;
    }

    private String name;
    private int length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }


    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Street street = (Street) o;
        return length == street.length && name.equals(street.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, length);
    }
}
