package HelloWorld;

public class PDFData {

    private String articleTitle;
    private String journalName;
    private String publicationYear;
    private String location;

    public PDFData(String articleTitle, String journalName, String publicationYear, String location) {
        this.articleTitle = articleTitle;
        this.journalName = journalName;
        this.publicationYear = publicationYear;
        this.location = location;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getJournalName() {
        return journalName;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public String getLocation() {
        return location;
    }
}