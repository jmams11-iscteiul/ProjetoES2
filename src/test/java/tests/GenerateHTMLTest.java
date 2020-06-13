package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import requisito6.*;

class GenerateHTMLTest {
	
	static GenerateHTML gHtml;
	static FileCompare fileCompare;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gHtml = new GenerateHTML();
		fileCompare = new FileCompare();
	}

	@Test
	void testGenerateHTML() {
		assertNotNull(gHtml.printToString(fileCompare.getDiffVersion(),fileCompare.getRawVersion()));
		assertNotEquals("", gHtml.printToString(fileCompare.getDiffVersion(),fileCompare.getRawVersion()));
	}

}
