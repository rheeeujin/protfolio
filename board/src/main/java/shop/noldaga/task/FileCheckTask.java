package shop.noldaga.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import shop.noldaga.domain.AttachFileVo;
import shop.noldaga.mapper.BoardAttachMapper;

@Log4j
@Component
public class FileCheckTask {
	@Setter @Autowired
	private BoardAttachMapper mapper;
	
	@Scheduled(cron="0 0 13 * * *")
	public void checkFiles() {
		log.warn("File Check Task Run.................");
		log.warn("====================================");
		
		List<AttachFileVo> fileList = mapper.getOldFiles();
		//DB에 일치하는파일 리스트
		List<Path> fileListPaths = fileList.stream().map(vo -> Paths.get("d:\\upload", vo.getUploadPath(),vo.getUuid() + "_" + vo.getFileName()))
				.collect(Collectors.toList());
		//fileListPaths.forEach(log::info);;
		
		//위 객체에 섬네일 파일 추가
		fileList.stream().filter(vo -> vo.isFileType())
		.map(vo -> Paths.get("d:\\upload", vo.getUploadPath(),
				"s_" + vo.getUuid() + "_" + vo.getFileName()))
		.collect(Collectors.toList());
		
		//어제의 폴더
		File targetDir = Paths.get("d:\\upload", getFolderYesterday()).toFile();
		
		//삭제 되어야 할 대상 파일 배열
		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
		
		for(File file : removeFiles) {
			log.info(file.getAbsolutePath());
			file.delete();
		}
	}
	
	private String getFolderYesterday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return sdf.format(cal.getTime());
	}
}
