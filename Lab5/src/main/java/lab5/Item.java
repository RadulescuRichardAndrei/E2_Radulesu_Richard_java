package lab5;

import java.util.Objects;

public class Item {
    private String id, title, location;
    private String author, type;
    private String year;

    public Item(String id, String title, String location, String author, String type, String year) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.author = author;
        this.type = type;
        this.year = year;
    }

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "comands.Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id) && title.equals(item.title) && location.equals(item.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
