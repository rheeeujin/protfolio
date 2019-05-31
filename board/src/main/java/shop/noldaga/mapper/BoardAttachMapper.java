package shop.noldaga.mapper;

import java.util.List;

import shop.noldaga.domain.AttachFileVo;

public interface BoardAttachMapper {
	void insert(AttachFileVo vo);
	void delete(String uuid);
	List<AttachFileVo> findByBno(Long bno);
	void deleteAll(Long bno);
	List<AttachFileVo> getOldFiles();
}
