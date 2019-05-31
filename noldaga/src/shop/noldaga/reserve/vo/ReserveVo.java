package shop.noldaga.reserve.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ReserveVo {
	public ReserveVo(int psidx, int rmidx, String email, String name, String tel, String startdate, String enddate,
			int days, int money, int moneyunit, String pstitle, String rmtitle) {
		super();
		this.psidx = psidx;
		this.rmidx = rmidx;
		this.email = email;
		this.name = name;
		this.tel = tel;
		this.startdate = startdate;
		this.enddate = enddate;
		this.days = days;
		this.money = money;
		this.moneyunit = moneyunit;
		this.pstitle = pstitle;
		this.rmtitle = rmtitle;
	}
	private int rsidx;
	private int psidx;
	private int rmidx;
	private String email;
	private String name;
	private String tel;
	private String startdate;
	private String enddate;
	private int days;
	private int money;
	private int moneyunit;
	private String pstitle;
	private String rmtitle;
}
