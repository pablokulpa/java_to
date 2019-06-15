package pl.edu.pk.dao.reader;

import pl.edu.pk.model.BookData;
import pl.edu.pk.model.BookStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtReaderData implements ReaderData {

    @Override
    public BookStore readData(File file) {
        try {
            Scanner scanner = new Scanner(file);
            BookStore bookStore = new BookStore();
            List<BookData> bookDataList = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String[] bookData = scanner.nextLine().split(",");
                bookDataList.add(new BookData(bookData[0],bookData[1],bookData[2],Integer.parseInt(bookData[3])
                        ,Integer.parseInt(bookData[4])));
            }
            bookStore.setBookData(bookDataList);
            return bookStore;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }
}
