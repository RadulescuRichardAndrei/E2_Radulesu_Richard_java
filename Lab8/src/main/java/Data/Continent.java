package Data;

import java.util.Objects;

public class Continent {
    private String id, name;

    public Continent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        Continent continent = (Continent) o;
        return Objects.equals(id, continent.id) && Objects.equals(name, continent.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Continent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
