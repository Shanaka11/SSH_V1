import static org.junit.Assert.*;

import org.junit.Test;


public class UnBlockerTest {

	@Test
	public void test() {
		
		UnBlocker tUb = new UnBlocker();
		tUb.unblock("192.168.1.1");
		String te = tUb.tm;
		
		assertEquals("iptables -D -s 192.168.1.1 -j DROP",te);
		
		
	}

}
