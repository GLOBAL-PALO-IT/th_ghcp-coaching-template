import static org.junit.Assert.*;

import org.junit.Test;

import utils.CommonUtils;

public class CommonUtilsTest {
	@Test
	public void testIsBoolean1() {
		String boolTest = "true";
		boolean result = CommonUtils.isBoolean(boolTest);
		assertEquals(true, result);
	}

	@Test
	public void testIsBoolean2() {
		String boolTest = "false";
		boolean result = CommonUtils.isBoolean(boolTest);
		assertEquals(false, result);
	}

	@Test
	public void testIsBoolean3() {
		String boolTest = "trueeee";
		boolean result = CommonUtils.isBoolean(boolTest);
		assertEquals(false, result);
	}
	
}
