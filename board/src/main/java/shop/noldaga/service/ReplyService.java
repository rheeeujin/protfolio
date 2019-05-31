package shop.noldaga.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shop.noldaga.domain.Criteria;
import shop.noldaga.domain.ReplyPageDTO;
import shop.noldaga.domain.ReplyVo;

public interface ReplyService {
	public int register(ReplyVo vo);
	public ReplyVo get(Long bno);
	public int modify(ReplyVo vo);
	public int remove(Long rno);
	
	public List<ReplyVo> getList(Criteria cri, Long bno);
	public ReplyPageDTO getListPage(Criteria cri, Long bno);
}