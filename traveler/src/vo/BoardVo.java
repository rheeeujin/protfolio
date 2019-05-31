package vo;

import java.sql.Date;

import lombok.Data;

@Data 
public class BoardVo {
	private int bidx;
	private String userid;
	private String title;
	private Date bdate;
	private int bcnt;
	private String bcontent;
	private String preview;
}
