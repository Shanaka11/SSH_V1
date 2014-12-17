import static org.junit.Assert.*;

import org.junit.Test;


public class log_lineTest {
	
	log_line test = new log_line("192.168.1.1");

	@Test
	public void test_log_line_check_ip_true() {
		
		assertEquals(true, test.check_ip("192.168.1.1"));
		
	}
	
	@Test
	public void test_log_line_check_ip_false() {
	
		assertEquals(false, test.check_ip("192.168.1.2"));
	}
	
	@Test
	public void test_check_attack_true() {
		
		test.no_of_attacks = 5;
		assertEquals(true,test.check_attack());
	}
	
	@Test
	public void test_check_attack_false() {
		
		test.no_of_attacks = 1;
		assertEquals(false,test.check_attack());
	}


}
