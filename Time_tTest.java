import static org.junit.Assert.*;

import org.junit.Test;


public class Time_tTest {
	
	Time_t test = new Time_t("12:11:10");
	Time_t test2 = new Time_t("1:12:12");
	Time_t test3 = new Time_t("1:12:12");
	Time_t test4;
	
	@Test
	public void test_equals_true() {
		assertEquals(true, test2.equals(test3));
	}
	@Test
	public void test_equals_false() {
		assertEquals(false,test2.equals(test));
	}
	@Test
	public void test_add(){
		test4 = test.add(test2);
		assertEquals(13,test4.hr);
		assertEquals(23,test4.mn);
		assertEquals(22,test4.se);
	}
	@Test
	public void test_add_to(){
		test.addto(test2);
		assertEquals(13,test.hr);
		assertEquals(23,test.mn);
		assertEquals(22,test.se);
		
	}

}
