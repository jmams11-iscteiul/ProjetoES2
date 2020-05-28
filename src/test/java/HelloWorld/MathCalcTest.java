package HelloWorld;

import static org.junit.Assert.*;

import org.junit.Test;

public class MathCalcTest {

	@Test
	public void testCompound() {
		int result = (int) MathCalc.compound(10, 0.01, 260);
		int expected = 132;
		assertEquals(expected, result);
	}

	@Test
	public void testSum() {
		int result = MathCalc.sum(99, 33);
		int expected = 132;
		assertEquals(expected, result);
	}

	@Test
	public void testSubtract() {
		int result = MathCalc.subtract(133, 1);
		int expected = 132;
		assertEquals(expected, result);
	}

	@Test
	public void testMultiply() {
		int result = MathCalc.multiply(2, 66);
		int expected = 132;
		assertEquals(expected, result);
	}

	@Test
	public void testDivide() {
		int result = MathCalc.divide(264, 2);
		int expected = 132;
		assertEquals(expected, result);
	}

}
