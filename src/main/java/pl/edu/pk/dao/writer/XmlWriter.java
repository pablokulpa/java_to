package pl.edu.pk.dao.writer;

import pl.edu.pk.dao.writer.Writer;
import pl.edu.pk.model.BookStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlWriter implements Writer {

    @Override
    public File write(BookStore bookStore) {
        try {
            File file = File.createTempFile("data","txt");
            JAXBContext context = JAXBContext.newInstance(BookStore.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(bookStore, file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
