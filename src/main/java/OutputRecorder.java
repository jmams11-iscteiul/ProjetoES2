package main.java;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OutputRecorder {
	private static final String filename = "/var/www/html/requisito2.html";
	private static FileWriter myWriter;
	public OutputRecorder() {
		try {
			myWriter = new FileWriter(filename);
			myWriter.write(" ");
			myWriter.close();
			startLog();
			myWriter = new FileWriter(filename, true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void startLog() throws IOException {
		InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream("htmlStartingTemplate.txt");
	        os = new FileOutputStream(filename);
	        byte[] buffer = new byte[4096];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	public void addToLog(String tipo, String link, String esperado, String obtido, String status) {
		try {
		      myWriter.write("<tr>" + 
		    		"<th>" + tipo + "</th>"+
		    		"<th>" + link + "</th>"  + 
		      		"<th>" + esperado + "</th>" + 
		      		"<th>" + obtido + "</th>" + 
		      		"<th>" + status + "</th>" + 
		      		"</tr>");
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}
	
	public void endLog() {
		try {
			myWriter.write("</table></body></html>");
			myWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
