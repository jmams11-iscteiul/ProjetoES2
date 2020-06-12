package CovidScientificDiscoveriesRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import pl.edu.icm.cermine.exception.AnalysisException;

/**
 * Discovers all the PDF files in a directory and adds them to a table in an HTML file.
 * Finds all the PDF files in a repository, then gets a PDFData object for each PDF file and finally appends the information in the PDFData object to a table in the HTML file.
 *
 * @author jmcrm
 * @since 2020-06-11
 */
public class Discovery {

    /**
     * The repository with the PDF Files.  
     */
    private String pdfRepository;
    /**
     * The name of the HTML file where the PDF data will be added.  
     */
    private String htmlFileName;

    /**
     * Constructs a Discovery program with the specified repository and HTMl file.
     * 
     * @param pdfRepository The repository with the PDF files.
     * @param htmlFileName The name of the HTML file.
     * @throws NullPointerException Exception thrown if the specified PDF repository or HTML file don't exist.
     */
    public Discovery(String pdfRepository, String htmlFileName) throws NullPointerException{
        this.pdfRepository = pdfRepository;
        this.htmlFileName = htmlFileName;
        repositoryExists();
    }


    /**
     * Checks if the Discovery PDF repository and HTML file exists, if one of these doesn't exists then a NullPointerException is thrown.
     * 
     * @throws NullPointerException Exception thrown if HTML file or PDF repository don't exist.
     */
    private void repositoryExists() {
        File f = new File(pdfRepository);
        if(!f.exists())
            throw new NullPointerException(pdfRepository + " repository");
        File f1 = new File(htmlFileName);
        if(!f1.exists())
            throw new NullPointerException(htmlFileName + " HTML file");
    }


    /** 
     * Given a list of PDFData, one for each file, appends all the information in each PDFData object to a table in a HTML file.
     * 
     * @param data List of PDFData to be added to the HTML file.
     * @throws IOException Exception thrown when the file isn't found.
     */
    public void appendHtmlString(List<PDFData> data) throws IOException {
        Document doc = getDocument(htmlFileName);
        org.jsoup.nodes.Element table = doc.select("table").first();

        for (int i = 0; i < data.size(); i++) {
            table.append("<tr>\n" + "<td><a href=\"" + data.get(i).getLocation() + "\">" + data.get(i).getArticleTitle()
                    + "</a></td>\n" + "<td>" + data.get(i).getJournalName() + "</td>\n" + "<td>"
                    + data.get(i).getPublicationYear() + "</td>\n" + "</tr>\n");
        }

        File newHtmlFile = new File(htmlFileName);
        FileUtils.writeStringToFile(newHtmlFile, doc.outerHtml(), "UTF-8");
    }

    
    /** 
     * Given an array with all the file names in the directory the method obtains the PDFData for each PDF file and it ignores any files that aren't PDF.
     * 
     * @param pathnames Array of the files in the directory. 
     * @return ListOfPDFData List of the PDFData from the PDF files in the PDF directory.
     * @throws IOException Exception thrown when the file isn't found.
     */
    public List<PDFData> getAllPDFMetadata(String[] pathnames) throws IOException {
        List<PDFData> data = new ArrayList<PDFData>();
        for (int i = 0; i < pathnames.length; i++) {
            if (pathnames[i].endsWith(".pdf") && !loadedInHTML(pathnames[i])) {
                try {
                    data.add(PDFParser.getPDFMetadata(pdfRepository + pathnames[i]));
                } catch (AnalysisException ae) {
                    System.out.println("File " + pathnames[i] + " wasn't successfully analised!");
                } 
            }
        }
        return data;
    }

    
    /** 
     * Given a file name checks if the relative path of the file is already in the HTML table.  
     * 
     * @param pathname The name of the PDF file.
     * @return boolean true if the file name isn't in the HTML file. 
     * @throws IOException Exception thrown when the file isn't found.
     */
    private boolean loadedInHTML(String pathname) throws IOException{
        Document doc = getDocument(htmlFileName);
        org.jsoup.nodes.Element table = doc.select("table").first();
        String content = table.outerHtml();
        if(content.contains(pdfRepository + pathname)) 
            return true;
        return false;
    }

    
    /** 
     * Gets all the files in the PDF repository
     * 
     * @return String[] The list of files in the given directory;
     */
    public String[] getFiles() {
        String[] files;
        File rep = new File(pdfRepository);
        files = rep.list();

        return files;
    }

    
    /** 
     * Given a HTML file name returns a Document with the HTML file.
     * 
     * @param file The HTML file name.
     * @return Document The HTML file.
     * @throws IOException Exception thrown when the file isn't found.
     */
    private Document getDocument(String file) throws IOException{
        File input = new File(file);
        Document doc = Jsoup.parse(input, "UTF-8");
        return doc;
    }

    
    public static void main(String[] args) {
        String pdfRep = "rep/";
        String htmlFile = "./table.html";

        try {
            Discovery d = new Discovery(pdfRep, htmlFile);
            String[] pathnames = d.getFiles();
            List<PDFData> data = d.getAllPDFMetadata(pathnames);
            d.appendHtmlString(data);
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage() + " doesn't exist!");
        } catch (IOException e) {
            System.out.println(htmlFile + " doesn't exist!");
            e.printStackTrace();
        } 
    }
}