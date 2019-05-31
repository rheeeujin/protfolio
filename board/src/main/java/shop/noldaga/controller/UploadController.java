package shop.noldaga.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sun.net.httpserver.Headers;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;
import shop.noldaga.domain.AttachFileDTO;

@Controller
@Log4j
public class UploadController {
	
	@GetMapping("uploadForm")
	public void uploadForm() {
		log.info("uploadForm......");
	}
	
	@PostMapping("uploadForm")
	public void upload(MultipartFile[] uploadFile, Model model) {
		for (MultipartFile file : uploadFile) {
			log.info("----------------------------------");
			log.info("Upload File Name : " + file.getName());
			log.info("Upload File Origin Name : " + file.getOriginalFilename());
			log.info("Upload File Size : " + file.getSize());
			
			File saveFile = new File("d:\\upload", file.getOriginalFilename());
			try {
				file.transferTo(saveFile);
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}
		}
	}
	
	@GetMapping("uploadAjax")
	public void uploadAjax() {
		log.info("upload Ajax.....");
	}
	
	@PostMapping("uploadAjax")
	public ResponseEntity<List<AttachFileDTO>> uploadAjax(MultipartFile[] uploadFile) {
		List<AttachFileDTO> list = new ArrayList<>();
		
		String uploadRoot = "d:\\upload";
		File uploadFolder = new File(uploadRoot, getFolder());
		if(uploadFolder.exists()==false) {
			uploadFolder.mkdirs();
		}
		
		for (MultipartFile file : uploadFile) {
			AttachFileDTO dto = new AttachFileDTO();
			
			
			log.info("----------------------------------");
			log.info("Upload File Name : " + file.getName());
			log.info("Upload File Origin Name : " + file.getOriginalFilename());
			log.info("Upload File Size : " + file.getSize());
			String uploadFileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("\\")+1);
			
			log.info(uploadFileName);
			log.info(file.getOriginalFilename().lastIndexOf("\\")+1);
			
			//1
			dto.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID(); 
			//2
			dto.setUuid(uuid+"");
			uploadFileName = uuid + "_" + uploadFileName;
			
			//3
			dto.setUploadPath(getFolder());
			File saveFile = new File(uploadFolder, uploadFileName);
			try {
				file.transferTo(saveFile);	//파일 저장
				log.info(checkImageType(saveFile)); //이미지 여부 확인
				if(checkImageType(saveFile)) {
					//4
					dto.setImage(true);
					FileOutputStream thumb = new FileOutputStream(new File(uploadFolder,"s_"+ uploadFileName));
					Thumbnailator.createThumbnail(file.getInputStream(),thumb,100,100);
					thumb.close();
				}
			} catch (IllegalStateException | IOException e) {
				log.error(e.getMessage());
			}
			list.add(dto);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("display")
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("getFile fileName : " + fileName);
		
		File file = new File("d:\\upload\\" + fileName);
		
		log.info(file);
		
		ResponseEntity<byte[]> entity = null;
		
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			entity = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
	
	@GetMapping("download")
	public ResponseEntity<Resource> downloadFile(String fileName,@RequestHeader("user-agent") String userAgent){
		log.info("downloadFile fileName : " + fileName);
		Resource resource = new FileSystemResource("d:\\upload\\" + fileName);
		log.info(resource);
		
		if(!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		String resourceName = resource.getFilename();
		HttpHeaders headers = new HttpHeaders();
		try {
			String downloadName = resourceName.substring(resourceName.indexOf("_")+1);
			
			if(userAgent.contains("Trident")) { //IE 일때
				log.info("IE browser");
				downloadName =URLEncoder.encode(downloadName, "utf-8").replaceAll("\\+"," ");
			}
			else if(userAgent.contains("Edge")) { //Edge 일때
				log.info("Edge browser");
				downloadName = URLEncoder.encode(downloadName, "utf-8");
			}
			else {//기타 브라우저 (ex : 크롬, 파이어폭스)
				log.info("Chrome browser");
				downloadName = new String(downloadName.getBytes("utf-8"),"iso-8859-1");
			}
			
			headers.add("Content-Disposition","attachment; filename=" + downloadName); 
			headers.add("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(resource,headers,HttpStatus.OK);
	}
	
	@PostMapping("deleteFile")
	public ResponseEntity<String> deleteFile(String fileName, String type) {
		log.info("deleteFile.........." + fileName);
		
		File file = null;
		try {
			file = new File("d:\\upload\\" + URLDecoder.decode(fileName, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.delete();

		if(type.equals("image")) {
			String largeFileName = file.getAbsolutePath().replace("s_","");
			log.info(largeFileName);
			
			file = new File(largeFileName);
			file.delete();
		}
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
	
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(new Date());
	}
	
	private boolean checkImageType(File file) {
		try {
			return Files.probeContentType(file.toPath()).startsWith("image");
		} catch (IOException | NullPointerException e) {
			return false;
		}
	}
}
