package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BoardVo;
import vo.ReplyVo;

public class ReplyDao {
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	private static ReplyDao dao = new ReplyDao();
	private ReplyDao() {}
	
	public static ReplyDao getInstance() {
		return dao;
	}

	public List<ReplyVo> selectBoardReply(int bidx) {
		// TODO Auto-generated method stub
		String sql = "SELECT ridx, bidx, userid, reply, to_char(reply_date, 'yyyy/mm/dd') FROM reply where bidx = ?";
		List<ReplyVo>list = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int idx=0;
				ReplyVo vo = new ReplyVo();
				vo.setRidx(rs.getInt(++idx));
				vo.setBidx(rs.getInt(++idx));
				vo.setUserid(rs.getString(++idx));
				vo.setReply(rs.getString(++idx));
				vo.setReply_date(rs.getString(++idx));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public List<ReplyVo> selectMyReply(String userid) {
		// TODO Auto-generated method stub
		String sql = "SELECT ridx, bidx, userid, reply, to_char(reply_date,'yyyy/mm/dd') FROM reply " + 
				"WHERE userid = ? ";
		List<ReplyVo> list = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReplyVo vo = new ReplyVo();
				
				int idx = 0;
				vo.setRidx(rs.getInt(++idx));
				vo.setBidx(rs.getInt(++idx));
				vo.setUserid(rs.getString(++idx));
				vo.setReply(rs.getString(++idx));
				vo.setReply_date(rs.getString(++idx));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public void deleteReply(int ridx, String userid) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM reply WHERE ridx = ? and userid = ?";
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ridx);
			pstmt.setString(2, userid);
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
//	public void updateReply(ReplyVo vo, String userid) {
//		// TODO Auto-generated method stub
//		String sql = "UPDATE reply SET " + 
//				"reply=? " + 
//				"WHERE ridx = ? and userid = ?";
//		conn = DBManager.getConnection();
//		try {
//			int idx = 0;
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(++idx, vo.getReply());
//			pstmt.setInt(++idx, vo.getRidx());
//			pstmt.setString(++idx, userid);
//			rs = pstmt.executeQuery();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			DBManager.close(conn, pstmt);
//		}
//	}
	
	public void insertReply(ReplyVo vo) {
		// TODO Auto-generated method stub
		String sql = "INSERT into reply VALUES (seq_reply.nextval, ?,?,?,sysdate)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setInt(++idx, vo.getBidx());
			pstmt.setString(++idx, vo.getUserid());
			pstmt.setString(++idx, vo.getReply());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
}
