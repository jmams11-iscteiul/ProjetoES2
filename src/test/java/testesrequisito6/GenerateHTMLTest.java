package testesrequisito6;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import requisito6.*;

public class GenerateHTMLTest {
	
	static GenerateHTML gHtml;
	static FileCompare fileCompare;

    @Before
    public void setUp() {
		gHtml = new GenerateHTML();
		fileCompare = new FileCompare();
	}

	@Test
	public void testGenerateHTML() {
		assertNotNull(gHtml.printToString(fileCompare.getDiffVersion(),fileCompare.getRawVersion()));
		assertNotEquals("", gHtml.printToString(fileCompare.getDiffVersion(),fileCompare.getRawVersion()));
	}

}
