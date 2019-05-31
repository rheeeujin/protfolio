package shop.noldaga.member.service;


import org.apache.ibatis.session.SqlSession;

import shop.noldaga.common.util.MyBatisSessionManager;
import shop.noldaga.member.vo.Member;

/**
 * 
 *  @author 서재진
 *
 */

public interface MemberService {
	SqlSession session = MyBatisSessionManager.getSqlSession(true);
	
	public boolean isMember(String email);
	public boolean isAuth(String email);
	public boolean authenticate(String email);
	public Member login(String email, String pw);
	public void logout();
	public void join(Member vo);
	public void resign(String email);
	public void mypage(Member vo);
}

