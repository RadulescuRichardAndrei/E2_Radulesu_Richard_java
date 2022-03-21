import java.util.Objects;

public class Intersect {
    private String name;

    public Intersect(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersect intersect = (Intersect) o;
        return Objects.equals(name, intersect.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Intersect{" +
                "name='" + name + '\'' +
                '}';
    }
}
