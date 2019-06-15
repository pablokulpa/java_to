package pl.edu.pk.dao.writer;

import pl.edu.pk.model.BookData;
import pl.edu.pk.model.BookStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class TxtWriter implements Writer {
    @Override
    public File write(BookStore bookStore) {
        String text = "";
        Iterator<BookData> iterator = bookStore.getBookData().iterator();

        while (iterator.hasNext()) {
            BookData bookData = iterator.next();
            if(iterator.hasNext()) {
                text += bookData.getName() + "," + bookData.getPrint() + "," + bookData.getAuthor() + "," + bookData.getAge() +  "," + bookData.getId() + "\n";
            } else {
                text += bookData.getName() + "," + bookData.getPrint() + "," + bookData.getAuthor() + "," + bookData.getAge() +  "," + bookData.getId();

            }
        }

        System.out.println(text);
        try {

            File file = File.createTempFile("data","txt");
            PrintWriter out = new PrintWriter(file);
            out.write(text);

            out.close();
            return file;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
