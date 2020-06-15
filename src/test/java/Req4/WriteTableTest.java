/**
 * 
 */
package Req4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author ricardo
 *
 */
public class WriteTableTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		WriteTable writeTable = new WriteTable();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Req4.WriteTable#draw(java.util.ArrayList)}.
	 * @throws IOException 
	 */
	@Test
	public final void testDraw() throws IOException {
		ArrayList<FileInfo> filesInfo = new ArrayList<>();
		for(int i = 0; i<5; i++) {
			FileInfo temp =  new FileInfo("20/5/16", "Ficheiro: " + i, "Tag: " + i , "Mensagem: " + i, "www.link_"+i+".com");
			filesInfo.add(temp);
		}
			File file =  new File("spreadTable.html");
			file.delete();
			assertFalse(file.exists());
			WriteTable.draw(filesInfo);
			WriteTable.draw(filesInfo);
			assertTrue(new File("spreadTable.html").exists());
	}

}
