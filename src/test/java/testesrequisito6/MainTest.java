package testesrequisito6;

import org.junit.Before;
import org.junit.Test;

import requisito6.*;

public class MainTest {

	static Main main;

    @Before
    public void setUp() {
		main = new Main();
	}

	@Test
	public void testMain() {
		main.main(new String[] {""});
	}

}
