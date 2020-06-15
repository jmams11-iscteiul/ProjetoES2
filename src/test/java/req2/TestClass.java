package req2;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TestClass {

	// WebDriver instance
	static WebDriver driver;
	static JSONArray jarry;
	static JSONArray login;
	static JSONArray mailInfo;
	static MailTool mt;
	private static OutputRecorder outputRecorder;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe"); // C:\\ISCTE\\Aulas\\DriversSelenium\\geckodriver.exe
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		
		
		mt = new MailTool();
		firefoxOptions.setHeadless(true);
        firefoxOptions.setLogLevel(FirefoxDriverLogLevel.FATAL);
		outputRecorder = new OutputRecorder();

		driver = new FirefoxDriver(firefoxOptions);
		
		JSONParser parser = new JSONParser();
		Object obj2 = parser.parse(new FileReader("login.json"));
		login = (JSONArray) obj2;
		Object obj3 = parser.parse(new FileReader("testcase.json"));
		jarry = (JSONArray) obj3;
		Object obj4 = parser.parse(new FileReader("mailInfo.json"));
		mailInfo = (JSONArray) obj4;
//	    System.out.println(jarry.toString());

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		 driver.close();   // close the tab it has opened
//		 driver.quit();    // close the browser
	}

	@Test
	public void test() {
//		System.out.println("1");
		
		for (int i = 0; i < jarry.size(); i++) {
			JSONObject jo = (JSONObject) jarry.get(i);
//			if (jo.get("modo").equals("Repo")) {
//				testRepo((String)jo.get("nome"), (String)jo.get("link"));
//			}
			if (jo.get("modo").equals("WebPage")) {
				testWebPage((String) jo.get("nome"), (String) jo.get("link"));
			}
			if (jo.get("modo").equals("Form")) {
				testForm((String) jo.get("link"), (String) jo.get("linkTarget"), (JSONObject) jo.get("params"));
			}

//			System.out.println("1");
		}
		for (int i = 0; i < login.size(); i++) {
			JSONObject jo = (JSONObject) login.get(i);
			testLogin((String) jo.get("link"), (String) jo.get("username"), (String) jo.get("password"));
		}
		for (int i = 0; i < jarry.size(); i++) {
			JSONObject jo = (JSONObject) jarry.get(i);
			if (jo.get("modo").equals("Repo")) {
				testRepo((String) jo.get("nome"), (String) jo.get("link"));
			}
		}
		outputRecorder.endLog();
//		System.out.println("2");
//		driver.get("http://google.com");
		
//		System.out.println("3");
		JSONObject jb = (JSONObject) mailInfo.get(0);
		String username = (String)jb.get("username");
		String password = (String)jb.get("password");
		String to = (String)jb.get("to");
		mt.sendEmail(username, to, username, password);
//		driver.get("http://google.com");
//		
	}
	
	private void testWebPage(String nome, String link) {
		try { 
			driver.get(link);
			String status = driver.getTitle().equals(nome) ? "UP" : "DOWN";
			outputRecorder.addToLog("Web Page", link, nome, driver.getTitle(), status);
			if(status.equals("DOWN")) mt.addToErrors("The Web Page \"" + nome + "\" at " + link + " is down");
		} catch (WebDriverException e) {
			outputRecorder.addToLog("Web Page", link, nome, "Missing", "DOWN");
			mt.addToErrors("The Web Page \"" + nome + "\" at " + link + " is down");
		}
		
	}
	
	private void testForm(String link, String targetLink, JSONObject params) {
		try {
			boolean emailSens = false;
			JSONObject emailInfo = null;
			driver.get(link);
			WebElement elem = null;
			for (Object keyStr : params.keySet()) {
				Object keyvalue = params.get(keyStr);
				if (((String) keyStr).equals("emailSensitive")) {
					emailSens = true;
					emailInfo = (JSONObject) keyvalue;

				} else {
					elem = driver.findElement(By.name((String) keyStr));
					elem.sendKeys(keyvalue.toString());
				}
			}
			elem.submit();
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			outputRecorder.addToLog("Form", link, targetLink, driver.getCurrentUrl() , targetLink.equals(driver.getCurrentUrl())?"UP":"DOWN");
			if(!targetLink.equals(driver.getCurrentUrl())) mt.addToErrors("The form at" + link + " is down");
			if (emailSens) {
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				boolean st = MailTool.checkIf((String) emailInfo.get("emailHost"),
						(String) emailInfo.get("mailStoreType"), (String) emailInfo.get("emailAddress"),
						(String) emailInfo.get("emailPassword"), (String) emailInfo.get("emailTitle"));
				
				String message = st ? (String) emailInfo.get("emailTitle") : "DOES NOT MATCH";
				
				outputRecorder.addToLog("Email", link, (String)emailInfo.get("emailTitle"), message, st ? "UP" : "DOWN");
				if(!st) mt.addToErrors("The email functionality at " + link + " is down");
			}
		} catch (WebDriverException e) {
			outputRecorder.addToLog("Form", link, targetLink, link, "DOWN");
			mt.addToErrors("The form at " + link + " is down");
		}
	}

	private void testLogin(String link, String user, String pass) {
		driver.get(link);
		WebElement elem = null;
		elem = driver.findElement(By.name("log"));
		elem.sendKeys(user);
		elem = driver.findElement(By.name("pwd"));
		elem.sendKeys(pass);
		elem.submit();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outputRecorder.addToLog("Login", link, user, user, link.equals(driver.getCurrentUrl())? "DOWN":"UP");
		if(link.equals(driver.getCurrentUrl())) mt.addToErrors("The login with user \"" + user + "\" at " + link + " is down");
	}
	
	private void testRepo(String nome, String link) {
		try { 
			driver.get(link);
			System.out.println(driver.getTitle().equals(nome));
			String status = driver.getTitle().equals(nome) ? "UP" : "DOWN";
			outputRecorder.addToLog("Repository", link, nome, driver.getTitle(), status);
			if(status.equals("DOWN")) mt.addToErrors("The repository \"" + nome + "\" at " + link + " is down");
		} catch (WebDriverException e) {
			outputRecorder.addToLog("Repository", link, nome, "Missing", "DOWN");
			mt.addToErrors("The repository \"" + nome + "\" at " + link + " is down");
		}
		
	}

}