package shop.noldaga.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import shop.noldaga.domain.AttachFileVo;
import shop.noldaga.domain.BoardVo;
import shop.noldaga.domain.Criteria;
import shop.noldaga.mapper.BoardAttachMapper;
import shop.noldaga.mapper.BoardMapper;
import shop.noldaga.mapper.ReplyMapper;

@Log4j @Service @Setter
public class BoardServiceImpl implements BoardService{
	@Autowired 
	BoardMapper mapper;
	@Autowired
	private BoardAttachMapper bMapper;
	@Autowired
	private ReplyMapper rMapper;
	
	
	@Override
	public void regisrter(BoardVo vo) {
		log.info("register........");
		mapper.insertSelectKey(vo);
		
		if(vo.getAttachList() != null && vo.getAttachList().size() > 0) {
			vo.getAttachList().forEach(attach -> {
				attach.setBno(vo.getBno());
				bMapper.insert(attach);
			});
		}
	}

	@Override
	public BoardVo get(Long bno) {
		log.info("get........");
		return mapper.read(bno);
	}

	@Override 
	@Transactional
	public boolean modify(BoardVo vo) {
		log.info("modify........");
		bMapper.deleteAll(vo.getBno());
		if(vo.getAttachList() != null && vo.getAttachList().size() > 0) {
			vo.getAttachList().forEach(attach -> {
				attach.setBno(vo.getBno());
				bMapper.insert(attach);
			});
		}
		return mapper.update(vo) == 1;
	}

	@Override
	@Transactional
	public boolean remove(Long bno) {
		log.info("remove........");
		rMapper.deleteAll(bno); 
		bMapper.deleteAll(bno);
		return mapper.delete(bno);
	}

	@Override
	public List<BoardVo> getList(@Qualifier("cri") Criteria cri) {
		log.info("getList........" + cri);
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("getTotal........" + cri);
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<AttachFileVo> getAttachList(Long bno) {
		// TODO Auto-generated method stub
		return bMapper.findByBno(bno);
	}
	
	
}
