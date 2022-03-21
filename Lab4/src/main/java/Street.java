import java.util.Objects;

public class Street {
    private Intersect inter1, inter2;
    private String name;
    private int length;

    public Street(Intersect inter1, Intersect inter2, String name, int length) {
        this.inter1 = inter1;
        this.inter2 = inter2;
        this.name = name;
        this.length = length;
    }

    public Street(Intersect inter1, Intersect inter2, String name) {
        this.inter1 = inter1;
        this.inter2 = inter2;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "Street{" +
                "inter1=" + inter1 +
                ", inter2=" + inter2 +
                ", name='" + name + '\'' +
                ", length=" + length +
                '}' + "\n";
    }
}
