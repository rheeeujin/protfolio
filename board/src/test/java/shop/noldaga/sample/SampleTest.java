package shop.noldaga.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTest {
	@Setter(onMethod_ = @Autowired)
	private Restuarant restuarant; 
	@Setter @Autowired
	private Hotel hotel;
	
//	@Test
//	public void testExist() {
//		assertNotNull(restuarant);
//		log.fatal("");
//		log.error("");
//		log.debug("");
//		log.warn("");
//		log.info(restuarant.toString());
//		log.info("----------------------------------");
//		log.info(restuarant.getChef().toString());
//	}
	
	@Test
	public void testExist() {
		assertNotNull(hotel);
		log.info(hotel);
		log.info("----------------------------------");
		log.info(hotel.getChef());
	}
	
}
