package CovidScientificDiscoveriesRepository;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

public class DiscoveryTest {

    private Discovery discGoodArguments;
    private Discovery discBadRepository;
    private Discovery discBadHtmlFile;

    private File htmlFile;
    private File pdfRep;
    private File nonPdfFile;
    private File htmlFileStructure = new File("./src/test/java/CovidScientificDiscoveriesRepository/htmlFileStructure.html");

    @Before
    public void setUp() {
        htmlFile = new File("./tableTest.html");

        FileChannel src = null;
        FileChannel dest = null;
        try {
            htmlFile.createNewFile();
            src = new FileInputStream(htmlFileStructure).getChannel();
            dest = new FileOutputStream(htmlFile).getChannel();
            dest.transferFrom(src, 0, src.size());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                src.close();
                dest.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        pdfRep = new File("rep");
        nonPdfFile = new File("./rep/pdf.txt");
        discGoodArguments = new Discovery(pdfRep.getName() + "/", htmlFile.getName());
    }

    @Test
    public void testConstructor() {
        try {
            discBadRepository = new Discovery("nonExistingRep/", htmlFile.getName());
        } catch (NullPointerException e) {
            assertEquals("nonExistingRep/ repository", e.getMessage());
        }
        try {
            discBadHtmlFile = new Discovery("./" + pdfRep.getName() + "/", "nonExistingHtml.html");
        } catch (NullPointerException e) {
            assertEquals("nonExistingHtml.html HTML file", e.getMessage());
        }
    }

    @Test
    public void testGetAllPDFMetadataAndAppendHTML() {
        String[] existingFiles = { "178-1-53.pdf", nonPdfFile.getName() };
        String[] nonExistingFiles = { "covid.pdf" };
        List<PDFData> result = new ArrayList<PDFData>();

        //Get files from rep
        String[] filesFromRep = discGoodArguments.getFiles();
        assertEquals(4, filesFromRep.length);
        
        //Testing getting PDFData using existingFiles
        try {
            result = discGoodArguments.getAllPDFMetadata(existingFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(1, result.size());

        //Testing the data extracted from the previous instruction
        PDFData pdfData = result.get(0);
        assertEquals("Pandemic versus Epidemic Influenza Mortality: A Pattern of Changing Age Distribution", pdfData.getArticleTitle());
        assertEquals("The Journal of Infectious Diseases", pdfData.getJournalName());
        assertEquals("1998", pdfData.getPublicationYear());
        assertEquals("../" + pdfRep.getName() + "/" + existingFiles[0], pdfData.getLocation());

        // Appending the Data 
        try {
            discGoodArguments.appendHtmlString(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        result.clear();

        //Trying to extract the data from the pdf, but is unable because its already in the HTML file, so the PDF's metadata won't be extracted again
        try {
            result = discGoodArguments.getAllPDFMetadata(existingFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(0, result.size());

        result.clear();



        //Testing getting the data from non existing files
        try {
            result = discGoodArguments.getAllPDFMetadata(nonExistingFiles);
        } catch (IOException e) {
            assertEquals(0, result.size());
        }
    }

    @After
    public void tearDown() {
        discGoodArguments = null;
        discBadRepository = null;
        discBadHtmlFile = null;
        nonPdfFile.delete();
        htmlFile.delete();
    }
}
    