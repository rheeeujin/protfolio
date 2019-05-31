package shop.noldaga.member.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import shop.noldaga.common.util.MyBatisSessionManager;
import shop.noldaga.common.util.SecurityUtil;
import shop.noldaga.member.vo.Member;

/**
 * 
 *  @author 서재진
 *
 */

public class MemberServiceImpl implements MemberService{
	SqlSession session = MyBatisSessionManager.getSqlSession(true);
	
	public boolean isMember(String email) {
		return session.selectOne("member.isMember",email) == null ? false : true;
	}

	public boolean isAuth(String email) {
		return session.selectOne("member.isAuth",email) == null ? false : true;
	}

	public boolean authenticate(String email) {
		return session.update("member.authenticate",email)>0;
	}
	
	public Member login(String email, String pw) {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("pw",pw);
		System.out.println(pw);
		
		return session.selectOne("member.login",map);
	}

	public void logout() {
		// TODO Auto-generated method stub

	}

	public void join(Member vo) {
		session.insert("member.join", vo);
	}
	
	
	public void resign(String email) {
		session.delete("member.resign",email);
	}
		
	public void mypage(Member vo) {
		session.update("member.mypage",vo);
	}
}

