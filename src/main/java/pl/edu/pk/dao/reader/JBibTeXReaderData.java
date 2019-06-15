package pl.edu.pk.dao.reader;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;
import org.jbibtex.ParseException;
import org.jbibtex.Value;
import pl.edu.pk.model.BookData;
import pl.edu.pk.model.BookStore;

import java.io.*;
import java.util.*;

public class JBibTeXReaderData implements ReaderData {

    @Override
    public BookStore readData(File file) {
        try {
            org.jbibtex.BibTeXParser bibtexParser = new org.jbibtex.BibTeXParser();
            Reader targetReader = new FileReader(file);
            org.jbibtex.BibTeXDatabase database = bibtexParser.parse(targetReader);
            targetReader.close();
            List<BookData> bookDataList = new ArrayList<>();

            for (BibTeXEntry bibTeXEntry :database.getEntries().values()) {

                Map<Key, Value> element = bibTeXEntry.getFields();

                bookDataList.add(new BookData(element.get(new Key("name")).toUserString(),
                        element.get(new Key("print")).toUserString(),
                        element.get(new Key("author")).toUserString(),
                        Integer.valueOf(element.get(new Key("age")).toUserString()),
                        Integer.valueOf(element.get(new Key("id")).toUserString())
                        ));
            }

            BookStore bookStore = new BookStore();
            bookStore.setBookData(bookDataList);

            return bookStore;

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
