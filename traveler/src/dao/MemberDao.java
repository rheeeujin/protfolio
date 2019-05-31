package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MemberVo;

public class MemberDao {
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	private static MemberDao dao = new MemberDao();

	private MemberDao() {
	}

	public static MemberDao getInstance() {
		return dao;
	}

	public List<MemberVo> selectNomalMember() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM member where admin = 1";
		List<MemberVo> list = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVo vo = new MemberVo();
				int idx = 0;
				vo.setMidx(rs.getInt(++idx));
				vo.setUserid(rs.getString(++idx));
				vo.setPw(rs.getString(++idx));
				vo.setName(rs.getString(++idx));
				vo.setEmail(rs.getString(++idx));
				vo.setAdmin(rs.getInt(++idx));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	public List<MemberVo> selectAdminMember() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM member where admin = 0";
		List<MemberVo> list = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVo vo = new MemberVo();
				int idx = 0;
				vo.setMidx(rs.getInt(++idx));
				vo.setUserid(rs.getString(++idx));
				vo.setPw(rs.getString(++idx));
				vo.setName(rs.getString(++idx));
				vo.setEmail(rs.getString(++idx));
				vo.setAdmin(rs.getInt(++idx));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public MemberVo selectMember(String userid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM member where userid = ?";
		MemberVo vo = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int idx = 0;

				vo = new MemberVo();
				vo.setMidx(rs.getInt(++idx));
				vo.setUserid(rs.getString(++idx));
				vo.setPw(rs.getString(++idx));
				vo.setName(rs.getString(++idx));
				vo.setEmail(rs.getString(++idx));
				vo.setAdmin(rs.getInt(++idx));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}
	public MemberVo selectMember(String userid, String pw) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM member where userid = ? and pw=?";
		MemberVo vo = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int idx = 0;
				
				vo = new MemberVo();
				vo.setMidx(rs.getInt(++idx));
				vo.setUserid(rs.getString(++idx));
				vo.setPw(rs.getString(++idx));
				vo.setName(rs.getString(++idx));
				vo.setEmail(rs.getString(++idx));
				vo.setAdmin(rs.getInt(++idx));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}


	public void insertMember(MemberVo vo) {
		// TODO Auto-generated method stub
		String sql = "INSERT into member VALUES (SEQ_MEMBER.nextval,?,?,?,?,1)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			System.out.println(vo);
			pstmt.setString(++idx, vo.getUserid());
			pstmt.setString(++idx, vo.getPw());
			pstmt.setString(++idx, vo.getName());
			pstmt.setString(++idx, vo.getEmail());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void updateBoard(MemberVo vo, String userid) {
		// TODO Auto-generated method stub
		String sql = "UPDATE member SET " +
				"pw=?, name=?, email=? " +
				"WHERE userid = ?";
		conn = DBManager.getConnection();
		try {
			int idx = 0;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++idx, vo.getPw());
			pstmt.setString(++idx, vo.getName());
			pstmt.setString(++idx, vo.getEmail());
			pstmt.setString(++idx, userid);
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void deleteMember(String userid) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM member WHERE userid = ?";
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
}
