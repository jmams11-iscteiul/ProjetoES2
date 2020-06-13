package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Before;
import org.junit.Test;

import requisito6.FileCompare;

public class FileCompareTest {
	
	static FileCompare fc;

    @Before
    public void setUp() {
		fc = new FileCompare();
		fc.readConfig();
		try {
			fc.createTempRepo();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}



	@Test
	public void testcreateTempRepo() {
		assertNotNull(fc.getRepository());
	}

	@Test
	public void testgetLastTwoTags() {
		try {
			assertEquals(2, fc.getLastTwoTags().size());
			assertNotEquals(fc.getLastTwoTags().get(0), fc.getLastTwoTags().get(1));
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testfileDiff() {
		fc.computeResults();
		assertNotNull(fc.getRawVersion());
		assertNotNull(fc.getDiffVersion());
		assertNotEquals(fc.getRawVersion(), fc.getDiffVersion());
	}

	@Test 
	public void outputString(){
		fc.computeResults();
		assertNotNull(fc.outputString());
		assertNotEquals("", fc.outputString());
	}

}
