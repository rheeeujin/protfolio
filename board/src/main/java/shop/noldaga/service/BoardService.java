package shop.noldaga.service;

import java.util.List;

import shop.noldaga.domain.AttachFileVo;
import shop.noldaga.domain.BoardVo;
import shop.noldaga.domain.Criteria;

public interface BoardService {
	void regisrter(BoardVo vo);
	BoardVo get(Long bno);
	boolean modify(BoardVo vo);
	boolean remove(Long bno);
	List<BoardVo> getList(Criteria cri);
	public int getTotal(Criteria cri);
	
	List<AttachFileVo> getAttachList(Long bno);
}
