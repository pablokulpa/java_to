package pl.edu.pk.dao.reader;

import pl.edu.pk.model.BookStore;

import java.io.File;

public interface ReaderData {

    BookStore readData(File file);
}
