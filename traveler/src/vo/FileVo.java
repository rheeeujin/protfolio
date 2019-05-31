package vo;

import lombok.Data;

@Data
public class FileVo {
	private int fidx;
	private int bidx;
	private String origin;
	private String realname;
	private String regdate;
	public FileVo(String origin, String realname) {
		super();
		this.origin = origin;
		this.realname = realname;
	}
	
}
