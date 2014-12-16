package ssh;

import static org.junit.Assert.*;

import org.junit.Test;
//import static package.to.TestHelper.*;
public class map_allTest {

	
	

	@Test
	public void testIp() {
		Double actual = map_all.Ip("1.0.0.0");
		Double expect = (double)(16777216);
		assertEquals(actual,expect);
	}

}
