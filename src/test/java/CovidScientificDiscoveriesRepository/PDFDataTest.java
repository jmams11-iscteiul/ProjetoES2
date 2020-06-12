package CovidScientificDiscoveriesRepository;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;    
    
public class PDFDataTest {

    private PDFData pdfData;

    @Before
    public void setUp(){
        pdfData = new PDFData("article-title", "journal-name", "publication-year", "location");
    }
        
    @Test
    public void testGetters() {
        assertEquals("article-title", pdfData.getArticleTitle());
        assertEquals("journal-name", pdfData.getJournalName());
        assertEquals("publication-year", pdfData.getPublicationYear());
        assertEquals("location", pdfData.getLocation());
    }

    @After
    public void tearDown(){
        pdfData = null;
    }
}
    