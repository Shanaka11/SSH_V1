package kk;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class mapTest {

	@Test
	public void testLocation() {
		try {
			map.location("17");
			assertEquals(map.url,"https://www.google.lk/maps/place//@-27.0000,133.0000,3z/data=!4m2!3m1!1s0x0:0x0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testIp() {
		Double actual = map.Ip("1.0.0.0");
		Double expect = (double)(16777216);
		assertEquals(actual,expect);
	}

}
