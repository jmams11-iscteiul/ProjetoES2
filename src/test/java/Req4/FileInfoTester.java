/**
 * 
 */
package Req4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author ricar
 *
 */
public class FileInfoTester {
	private static FileInfo fileInfo;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fileInfo =  new FileInfo();
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
	 * Test method for {@link Req4.FileInfo#FileInfo()}.
	 */
	@Test
	public final void testFileInfo() {
		FileInfo fi = new FileInfo();
		assertNotNull(fi);
		assertTrue(fi instanceof FileInfo);
	}

	/**
	 * Test method for {@link Req4.FileInfo#FileInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testFileInfoStringStringStringStringString() {
		String date = "20/06/15";
		String fileName = "tag.txt";
		String tag ="tag";
		String message = "Esta tag serve paraa testar o programa";
		String link = "http://www.teste.pt";
		FileInfo fi =  new FileInfo(date, fileName, tag, message, link);
		assertEquals(date,fi.getDate());
		assertEquals(fileName,fi.getFileName());
		assertEquals(tag,fi.getTag());
		assertEquals(message,fi.getMessage());
		assertEquals(link,fi.getLink());
		assertTrue(fi instanceof FileInfo);
	}

	/**
	 * Test method for {@link Req4.FileInfo#getDate()}.
	 */
	@Test
	public final void testGetDate() {
		fileInfo.setDate("24/09/1999");
		assertNotNull(fileInfo.getDate());
		assertEquals("24/09/1999",fileInfo.getDate());
	}

	/**
	 * Test method for {@link Req4.FileInfo#setDate(java.lang.String)}.
	 */
	@Test
	public final void testSetDate() {
		FileInfo teste =  new FileInfo();
		teste.setDate("5/9/2005");
		assertEquals("5/9/2005", teste.getDate());
	}

	/**
	 * Test method for {@link Req4.FileInfo#getFileName()}.
	 */
	@Test
	public final void testGetFileName() {
		fileInfo.setFileName("Ficheiro");
		assertNotNull(fileInfo.getFileName());
		assertEquals("Ficheiro",fileInfo.getFileName());
	}

	/**
	 * Test method for {@link Req4.FileInfo#setFileName(java.lang.String)}.
	 */
	@Test
	public final void testSetFileName() {
		FileInfo teste =  new FileInfo();
		teste.setFileName("Ficheiro teste");
		assertEquals("Ficheiro teste", teste.getFileName());
		
	}

	/**
	 * Test method for {@link Req4.FileInfo#getTag()}.
	 */
	@Test
	public final void testGetTag() {
		fileInfo.setTag("Tag123");
		assertNotNull(fileInfo.getTag());
		assertEquals("Tag123",fileInfo.getTag());
	}

	/**
	 * Test method for {@link Req4.FileInfo#setTag(java.lang.String)}.
	 */
	@Test
	public final void testSetTag() {
		FileInfo teste =  new FileInfo();
		teste.setTag("Tag_teste");
		assertEquals("Tag_teste", teste.getTag());
	}

	/**
	 * Test method for {@link Req4.FileInfo#getMessage()}.
	 */
	@Test
	public final void testGetMessage() {
		fileInfo.setMessage("Mensagem de teste");
		assertEquals("Mensagem de teste",fileInfo.getMessage());
	}

	/**
	 * Test method for {@link Req4.FileInfo#setMessage(java.lang.String)}.
	 */
	@Test
	public final void testSetMessage() {
		FileInfo teste =  new FileInfo();
		teste.setMessage("Mensagem de Teste");
		assertEquals("Mensagem de Teste", teste.getMessage());
	}

	/**
	 * Test method for {@link Req4.FileInfo#getLink()}.
	 */
	@Test
	public final void testGetLink() {
		fileInfo.setLink("http://www.google.pt");
		assertEquals("http://www.google.pt",fileInfo.getLink());
	}

	/**
	 * Test method for {@link Req4.FileInfo#setLink(java.lang.String)}.
	 */
	@Test
	public final void testSetLink() {
		FileInfo teste =  new FileInfo();
		teste.setLink("http://www.google.pt");
		assertEquals("http://www.google.pt", teste.getLink());
	}

}
