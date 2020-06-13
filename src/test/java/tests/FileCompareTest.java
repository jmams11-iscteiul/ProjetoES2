package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import requisito6.*;

class FileCompareTest {

	static FileCompare fc;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		fc = new FileCompare();
		fc.readConfig();
		fc.createTempRepo();
	}

	@Test
	void testcreateTempRepo() {
		assertNotNull(fc.getRepository());
	}

	@Test
	void testgetLastTwoTags() {
		try {
			assertEquals(2, fc.getLastTwoTags().size());
			assertNotEquals(fc.getLastTwoTags().get(0), fc.getLastTwoTags().get(1));
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testfileDiff() {
		fc.computeResults();
		assertNotNull(fc.getRawVersion());
		assertNotNull(fc.getDiffVersion());
		assertNotEquals(fc.getRawVersion(), fc.getDiffVersion());
	}

	@Test 
	void outputString(){
		fc.computeResults();
		assertNotNull(fc.outputString());
		assertNotEquals("", fc.outputString());
	}
}

