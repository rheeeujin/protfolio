package shop.noldaga.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;
import shop.noldaga.domain.ReplyVo;
import shop.noldaga.domain.Ticket;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class ReplyControllerTest {
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	
	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testCreate() throws Exception {
		ReplyVo vo = new ReplyVo();
		vo.setBno(2170104L);
		vo.setReply("Controller Test junit으로 작성한 내용");
		vo.setReplyer("작성자");
		String json = new Gson().toJson(vo); // JSON.stringify();
		log.info(json);
		
		mvc.perform(post("/replies/new").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().is(200));
	}
	
	@Test
	public void testGetList() throws Exception {
		mvc.perform(get("/replies/2170104/1")).andExpect(status().is(200));
	}

	@Test
	public void testGet() throws Exception {
		mvc.perform(get("/replies/1")).andExpect(status().is(200));
	}

	@Test
	public void testDelete() throws Exception {
		mvc.perform(delete("/replies/1")).andExpect(status().is(200));
	}

	@Test
	public void testModify() throws Exception {
		ReplyVo vo = new ReplyVo();
		vo.setBno(2170104L);
		vo.setReply("Controller Test junit으로 작성한 내용");
		vo.setReplyer("작성자");
		String json = new Gson().toJson(vo); // JSON.stringify();
		log.info(json);
		mvc.perform(put("/replies/1").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().is(200));
	}
}
