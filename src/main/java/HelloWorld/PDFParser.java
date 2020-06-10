package HelloWorld;

import pl.edu.icm.cermine.ContentExtractor;
import java.io.FileInputStream;
import java.io.InputStream;
import org.jdom.Element;

public class PDFParser {

    private String dir;
    private String rep;

    public PDFParser(String dir) {
        this.dir = dir;
        this.rep = "../" + dir;
    }
    
    public PDFData getPDFMetadata(String pathname) throws Exception {
        ContentExtractor extractor = new ContentExtractor();
        InputStream inputStream = new FileInputStream(dir + pathname);
        extractor.setPDF(inputStream);
        Element result = extractor.getContentAsNLM();   
        PDFData pdf = new PDFData(getArticleNameMetadata(result), getJournalNameMetadata(result), getPublicationYearMetadata(result), rep + pathname);
        return pdf;
    }

    private String getArticleNameMetadata(Element nlm) {
        String articleName = nlm.getChild("front")
                            .getChild("article-meta")
                            .getChild("title-group")
                            .getChild("article-title")
                            .getValue();
        return articleName;
    }

    private String getJournalNameMetadata(Element nlm) {
        String journalName = nlm.getChild("front")
                            .getChild("journal-meta")
                            .getChild("journal-title-group")
                            .getChild("journal-title")
                            .getValue();
        return journalName;
    }

    private String getPublicationYearMetadata(Element nlm) {
        String publicationYear = nlm.getChild("front")
                                .getChild("article-meta")
                                .getChild("pub-date")
                                .getChild("year")
                                .getValue();
        return publicationYear;
    }
}