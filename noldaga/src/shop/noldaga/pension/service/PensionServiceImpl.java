
/**
 * 
 *  @author 장우영
 *	
 *
 *
 *
 *  
 */

package shop.noldaga.pension.service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import shop.noldaga.common.util.DBManager;
import shop.noldaga.common.util.MyBatisSessionManager;
import shop.noldaga.pension.vo.CartVo;
import shop.noldaga.pension.vo.PensionVo;
import shop.noldaga.reserve.vo.ReserveVo;

public class PensionServiceImpl implements PensionService {
	SqlSession session = MyBatisSessionManager.getSqlSession();
	
	public static PensionServiceImpl dao = new PensionServiceImpl();

	public static PensionServiceImpl getInstance() {
		return dao;
	}

	public List<PensionVo> readList() {
		return session.selectList("pension.readList");
	}
	
	public List<PensionVo> selectPension(String search,int type, int from, int to) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("type", type);
		map.put("from", from);
		map.put("to", to);
		return session.selectList("pension.selectPension", map);
	}
	
	public int selectPensionCount(String search, int type) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("type", type);
		return session.selectOne("pension.selectPensionCount",map);
	}	
	
	public List<Map<String, String>> detailPension(String psidx, String startDate,String endDate) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("psidx", psidx);
		map.put("startdate", startDate);
		map.put("enddate", endDate);
		return session.selectList("pension.detailPension", map);
	}
	
	
	public int roomImgCount(int rmidx) {
		return session.selectOne("pension.roomImgCount", rmidx);
	}
	
	
	
	// index에 쓰이는 메서드
	public List<PensionVo> indexList() {
		List<PensionVo> pensions = session.selectList("pension.indexList");
		return pensions;
	}
	
	// 장바구니
	public ArrayList<CartVo> getcakt(String email) {
		String sql = "select p.pstitle,p.preaddr,p.oridx ,p.psidx " + 
				"from pension p, cart c, member m " + 
				"where c.psidx=p.psidx and  m.email=c.email  " + 
				"and m.email=? " ;
		Connection conn;
		PreparedStatement pstmt;

		ResultSet rs;
		CartVo vo = null;
		ArrayList<CartVo> cartlist = new ArrayList<>();
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new CartVo();
				vo.setPstitle(rs.getString(1));
				vo.setPreaddr(rs.getString(2));
				vo.setOridx(rs.getString(3));
				vo.setPsidx(rs.getInt(4));
				cartlist.add(vo);
			}
			conn.close();
			pstmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartlist;
	}
	
	public int caktinsert(String email,int psidx) {
		boolean b = false; 
		try {
			b = !cartselect(psidx, email);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO CART VALUES (CART_SEQ.nextval,?,?) ";
		int ret = 0;		
		Connection conn;
		PreparedStatement pstmt;
		conn = DBManager.getConnection();
		try {
			if(b) { // 없을때
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				pstmt.setInt(2, psidx);
				ret = pstmt.executeUpdate();
				pstmt.close();
			}
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public void cartdelete(int psidx,String email) {
		String sql = " DELETE CART WHERE psidx = ? ";
		Connection conn;
		PreparedStatement pstmt;
		
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,psidx);
				
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean cartselect(int psidx,String email) throws SQLException {
		String sql = " SELECT * FROM CART WHERE psidx = ? and email = ? " ;
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
	
		conn = DBManager.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, psidx);
		pstmt.setString(2, email);
		rs = pstmt.executeQuery();
		boolean ret = rs.next();
		conn.close();
		pstmt.close();
		rs.close();
			
		return ret;
}

	public boolean reserve(String psidx, String rmidx, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return session.insert("reserve.insert") > 0;
	}

	public ReserveVo reserveDetail(ReserveVo vo) {
		// TODO Auto-generated method stub
		vo = session.selectOne("reserve.reservDetail", vo);
		return vo;
	}

	
}
