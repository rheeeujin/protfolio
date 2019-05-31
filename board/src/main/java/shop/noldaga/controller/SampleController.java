package shop.noldaga.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;
import shop.noldaga.domain.SampleVo;
import shop.noldaga.domain.Ticket;

@RequestMapping("sample/*")
@Log4j
@RestController
public class SampleController {
	
	@GetMapping(value="/getText", produces=MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	public String getText() {
		log.info("MIME Type :: " + MediaType.TEXT_PLAIN_VALUE);
		return "안녕하세요";
	}
	
	@GetMapping(value="getSample",produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XHTML_XML_VALUE})
	public SampleVo getSample() {
		return new SampleVo(112, "스타", "로드");
	}
	
	@GetMapping(value="getList")
	public List<SampleVo> getList() {
		return IntStream.range(1, 10).mapToObj(i -> new SampleVo(i, i + "First", i + "Last")).collect(Collectors.toList());
	}
	
	@GetMapping(value="getMap")
	public Map<String, SampleVo> getMap() {
		Map<String, SampleVo> map =  new HashMap<>();
		map.put("First", new SampleVo(111, "그루트", "주니어"));
		return map;
	}
	
	@GetMapping(value="check")
	public ResponseEntity<SampleVo> check(@RequestParam("h") Double height, @RequestParam("w") Double weight) {
		SampleVo vo = new SampleVo(0, ""+height, ""+weight);
		ResponseEntity<SampleVo> result = null;		
		if (height <150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo); 
		}
		return result;
	}
	
	@GetMapping("product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
		return new String[] {"category : " + cat, "productid : " + pid};
	}
	
	
	@PostMapping("ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("convert........" + ticket);
		return ticket;
	}
}
