package vo;

import java.sql.Date;

import lombok.Data;

@Data
public class ReplyVo {
	private int ridx;
	private int bidx;
	private String userid;
	private String reply;
	private String reply_date;
}
