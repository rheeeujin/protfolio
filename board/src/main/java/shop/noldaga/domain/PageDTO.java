package shop.noldaga.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		endPage = ((cri.getPageNum() - 1) / 10 + 1) * 10;  // pager 마지막 숫자
		startPage = endPage-9; // pager 첫 숫자
		
		int realEnd = (total -1) / cri.getAmount() + 1; // pager 마지막 숫자가 10개 단위가 아닐때 대체 될 값
		if (realEnd < endPage) { // 대체값이 pager의 마지막 숫자보다 작을때 값 교체
			endPage = realEnd;
		}
		
		prev = startPage > 1;
		next = endPage < realEnd;
	}
	
	/*public static void main(String[] args) {
		PageDTO dto = new PageDTO(new Criteria(11,20), 500);
		System.out.println(dto);
	}*/
}
