package vo;

import lombok.Data;

@Data
public class MemberVo {
	private int midx;
	private String userid;
	private String pw;
	private String name;
	private String email;
	private int admin;
}
