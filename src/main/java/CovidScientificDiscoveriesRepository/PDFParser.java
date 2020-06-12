package CovidScientificDiscoveriesRepository;

import pl.edu.icm.cermine.ContentExtractor;
import pl.edu.icm.cermine.exception.AnalysisException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.jdom.Element;

/**
 * Program that converts a PDF file into a PDFData object. 
 * Given the file path the PDF metadata is extracted, with this data an PDFData object is created with the PDF Article Name, Journal Name and Publication Year.
 *
 * @author jmcrm
 * @since 2020-06-11
 */
public class PDFParser {

    /**
     * Given an PDF file path this method extracts the file metadata, parses it and then creates a PDFData object to keep the important data and returns it.
     * 
     * @param pathname The PDF File path.
     * @return PDFData The PDFData object which contains the Article Name, Journal Name, Publication Year and File Location.
     * @throws AnalysisException Exception thrown when there is a problem creating a PDF content extractor or extracting the metadata.
     * @throws IOException Exception thrown when there is a problem getting the PDF file from the PDF directory.
     */
    public static PDFData getPDFMetadata(String pathname) throws AnalysisException, IOException {
        ContentExtractor extractor = new ContentExtractor();
        InputStream inputStream = new FileInputStream(pathname);
        extractor.setPDF(inputStream);
        Element result = extractor.getContentAsNLM();   
        PDFData pdf = new PDFData(getArticleTitleMetadata(result), getJournalNameMetadata(result), getPublicationYearMetadata(result), "../" + pathname);
        return pdf;
    }

    
    /** 
     * Given an XML element containing the PDF metadata in NLM format this method returns the content in the article-title elemnt, which parent is the title-group element.
     * 
     * @param nlm The PDF metadata in NLM format.
     * @return String The PDF Article Title.
     */
    private static String getArticleTitleMetadata(Element nlm) {
        String articleTitle = nlm.getChild("front")
                            .getChild("article-meta")
                            .getChild("title-group")
                            .getChild("article-title")
                            .getValue();
        return articleTitle;
    }

    
    /** 
     * Given an XML element containing the PDF metadata in NLM format this method returns the content in the journal-title element.
     * 
     * @param nlm The PDF metadata in NLM format.
     * @return String The PDF Journal Name.
     */
    private static String getJournalNameMetadata(Element nlm) {
        String journalName = nlm.getChild("front")
                            .getChild("journal-meta")
                            .getChild("journal-title-group")
                            .getChild("journal-title")
                            .getValue();
        return journalName;
    }

    
    /** 
     * Given an XML element containing the PDF metadata in NLM format this method returns the content in the year elemnt, which parent is the pub-date element.
     * 
     * @param nlm The PDF metadata in NLM format.
     * @return String The PDF Publication Year.
     */
    private static String getPublicationYearMetadata(Element nlm) {
        String publicationYear = nlm.getChild("front")
                                .getChild("article-meta")
                                .getChild("pub-date")
                                .getChild("year")
                                .getValue();
        return publicationYear;
    }
}