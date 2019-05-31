package shop.noldaga.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shop.noldaga.domain.Criteria;
import shop.noldaga.domain.ReplyVo;

public interface ReplyMapper {
	public int insert(ReplyVo vo);
	public ReplyVo read(Long bno);
	public int update(ReplyVo vo);
	public int delete(Long rno);
	int deleteAll(Long bno);
	
	public List<ReplyVo> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
	
	public int getCountByBno(Long bno);
}