package pl.edu.pk.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookData {

    private String name;
    private String print;
    private String author;
    private int age;
    private int id;

    public BookData(String name, String print, String author, int age, int id) {
        this.name = name;
        this.print = print;
        this.author = author;
        this.age = age;
        this.id = id;
    }

    public BookData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookData{" +
                "name='" + name + '\'' +
                ", print=" + print +
                ", author=" + author +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
