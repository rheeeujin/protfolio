package shop.noldaga.domain;

import lombok.Data;

@Data
public class AttachFileVo {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	
	private Long bno;
}
