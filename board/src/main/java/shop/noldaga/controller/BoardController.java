package shop.noldaga.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;
import shop.noldaga.domain.AttachFileVo;
import shop.noldaga.domain.BoardVo;
import shop.noldaga.domain.Criteria;
import shop.noldaga.domain.PageDTO;
import shop.noldaga.service.BoardService;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Model model, Criteria cri) {
		log.info("list");
		model.addAttribute("list",service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
	}
	
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void register() {}
	
	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVo vo, RedirectAttributes rttr) {
		log.info("register : "+vo);
		if(vo.getAttachList() !=null) {
			vo.getAttachList().forEach(log::info);
		}
		service.regisrter(vo);
		rttr.addFlashAttribute("result",vo.getBno()); // flashattribute 1회성 insert용
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get","/modify"})
	public void get(Long bno, Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("get");
		model.addAttribute("board",service.get(bno));
	}
	
	
	@PostMapping("/modify") @PreAuthorize("principal.username == #vo.writer")
	public String modify(BoardVo vo, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
		log.info("modify : "+ vo);
		if (service.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
		}
//		rttr.addAttribute("pageNum",cri.getPageNum());
//		rttr.addAttribute("amount",cri.getAmount());
		return "redirect:/board/list" + cri.getListLink();
	}

	@PostMapping("/remove") @PreAuthorize("principal.username == #writer")
	public String remove(Long bno, RedirectAttributes rttr , @ModelAttribute("cri") Criteria cri, String writer) {
		log.info("remove");
		//1
		List<AttachFileVo> list = service.getAttachList(bno);
		//2
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		//3
		deleteFiles(list);
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@GetMapping(value="getAttachList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE) @ResponseBody
	public ResponseEntity<List<AttachFileVo>> getAttachList(Long bno){
		log.info("getAttachList");
		return new ResponseEntity<List<AttachFileVo>>(service.getAttachList(bno),HttpStatus.OK);
	}
	
	private void deleteFiles(List<AttachFileVo> list) {
		if(list == null || list.size() == 0) {
			return;
		}
		log.info("delete attached file ::" + list);
		list.forEach(attach -> {
			File file = null;
			String fileName = null;
			fileName = "d:\\upload\\" + attach.getUploadPath() + "\\" + attach.getUuid() + "_" + attach.getFileName();

			log.info(fileName);
			file = new File(fileName);
			file.delete();

			if(attach.isFileType()){
				String largeFileName = 
						"d:\\upload\\" + attach.getUploadPath() + "\\s_" + attach.getUuid() + "_" +attach.getFileName();
				log.info(largeFileName);
				
				file = new File(largeFileName);
				file.delete();
			}
		});
	}
	
	@GetMapping("/sample")
	public void sample() {}

	@GetMapping("/proxy")
	public void proxy() {}
}

/*
 	//test
	@GetMapping("test")
	public ResponseEntity<String> test(TestList vo){
		log.info(vo);
		return new ResponseEntity<String>(new Gson().toJson(vo),HttpStatus.OK);
	}
 */
/*	
	class TestList{
		List<AttachFileVo> attachList;
		Testlist(){
			attachList = new ArrayList<>();
		}
	}
 */