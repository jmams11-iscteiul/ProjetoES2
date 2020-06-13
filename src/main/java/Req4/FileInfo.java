package Req4;

public class FileInfo {
    private String date;
    private String fileName;
    private String name;
    private String message;
    private String link;

    FileInfo(){}
    FileInfo(String date, String fileName, String name, String message, String link){
        this.date = date;
        this.fileName = fileName;
        this.name = name;
        this.message = message;
        this.link = link;
        }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }



    
}