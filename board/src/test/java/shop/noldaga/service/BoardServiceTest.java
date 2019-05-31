package shop.noldaga.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import shop.noldaga.domain.BoardVo;
import shop.noldaga.domain.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {
	@Setter @Autowired
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		BoardVo vo = new BoardVo();
		vo.setTitle("새로 작성하는 글 by Service");
		vo.setContent("새로 작성하는 글 내용 by Service");
		vo.setWriter("newbie");
		service.regisrter(vo);
		log.info("새로 작성된 게시글 번호 : " + vo.getBno());
	}
	
	@Test
	public void testGet() {
		log.info(service.get(34L));
	}
	
	@Test
	public void testGetList() {
		service.getList(new Criteria(3, 10)).forEach(log::info);
	}
	
	@Test
	public void testModify() {
		BoardVo vo = new BoardVo();
		vo.setTitle("수정하는 글 by Modify");
		vo.setContent("수정하는 글 내용 by Modify");
		vo.setWriter("user00");
		vo.setBno(34L);
		log.info(service.modify(vo));
	}

	@Test
	public void testDelete() {
		log.info(service.remove(34L));
	}
}
