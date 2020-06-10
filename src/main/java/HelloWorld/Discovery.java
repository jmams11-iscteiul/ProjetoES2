package HelloWorld;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Discovery {

    private static final String PDF_DIR = "rep/";
    private static final String HTML_PAGE_NAME = "table.html";
    private static final String HTML_FOLDER = "./";
    
    private PDFParser pdfParser;

    public Discovery() {
        this.pdfParser = new PDFParser(PDF_DIR);
    }

    public void appendHtmlString(List<PDFData> data) throws IOException {
        Document doc = getDocument();
        org.jsoup.nodes.Element table = doc.select("table").first();

        for (int i = 0; i < data.size(); i++) {
            table.append("<tr>\n" + "<td><a href=\"" + data.get(i).getLocation() + "\">" + data.get(i).getArticleTitle()
                    + "</a></td>\n" + "<td>" + data.get(i).getJournalName() + "</td>\n" + "<td>"
                    + data.get(i).getPublicationYear() + "</td>\n" + "</tr>\n");
        }

        File newHtmlFile = new File(HTML_FOLDER + HTML_PAGE_NAME);
        FileUtils.writeStringToFile(newHtmlFile, doc.outerHtml(), "UTF-8");

    }

    private List<PDFData> getAllPDFMetadata(String[] pathnames) throws Exception {
        List<PDFData> data = new ArrayList<PDFData>();
        for (int i = 0; i < pathnames.length; i++)
            if (pathnames[i].endsWith(".pdf") && !loadedInHTML(pathnames[i]))
                data.add(pdfParser.getPDFMetadata(pathnames[i]));
        return data;
    }

    private boolean loadedInHTML(String pathname) throws IOException {
        Document doc = getDocument();
        org.jsoup.nodes.Element table = doc.select("table").first();
        String content = table.outerHtml();
        if(content.contains(PDF_DIR + pathname)) 
            return true;
        return false;
    }

    private String[] getFiles(String path) {
        String[] files;
        File rep = new File(path);
        files = rep.list();

        return files;
    }

    private Document getDocument() throws IOException {
        File input = new File(HTML_FOLDER + HTML_PAGE_NAME);
        Document doc = Jsoup.parse(input, "UTF-8");
        return doc;
    }

    public static void main(String[] args) {
        Discovery d = new Discovery();

        String[] pathnames = d.getFiles(PDF_DIR);

        try {
            List<PDFData> data = d.getAllPDFMetadata(pathnames);
            d.appendHtmlString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}