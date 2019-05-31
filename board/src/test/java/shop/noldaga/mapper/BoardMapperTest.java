package shop.noldaga.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import shop.noldaga.domain.BoardVo;
import shop.noldaga.domain.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testGetList() {
//		boardMapper.getList().forEach(board-> log.info(board));
		boardMapper.getList().forEach(log::info);
	}
	
	@Test
	public void testPaging() {
		Criteria cri = new Criteria(1,10);
		cri.setType("W");
		cri.setKeyword("user");
		boardMapper.getListWithPaging(cri).forEach(log::info);
	}
	
	@Test
	public void testInsert() {
		BoardVo vo = new BoardVo();
		vo.setTitle("새로 작성하는 글");
		vo.setContent("새로 작성하는 내용");
		vo.setWriter("newbie");
		boardMapper.insert(vo);
		log.info(vo);
	}

	@Test
	public void testInsertSelectKey() {
		BoardVo vo = new BoardVo();
		vo.setTitle("새로 작성하는 글 select key");
		vo.setContent("새로 작성하는 내용 select key");
		vo.setWriter("newbie");
		boardMapper.insertSelectKey(vo);
		log.info(vo);
	}
	
	@Test
	public void testread() {
		BoardVo vo = boardMapper.read(4L);
		log.info(vo);
	}
	
	
	@Test
	public void testDelete() {
		log.info("Delete Count : " + boardMapper.delete(5L));
	}
	
	@Test
	public void testUpdate() {
		BoardVo vo = new BoardVo();
		vo.setBno(4L);
		vo.setTitle("수정된 제목");
		vo.setContent("수정된 내용");
		vo.setWriter("user00");
		log.info(boardMapper.update(vo));
	}
	
	@Test
	public void testCount() {
		Criteria cri = new Criteria(1,10);
		cri.setType("W");
		cri.setKeyword("user");
		System.out.println(boardMapper.getTotalCount(cri));
	}
}
