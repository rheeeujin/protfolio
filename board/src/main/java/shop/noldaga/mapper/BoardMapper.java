package shop.noldaga.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shop.noldaga.domain.BoardVo;
import shop.noldaga.domain.Criteria;

public interface BoardMapper {
//	@Select("select * from tbl_board where bno > 0") //pk로 찾으면 cost가 낮음
	public List<BoardVo> getList();
	
	public List<BoardVo> getListWithPaging(Criteria criteria);
	
	public int getTotalCount(Criteria cri);
	
	public void insert(BoardVo vo);
	
	public void insertSelectKey(BoardVo vo);
	
	public BoardVo read(Long bno);
	
	public boolean delete(Long bno);
	
	public int update(BoardVo vo);
	
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}
