package shop.noldaga.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import shop.noldaga.domain.Criteria;
import shop.noldaga.domain.ReplyPageDTO;
import shop.noldaga.domain.ReplyVo;
import shop.noldaga.service.ReplyService;


@RestController
@RequestMapping("replies/*")
@AllArgsConstructor
@Log4j
public class ReplyController {
	private ReplyService service;
	
	@PostMapping("new") @PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> create(@RequestBody ReplyVo vo) {
		log.info("ReplyVo : " + vo);
		int insertCount = service.register(vo);
		log.info("Reply Insert Count : " + insertCount);
		
		return insertCount == 1 ? new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("{bno}/{page}")
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable Long bno, @PathVariable int page) {
		log.info("getList");
		return new ResponseEntity<>(service.getListPage(new Criteria(page, 10), bno), HttpStatus.OK);
	}
	
	@GetMapping("{rno}")
	public ResponseEntity<ReplyVo> get(@PathVariable Long rno) {
		log.info("get");
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}

	@DeleteMapping("{rno}") @PreAuthorize("principal.username == #vo.replyer")
	public ResponseEntity<String> remove(@RequestBody ReplyVo vo, @PathVariable("rno") Long rno) {
		log.info("remove");
		return service.remove(vo.getRno()) == 1 
				? new ResponseEntity<>("success",HttpStatus.OK) 
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("{rno}") @PreAuthorize("principal.username == #vo.replyer")
	public ResponseEntity<String> modify(@RequestBody ReplyVo vo, @PathVariable("rno") Long rno) {
		log.info("modify");
		return service.modify(vo) == 1 ? new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
