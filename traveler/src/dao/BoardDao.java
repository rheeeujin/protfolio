package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BoardVo;
import vo.FileVo;
import vo.MemberVo;
import vo.ReplyVo;

public class BoardDao {
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	private static BoardDao dao = new BoardDao();
	private BoardDao() {}
	
	public static BoardDao getInstance() {
		return dao;
	}
	
	public List<BoardVo> selectALLBoard(int page) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM " + 
				"(SELECT bidx, userid, title, bdate, bcnt, preview, ROWNUM rn FROM board b ORDER by bidx desc) " + 
				"WHERE rn BETWEEN ? and ?";
		List<BoardVo> list = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*10+1);
			pstmt.setInt(2, page*10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				int idx = 0;
				vo.setBidx(rs.getInt(++idx));
				vo.setUserid(rs.getString(++idx));
				vo.setTitle(rs.getString(++idx));
				vo.setBdate(rs.getDate(++idx));
				vo.setBcnt(rs.getInt(++idx));
				vo.setPreview(rs.getString(++idx));
				
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
	
	public List<BoardVo> selectMemberBoard(MemberVo mvo) {
		// TODO Auto-generated method stub
		String sql = "SELECT bidx,title, bdate, bcnt, bcontent, ROWNUM rn FROM board WHERE userid = ?";
		List<BoardVo> list = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mvo.getUserid());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				int idx = 0;
				vo.setBidx(Integer.parseInt(rs.getString(++idx)));
				vo.setTitle(rs.getString(++idx));
				vo.setBdate(rs.getDate(++idx));
				vo.setBcnt(rs.getInt(++idx));
				vo.setBcontent(rs.getString(++idx));
				
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
	public List<BoardVo> selectMemberBoard(MemberVo mvo,int page) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM " + 
				"(SELECT bidx,title, bdate, bcnt, bcontent, ROWNUM rn FROM board WHERE userid = ?)"+
				"WHERE rn BETWEEN ? and ?";;
				List<BoardVo> list = new ArrayList<>();
				try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mvo.getUserid());
					pstmt.setInt(2, (page-1)*10+1);
					pstmt.setInt(3, page*10);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						BoardVo vo = new BoardVo();
						int idx = 0;
						vo.setBidx(Integer.parseInt(rs.getString(++idx)));
						vo.setTitle(rs.getString(++idx));
						vo.setBdate(rs.getDate(++idx));
						vo.setBcnt(rs.getInt(++idx));
						vo.setBcontent(rs.getString(++idx));
						
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
	
	public BoardVo selectBoard(int bidx) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM board where bidx = ?";
		BoardVo vo = new BoardVo();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				vo.setBidx(rs.getInt(1));
				vo.setUserid(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setBdate(rs.getDate(4));
				vo.setBcnt(rs.getInt(5));
				vo.setBcontent(rs.getString(6));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}
	public List<FileVo> selectBoardFile(int bidx) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM files where bidx = ?";
		List<FileVo>list = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FileVo vo = new FileVo(rs.getString("origin"),rs.getString("realname"));
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
	
	public int getMaxPage() {
		// TODO Auto-generated method stub
		String sql = "SELECT floor((COUNT(*)+9)/10) FROM " + 
				"(SELECT * FROM board) ";
		int result = 0;
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	
	public void insertBoard(BoardVo vo, List<vo.FileVo> list) {
		// TODO Auto-generated method stub
		String sql = "insert into board(bidx, userid, title, bcontent, preview) values (SEQ_BOARD.nextval, ?, ?, ?,?)";
		conn = DBManager.getConnection();
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setString(++idx, vo.getUserid());
			pstmt.setString(++idx, vo.getTitle());
			pstmt.setString(++idx, vo.getBcontent());
			pstmt.setString(++idx, vo.getPreview());
			pstmt.executeUpdate();
			

			pstmt = conn.prepareStatement("SELECT max(bidx) fROM board");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int boardIdx = rs.getInt(1);
			System.out.println(boardIdx);
			pstmt.close();
	
			sql = "INSERT INTO files VALUES (Seq_files.nextval, ?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			
			for(FileVo file:list) {
				pstmt.setInt(1, boardIdx);
				pstmt.setString(2, file.getOrigin());
				pstmt.setString(3, file.getRealname());
				
				pstmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public void updateBoard(BoardVo vo, List<FileVo> list) {
		// TODO Auto-generated method stub
		System.out.println(vo);
		System.out.println(list);
		String sql = "UPDATE board SET " + 
				"title=?, bcontent=? " + 
				"WHERE bidx = ?";
		conn = DBManager.getConnection();
		try {
			conn.setAutoCommit(false);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setInt(3, vo.getBidx());
			pstmt.executeUpdate();
			
			System.out.println(vo.getBidx());
	
			sql = "INSERT INTO files VALUES (Seq_files.nextval, ?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			
			for(FileVo file:list) {
				pstmt.setInt(1, vo.getBidx());
				pstmt.setString(2, file.getOrigin());
				pstmt.setString(3, file.getRealname());
				
				pstmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void deleteBoard(int bidx) {
		// TODO Auto-generated method stub
		System.out.println("delete bidx = "+bidx);
		String sql = "DELETE FROM board WHERE bidx = ?";
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public void deleteFiles(int bidx) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM files WHERE bidx = ?";
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
}
