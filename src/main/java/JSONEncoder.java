package main.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.html5.AddWebStorage;

public class JSONEncoder {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		JSONArray arr = new JSONArray();
		
		arr.add(addWebsite("Home", "http://localhost:80/home"));
		arr.add(addWebsite("Covid Scientific Discoveries", "http://localhost:80/csd"));
		arr.add(addWebsite("Covid Spread", "http://localhost:80/cs"));
		arr.add(addWebsite("Covid Queries", "http://localhost:80/cq"));
		arr.add(addWebsite("Covid Evolution", "http://localhost:80/ce"));
		arr.add(addWebsite("Covid Wiki", "http://localhost:80/cw"));
		arr.add(addWebsite("FAQ", "http://localhost:80/faq"));
		arr.add(addWebsite("Contact Us", "http://localhost:80/Us"));
		arr.add(addWebsite("Join Us", "http://localhost:80/ju"));
		arr.add(addWebsite("About Us", "http://localhost:80/au"));
		arr.add(addWebsite("Web Site Analytics", "http://localhost:80/wsa"));
		arr.add(addWebsite("Covid Scientific Discoveries Repository", "http://localhost:80/csdr"));

		arr.add(addWebsite("Página principal - Iscte – Instituto Universitário de Lisboa", "https://www.iscte-iul.pt/"));

		
		
		
		JSONObject form1 = new JSONObject();
		form1.put("username", "trmfa@iscte-iul.pt");
		form1.put("password", "");
		arr.add(addBasicForm("https://fenix.iscte-iul.pt/loginPage.jsp","https://fenix.iscte-iul.pt/loginPage.jsp", form1));
		
		
		
		JSONObject form2 = new JSONObject();
		form2.put("username", "trmfa@iscte-iul.pt");
		form2.put("password", "");
		form2.put("emailSensitive", emailSensitive("esii_testing_slave@sapo.pt", "ES2-2020-EIC1-11aaa", "pop.sapo.pt", "pop3", "Testing 1 2"));
		arr.add(addBasicForm("https://fenix.iscte-iul.pt/loginPage.jsp","https://fenix.iscte-iul.pt/loginPage.jsp", form2));

		
		//		
//		
//		JSONObject form2 = new JSONObject();
//		form2.put("name", "name");
//		form2.put("surname", "surname");
//		form2.put("affiliation", "aff");
//		form2.put("country", "country");
//		form2.put("email", "email@email.pt");
//		form2.put("koi", "koi");
//		arr.add(addBasicForm("http://localhost:80/ju", form2));

		
		//System.out.print(arr.toJSONString());
		JSONArray emailinfo = new JSONArray();
		JSONObject form3 = new JSONObject();
		form3.put("username", "esii_testing_slave@sapo.pt");
		form3.put("password", "ES2-2020-EIC1-11aaa");
		form3.put("to", "trmfa@iscte-iul.pt");
		emailinfo.add(form3);
		
		
		
		
		try {
		      FileWriter myWriter = new FileWriter("mailInfo.json");
		      myWriter.write(emailinfo.toJSONString());
		      myWriter.close();
		      System.out.println('\n'+"Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		
		
		
		
		try {
		      FileWriter myWriter = new FileWriter("testcase.json");
		      myWriter.write(arr.toJSONString());
		      myWriter.close();
		      System.out.println('\n'+"Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		
		JSONArray login = new JSONArray();
		JSONObject loginobject = new JSONObject();
		loginobject.put("username", "admin");
		loginobject.put("password", "admin");
		loginobject.put("link", "http://localhost/wp-login.php");
		login.add(loginobject);
		try {
		      FileWriter myWriter = new FileWriter("login.json");
		      myWriter.write(login.toJSONString());
		      myWriter.close();
		      System.out.println('\n'+"Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		
	}
	
	private static JSONObject addWebsite(String param1, String param2) {
		JSONObject obj = new JSONObject();
		obj.put("modo", "WebPage");
		obj.put("nome", param1);
		obj.put("link", param2);
		return obj;
	}
	
	private static JSONObject addBasicForm(String param1, String param2, JSONObject opts) {
		JSONObject obj = new JSONObject();
		obj.put("modo", "Form");
		obj.put("link", param1);
		obj.put("linkTarget", param2);
		obj.put("params", opts);
		return obj;
	}
	
	private static JSONObject emailSensitive(String emailAddress, String password, String host, String mailStoreType, String expectedTitle) {
		JSONObject obj = new JSONObject();
		obj.put("emailAddress", emailAddress);
		obj.put("emailPassword", password);
		obj.put("emailHost", host);
		obj.put("mailStoreType", mailStoreType);
		obj.put("emailTitle", expectedTitle);
		return obj;
	}
	
	
}

