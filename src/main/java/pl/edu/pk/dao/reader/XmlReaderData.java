package pl.edu.pk.dao.reader;

import pl.edu.pk.model.BookStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlReaderData implements ReaderData {
    @Override
    public BookStore readData(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(BookStore.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (BookStore) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
