package shop.noldaga.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/security/*")
@Controller
public class SecuritySampleController {
	@GetMapping("link")
	public void link() {
	}
	@GetMapping("all")
	public void doAll() {
		log.info("all");
	}
	@GetMapping("member")
	public void doMember() {
		log.info("member");
	}
	@GetMapping("admin")
	public void doAdmin() {
		log.info("admin");
	}
	@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")
	@GetMapping("annoMember")
	public void doMember2() {
		log.info("annoMember");
	}
	@PreAuthorize("ROLE_ADMIN")
	@GetMapping("annoAdmin")
	public void doAdmin2() {
		log.info("annoAdmin");
	}
}
