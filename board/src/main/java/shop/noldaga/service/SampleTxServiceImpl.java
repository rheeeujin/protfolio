package shop.noldaga.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import shop.noldaga.mapper.SampleMapper1;
import shop.noldaga.mapper.SampleMapper2;

@Data
@AllArgsConstructor
@Log4j
@Service
public class SampleTxServiceImpl implements SampleTxService {
	private SampleMapper1 sampleMapper1;
	private SampleMapper2 sampleMapper2; 
	
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED)
	public void addData(String value) {
		// TODO Auto-generated method stub
		log.info("mapper1...........");
		sampleMapper1.insertCol1(value);

		log.info("mapper2...........");
		sampleMapper2.insertCol2(value);
		
		log.info("end...............");
	}

}
