package CovidScientificDiscoveriesRepository;

/**
* Data structure that stores the PDF article name, journal name, year of publication and the PDF relative path. 
*
* @author  jmcrm 
* @since   2020-06-11
*/
public class PDFData {

    /**
     * The PDF Article Title.
     */
    private String articleTitle;

    /**
     * The PDF Journal Name.
     */
    private String journalName;

    /**
     * The PDF Publication Year.
     */
    private String publicationYear;

    /**
     * The PDF Relative Path.
     */
    private String location;

    /**
     * Creates a PDFData with the specified Article Title, Journal Name, Publication Name and the Relative Path.
     * 
     * @param articleTitle The PDF Article Title.
     * @param journalName The PDF Journal Name.
     * @param publicationYear The PDF Publication Year.
     * @param location The PDF Relative Path.
     */
    public PDFData(String articleTitle, String journalName, String publicationYear, String location) {
        this.articleTitle = articleTitle;
        this.journalName = journalName;
        this.publicationYear = publicationYear;
        this.location = location;
    }

    
    /** 
     * Gets the PDF Article Title.
     * @return PDF Article Title.
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    
    /**
     * Gets the PDF Journal Name.
     * @return Gets the PDF Journal Name.
     */
    public String getJournalName() {
        return journalName;
    }

    
    /** 
     * Gets the PDF Publication Year.
     * @return Gets the PDF Publication Year.
     */
    public String getPublicationYear() {
        return publicationYear;
    }

    
    /** 
     * Gets the PDF relative path.
     * @return Gets the PDF relative path.
     */
    public String getLocation() {
        return location;
    }
}