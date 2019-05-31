package shop.noldaga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;
import shop.noldaga.domain.Criteria;
import shop.noldaga.domain.ReplyPageDTO;
import shop.noldaga.domain.ReplyVo;
import shop.noldaga.mapper.BoardMapper;
import shop.noldaga.mapper.ReplyMapper;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyMapper mapper;
	@Autowired
	private BoardMapper bMapper;
	
	@Override
	@Transactional
	public int register(ReplyVo vo) {
		log.info("register......");
		bMapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVo get(Long bno) {
		log.info("get......");
		return mapper.read(bno);
	}

	@Override
	public int modify(ReplyVo vo) {
		log.info("modify......");
		return mapper.update(vo);
	}

	@Override
	@Transactional
	public int remove(Long rno) {
		log.info("remove......");
		bMapper.updateReplyCnt(mapper.read(rno).getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVo> getList(Criteria cri, Long bno) {
		log.info("getList......");
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		log.info("getListPage......");
		return new ReplyPageDTO(mapper.getCountByBno(bno), mapper.getListWithPaging(cri, bno));
	}

}
