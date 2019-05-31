package shop.noldaga.board.vo;


import java.util.List;

import lombok.Data;

@Data
public class BoardVo {
	private int idx;
	private String title;
	private String cont;
	private String regdate;
	private String email;
	
	private int cate; // 1. 공지 , 2. QnA , 3. 후기
	
	private int psIdx;
	private int score;
	
	private List<BoardImg> imgList;
}
