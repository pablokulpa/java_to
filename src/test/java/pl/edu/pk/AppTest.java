package pl.edu.pk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.common.io.Files;
import org.junit.Test;
import pl.edu.pk.dao.reader.JBibTeXReaderData;
import pl.edu.pk.dao.reader.ReaderData;
import pl.edu.pk.dao.reader.TxtReaderData;
import pl.edu.pk.dao.reader.XmlReaderData;
import pl.edu.pk.dao.writer.TxtWriter;
import pl.edu.pk.dao.writer.Writer;
import pl.edu.pk.dao.writer.XmlWriter;
import pl.edu.pk.model.BookStore;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void readerTxtTest() {
        ReaderData txtReaderData = new TxtReaderData();
        File file = new File("src/test/resources/txt_data.txt");
        BookStore bookStore = txtReaderData.readData(file);
        assertEquals(3,bookStore.getBookData().get(0).getAge());
    }

    @Test
    public void readerXmlTest() {
        ReaderData xmlReaderData = new XmlReaderData();
        File file = new File("src/test/resources/xml_data.xml");
        BookStore bookStore = xmlReaderData.readData(file);
        assertEquals("ala",bookStore.getBookData().get(0).getName());
    }

    @Test
    public void writerTxtTest() throws IOException {
        ReaderData txtReaderData = new TxtReaderData();
        File file = new File("src/test/resources/txt_data.txt");
        BookStore bookStore = txtReaderData.readData(file);
        Writer txtWriter = new TxtWriter();
        File newfile = txtWriter.write(bookStore);
        assertTrue(Files.equal(file,newfile));
    }

    @Test
    public void writerXmlTest() throws IOException {
        ReaderData xmlReaderData = new XmlReaderData();
        File file = new File("src/test/resources/xml_data.xml");
        BookStore bookStore = xmlReaderData.readData(file);
        Writer xmlWriter = new XmlWriter();
        File newfile = xmlWriter.write(bookStore);
        assertTrue(Files.equal(file,newfile));
    }

    @Test
    public void testo() {
        File file = new File("src/test/resources/biografia.bib");
        ReaderData reader = new JBibTeXReaderData();
        reader.readData(file);

    }
}
