package pl.edu.pk.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pk.dao.reader.JBibTeXReaderData;
import pl.edu.pk.dao.reader.TxtReaderData;
import pl.edu.pk.dao.writer.TxtWriter;
import pl.edu.pk.dao.reader.ReaderData;
import pl.edu.pk.dao.reader.XmlReaderData;
import pl.edu.pk.dao.writer.Writer;
import pl.edu.pk.dao.writer.XmlWriter;
import pl.edu.pk.model.BookStore;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@Controller
public class WebController {

    private ReaderData readerData;
    private BookStore bookStore;
    private Writer writer;
    private File file;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        return "start";
    }
    public String TESTgit;
    @RequestMapping(value = "/uploaded", method= RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (extension.equals("xml")) {
            readerData = new XmlReaderData();
        } else if(extension.equals("txt")) {
            readerData = new TxtReaderData();
        } else if(extension.equals("bib")) {
            readerData = new JBibTeXReaderData();
        }

        File fileWithBookData = convert(file);
        this.bookStore = readerData.readData(fileWithBookData);

        model.addAttribute("books",bookStore.getBookData());

        return "uploaded";
    }


    public File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convFile;
    }

    @RequestMapping(value = "/converted", method = RequestMethod.GET)
    public void getFile(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String formatToConvert = request.getParameter("format");

        String fileName = System.currentTimeMillis() + "converted";
        if (formatToConvert.equals("xml")) {
            response.setContentType("application/xml");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xml");
            writer = new XmlWriter();
        } else if(formatToConvert.equals("txt")) {
            response.setContentType("application/txt");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName+".txt");
            writer = new TxtWriter();
        }


        file= writer.write(this.bookStore);

        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ServletOutputStream out = response.getOutputStream();
        IOUtils.copy(in, out);
        response.flushBuffer();
    }
}
