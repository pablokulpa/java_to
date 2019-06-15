package pl.edu.pk.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(namespace = "de.vogella.xml.jaxb.model")
public class BookStore {

    private List<BookData> bookData;

    public BookStore() {
    }

    public List<BookData> getBookData() {
        return bookData;
    }

    public void setBookData(List<BookData> bookData) {
        this.bookData = bookData;
    }

    public BookStore(List<BookData> bookList) {
        this.bookData = bookList;
    }
}
