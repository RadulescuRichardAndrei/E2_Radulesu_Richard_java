package Data;

import java.util.Objects;

public class Country {
    private String id,name,code,continent;

    public Country(String id, String name, String code, String continent) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.continent = continent;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id) && Objects.equals(name, country.name) && Objects.equals(code, country.code) && Objects.equals(continent, country.continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, continent);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", continent='" + continent + '\'' +
                '}';
    }
}
