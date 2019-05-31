package shop.noldaga.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import shop.noldaga.domain.Criteria;
import shop.noldaga.domain.ReplyVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyServiceTest {
	@Setter @Autowired
	private ReplyService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		ReplyVo vo = new ReplyVo();
		vo.setBno(2170104L);
		vo.setReply("Test 댓글 내용");
		vo.setReplyer("Test 댓글 작성자");
		service.register(vo);
		log.info("새로 작성된 게시글 번호 : " + vo.getBno());
	}
	
	@Test
	public void testGet() {
		log.info(service.get(1L));
	}
	
	@Test
	public void testGetList() {
		service.getList(new Criteria(1, 10),2170104L).forEach(log::info);
	}
	
	@Test
	public void testGetListPage() {
		System.out.println(service.getListPage(new Criteria(1, 10),2170104L));
	}
	
	@Test
	public void testModify() {
		ReplyVo vo = new ReplyVo();
		vo.setRno(1L);
		vo.setReply("수정된 Test 댓글 내용");
		log.info(service.modify(vo));
	}

	@Test
	public void testDelete() {
		log.info(service.remove(6L));
	}
}
