package shop.noldaga.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTxServiceTest {
	
	@Autowired
	private SampleTxService service;
	
	@Test
	public void testLong() {
		log.info(service);
		String str = "";
		for (int i = 0; i < 51; i++) {
			str += (char)((int)(Math.random() * 26) + 65) + "";
		}
		log.info(str);
		log.info(str.getBytes().length);
		
		service.addData(str);
	}
}
