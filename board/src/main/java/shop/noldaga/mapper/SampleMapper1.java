package shop.noldaga.mapper;

import org.apache.ibatis.annotations.Insert;

public interface SampleMapper1 {
	@Insert("insert into tbl_sample1 (col1) values(#{data})")
	int insertCol1(String data);
}
