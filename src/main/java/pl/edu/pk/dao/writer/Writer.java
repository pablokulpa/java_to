package pl.edu.pk.dao.writer;

import pl.edu.pk.model.BookStore;

import java.io.File;

public interface Writer {

    File write(BookStore bookStore);
}
