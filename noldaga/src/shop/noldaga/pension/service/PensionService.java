
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shop.noldaga.pension.vo.CartVo;
import shop.noldaga.pension.vo.PensionVo;
import shop.noldaga.reserve.vo.ReserveVo;

public interface PensionService {
	public List<PensionVo> readList();
	public List<PensionVo> selectPension(String search,int type, int from, int to);
	public int selectPensionCount(String search, int type);
	public List<Map<String, String>> detailPension(String psidx, String startDate,String endDate);
	public int roomImgCount(int rmidx);
	public List<PensionVo> indexList();
	public ArrayList<CartVo> getcakt(String email);
	public int caktinsert(String email,int psidx);
	public void cartdelete(int psidx,String email);
	public boolean cartselect(int psidx,String email) throws SQLException;
	public boolean reserve(String psidx, String rmidx, String startDate, String endDate);
	public ReserveVo reserveDetail(ReserveVo vo);
}
