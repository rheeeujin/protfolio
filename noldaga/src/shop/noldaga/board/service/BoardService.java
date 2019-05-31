package shop.noldaga.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import shop.noldaga.board.vo.BoardImg;
import shop.noldaga.board.vo.BoardVo;
import shop.noldaga.common.util.MyBatisSessionManager;

public interface BoardService  {
	
	SqlSession session = MyBatisSessionManager.getSqlSession();
	
	public void write(BoardVo vo);
	public List<BoardVo> list(String category, int from, int to);
	public List<BoardVo> listReview(int psidx, int from, int to);
	public List<BoardImg> listReviewImg(int boardidx);
	public Map<String, Object> reviewOverall(int psidx);
	public BoardVo get(int boardidx);
	public void modify(BoardVo vo);
	public void delete(int boardidx);
	public boolean writeReview(BoardVo vo);
		
}
