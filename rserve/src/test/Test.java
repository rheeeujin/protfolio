package test;

import java.util.Arrays;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;

public class Test {
	public static void main(String[] args) throws Exception {
		// r connection <-연결
		// rexp <-문법전송
//		RConnection connection = new RConnection("rhee.shop", 6311);
		RConnection connection = new RConnection("127.0.0.1", 6311);
//		System.out.println(connection);
		
		REXP rexp = null;
		int i = 0;
		System.out.println("start");
		System.out.println(++i);
		
		//라이브러리 기타 세팅
		connection.parseAndEval("library(stringr)");
		System.out.println(++i);
		connection.parseAndEval("library(KoNLP)");
		System.out.println(++i);
		connection.parseAndEval("useSejongDic()");
		System.out.println(++i);
		connection.parseAndEval("options(encoding='utf-8')");
		System.out.println(++i);
		connection.parseAndEval("library(RColorBrewer)");
		System.out.println(++i);
		connection.parseAndEval("library(wordcloud)");
		System.out.println(++i);
		System.out.println("----------------------------------");
		
		//
		connection.parseAndEval("setwd('D:/workspace/mongo/RServer/WebContent')");
		System.out.println(++i);
		System.out.println(connection.parseAndEval("getwd()").asString());
		System.out.println(++i);
		connection.eval("data1<-readLines('rcrawl.txt')"); // 파일읽기
		System.out.println(++i);
		connection.parseAndEval("data2<-sapply(data1,extractNoun,USE.NAMES=F)"); // 단어별로 분리
		System.out.println(++i);
		connection.parseAndEval("data3<-unlist(data2)"); 
		System.out.println(++i);
		
		connection.parseAndEval("data4<-str_replace_all(data3,'[^[:alpha:]]','')"); //분리된 단어에서 특수문자 제외
		System.out.println(++i);
		
		connection.parseAndEval("data5<-Filter(function(x){nchar(x)>=2},data4)"); // 특정단어들 제거
		System.out.println(++i);
//		connection.parseAndEval("data5<-unlist(data5)"); 
//		System.out.println(++i);
//		connection.parseAndEval("data5<-gsub('배낭여행','',data5)"); 
//		System.out.println(++i);
//		connection.parseAndEval("data5<-gsub('여행지','',data5)"); 
//		System.out.println(++i);
//		connection.parseAndEval("data5<-gsub('여행','',data5)"); 
//		System.out.println(++i);
		
		connection.parseAndEval("write(unlist(data5),\"rcrawl_2.txt\")"); // 단어가 지워진 부분 제거
		System.out.println(++i);
		connection.parseAndEval("data5<-read.table(\"rcrawl_2.txt\")"); 
		System.out.println(++i);
		
		//워드 클라우드 생성
		connection.parseAndEval("wordcount<-table(data5)"); 
		System.out.println(++i);
		connection.parseAndEval("wordcloud(names(wordcount),freq=wordcount,scale=c(10,1),rot.per=0.5,min.freq=2,random.order=F,random.color=T,colors=brewer.pal(9,\"Set3\"))"); 
		System.out.println(++i);

		//이미지 파일 저장
		connection.parseAndEval("savePlot('rcrawl.png',type='png')");
		System.out.println(++i);
		
		connection.parseAndEval("graphics.off()");
		System.out.println(++i);
		
		rexp = connection.parseAndEval("readBin('rcrawl.png','raw', 1024*1024)");
//		rexp = connection.parseAndEval("readBin('word.png','raw', 1024*1024)");
		
		connection.parseAndEval("unlink('rcrawl.png')");
		System.out.println(++i);
		
		byte[] arr1 = rexp.asBytes();
//		int[] arr2 = connection.parseAndEval("0:9").asIntegers();
		System.out.println(arr1.length);
//		System.out.println(Arrays.toString(arr2));
		
		System.out.println("end");
		
	}
	
	public static byte[] getImg() throws Exception{
		// r connection <-연결
		// rexp <-문법전송
//		RConnection connection = new RConnection("rhee.shop", 6311);
		RConnection connection = new RConnection("127.0.0.1", 6311);
//		System.out.println(connection);
		
		REXP rexp = null;
		
		connection.parseAndEval("library(wordcloud)");
		connection.parseAndEval("wordcloud(c(LETTERS, letters, 0:9), seq(1,100,length=62))");
		connection.parseAndEval("savePlot('word.png',type='png')");
		connection.parseAndEval("graphics.off()");
		rexp = connection.parseAndEval("readBin('word.png','raw', 1024*1024)");
		connection.parseAndEval("unlink('word.png')");
		byte[] arr1 = rexp.asBytes();
		int[] arr2 = connection.parseAndEval("0:9").asIntegers();
		System.out.println(arr1.length);
		System.out.println(Arrays.toString(arr2));
		
		System.out.println("end");
		connection.close();
		return arr1;
		
	}
}
