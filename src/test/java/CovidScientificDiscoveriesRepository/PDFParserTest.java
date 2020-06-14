package CovidScientificDiscoveriesRepository;

import org.junit.Test;

import pl.edu.icm.cermine.exception.AnalysisException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;

public class PDFParserTest {

    private PDFData testData;
    private String existentPath = "rep/178-1-53.pdf";
    private String nonExistentPath = "rep/1111aaa.pdf";

    @Before
    public void setUp() {
        try {
            testData = PDFParser.getPDFMetadata(existentPath);
        } catch (AnalysisException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    @Test
    public void testParser() {
        boolean error = false;
        try {
            PDFParser.getPDFMetadata(nonExistentPath);
        } catch (AnalysisException e) {
            e.printStackTrace();
        } catch (IOException e) {
            error = true;
            e.printStackTrace();
        }
        
        assertTrue(error);

        assertEquals("Pandemic versus Epidemic Influenza Mortality: A Pattern of Changing Age Distribution", testData.getArticleTitle());
        assertEquals("The Journal of Infectious Diseases", testData.getJournalName());
        assertEquals("1998", testData.getPublicationYear());
        assertEquals(existentPath, testData.getLocation());
    }

    public void tearDown() {
        testData = null;
        existentPath = "";
        nonExistentPath = "";
    }
}
    