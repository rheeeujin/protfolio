package shop.noldaga.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import shop.noldaga.domain.Criteria;
import shop.noldaga.domain.ReplyVo;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyMapperTest {
	@Autowired
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void testInsert() {
		ReplyVo vo = new ReplyVo();
		vo.setBno(2170104L);
		vo.setReply("Test 댓글 내용");
		vo.setReplyer("Test 댓글 작성자");
		mapper.insert(vo);
		log.info(vo);
	}
	
	@Test
	public void testRead() {
		log.info(mapper.read(1L));
	}
	
	@Test
	public void testUpdate() {
		ReplyVo vo = new ReplyVo();
		vo.setRno(1L);
		vo.setReply("수정된 Test 댓글 내용");
		mapper.update(vo);
		log.info(vo);
	}

	@Test
	public void testDelete() {
		log.info(mapper.delete(5L));
	}
	
	@Test
	public void testList() {
		mapper.getListWithPaging(new Criteria(), 2170104L).forEach(log::info);
	}
	
	@Test
	public void testListCount() {
		mapper.getCountByBno(2170104L);
	}
}
