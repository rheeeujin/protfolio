package shop.noldaga.reserve.service;

import java.util.List;


import org.apache.ibatis.session.SqlSession;

import shop.noldaga.common.util.MyBatisSessionManager;
import shop.noldaga.reserve.vo.ReserveVo;

public class ReserveServiceImpl implements ReserveService {
	SqlSession session = MyBatisSessionManager.getSqlSession(true);
	
	public void insert(ReserveVo vo) {
		session.insert("reserve.insert", vo);
	}
	
	public ReserveVo getReserve(int rsidx) {
		return session.selectOne("reserve.selectByRsidx", rsidx);
	}

	public List<ReserveVo> selectByEmail(String email) {
		// TODO Auto-generated method stub
		return session.selectList("reserve.selectByEmail", email);
	}

	public void cancel(int rsidx) {
		// TODO Auto-generated method stub
		session.delete("reserve.cancel",rsidx);
	}
}
