package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

public class RserverTest {
	public static byte[] getWordCloud(String path, String url, String type) throws Exception{
		RConnection connection = new RConnection("127.0.0.1", 6311);
		
		REXP rexp = null;
		System.out.println("wordcloud");
		RCrawl.crawl(path,url,type);
		
		//라이브러리 기타 세팅
		connection.parseAndEval("library(stringr)");
		connection.parseAndEval("library(KoNLP)");
		connection.parseAndEval("useSejongDic()");
		connection.parseAndEval("options(encoding='utf-8')");
		connection.parseAndEval("library(RColorBrewer)");
		connection.parseAndEval("library(wordcloud)");
		connection.parseAndEval("library(Rserve)");
		connection.parseAndEval("Rserve()");
		System.out.println("library end!!");
		
		//
		System.out.println(path);
		path = path.replace("\\", "/");
		connection.parseAndEval("setwd('"+path+"')");
		System.out.println(connection.parseAndEval("getwd()").asString());

		connection.eval("data1<-readLines('rcrawl.txt')"); // 파일읽기
		connection.parseAndEval("data2<-sapply(data1,extractNoun,USE.NAMES=F)"); // 단어별로 분리
		connection.parseAndEval("data3<-unlist(data2)"); 
		
		connection.parseAndEval("data4<-str_replace_all(data3,'[^[:alpha:]]','')"); //분리된 단어에서 특수문자 제외
		
		connection.parseAndEval("data5<-Filter(function(x){nchar(x)>=2},data4)"); // 특정단어들 제거
//		connection.parseAndEval("data5<-str_replace_all(data5,'배낭여행','')"); 
//		connection.parseAndEval("data5<-str_replace_all(data5,'여행지','')"); 
//		connection.parseAndEval("data5<-str_replace_all(data5,'여행','')"); 
//		connection.parseAndEval("data5<-gsub('배낭여행','',data5)"); 
//		connection.parseAndEval("data5<-gsub('여행지','',data5)"); 
//		connection.parseAndEval("data5<-gsub('여행','',data5)"); 
		
		connection.parseAndEval("write(unlist(data5),\"rcrawl_2.txt\")"); // 단어가 지워진 부분 제거
		connection.parseAndEval("data5<-read.table(\"rcrawl_2.txt\")"); 
		
		//워드 클라우드 생성
		connection.parseAndEval("wordcount<-table(data5)"); 
		connection.parseAndEval("wordcloud(names(wordcount),freq=wordcount,scale=c(10,1),rot.per=0.02,min.freq=2,random.order=F,random.color=T,colors=brewer.pal(9,\"Set3\"))"); 

		//이미지 파일 저장
		connection.parseAndEval("savePlot('rcrawl.png',type='png')");
		
		connection.parseAndEval("graphics.off()");
		
		rexp = connection.parseAndEval("readBin('rcrawl.png','raw', 1024*1024)");
		
		connection.close();
		
		byte[] arr = rexp.asBytes();
		System.out.println(arr.length);
		
		System.out.println("end");
		
		return arr;
	}
	
	public static byte[] getApiChart(String path) throws Exception{
		RConnection connection = new RConnection("127.0.0.1", 6311);
		
		REXP rexp = null;
		System.out.println("api path : "+path);
		connection.parseAndEval("Rserve()");
//		
		connection.parseAndEval("setwd('"+path+"')"); 
		System.out.println("setwd ok");
		
		connection.parseAndEval("api1<-read.csv('Report1.csv')"); 
		System.out.println("api1<-read.csv('Report1.csv')");
		
		connection.parseAndEval("api1<-api1[,2:13]"); 
		System.out.println("api1<-api1[,2:13]");
		
		connection.parseAndEval("api2<-api1[2:21,]"); 
		System.out.println("api2");
		
		connection.parseAndEval("names<-c('country','Number','Myeong-dong','Dongdaemun','Namdaemun','Forbidden','NTower','Insa-dong','Museums','Itaewon','Shinchon,Hongik','GangnamStation')"); 
		
		connection.parseAndEval("names(api2)<-names"); 
		System.out.println("names");
		
		connection.parseAndEval("label<-round(api2$Number/sum(api2$Number)*100,1)"); 
		connection.parseAndEval("label<-paste(label,'%')");
		connection.parseAndEval("label<-paste(api2[,1],'(',label,')')");
		System.out.println("label");
		
		connection.parseAndEval("palete<-rainbow(nrow(api2))");
		
		connection.parseAndEval("pie(api2$Number,radius=1,labels=label,col=palete)"); 
		System.out.println("pie(api2$Number,radius=1,labels=paste(label,'%'),col=rainbow(nrow(api2)))");
		
		connection.parseAndEval("savePlot('api.png',type='png')");
		
		connection.parseAndEval("graphics.off()");
		
		rexp = connection.parseAndEval("readBin('api.png','raw', 1024*1024)");
		
		
		byte[] arr = rexp.asBytes();
		System.out.println(arr.length);
		connection.close();
		
		System.out.println("end");
		return arr;

	}
	
	public static byte[] getIgraph(String path) throws Exception{
		RConnection connection = new RConnection("127.0.0.1", 6311);

		REXP x = null;

		System.out.println(path);

		System.out.println("igraph start path:"+path);

		connection.parseAndEval("library(DBI)");

		connection.parseAndEval("library(RJDBC)");

		connection.parseAndEval("library(igraph)");
		connection.parseAndEval("setwd('"+path+"')");

		connection.parseAndEval("driv <- JDBC('oracle.jdbc.driver.OracleDriver','C:/oraclexe/app/oracle/product/11.2.0/server/jdbc/lib/ojdbc6.jar')");

		//r.parseAndEval("setwd('D:/student/Jeong/R/tmp')");

		connection.parseAndEval("conn <- dbConnect(driv,'jdbc:oracle:thin:@54.180.119.0:1521:xe','portfolio','4118')");

		System.out.println("1");

		connection.parseAndEval("conn");

		System.out.println("1");

		connection.parseAndEval("emp <- dbGetQuery(conn,'SELECT a.employee_id, a.first_name||a.last_name as aname, a.manager_id, b.employee_id, CASE WHEN b.employee_id IS NULL then a.first_name||a.last_name else b.first_name||b.last_name end as bname FROM employees a left JOIN employees b ON (a.manager_id = b.employee_id) ORDER BY a.employee_id')");

		connection.parseAndEval("name<-emp$ANAME");
		connection.parseAndEval("boss<-emp$BNAME");
		
		connection.parseAndEval("df<-data.frame(name,boss)");

		connection.parseAndEval("g<-graph.data.frame(df,directed=T)");

		connection.parseAndEval("library(igraph)");

		connection.parseAndEval("plot(g,layout=layout.fruchterman.reingold,vertex.size=10,edge.arrow.sie=0.5)");

 

		connection.parseAndEval("savePlot('igraph.png',type='png')");

		connection.parseAndEval("graphics.off()");

		x = connection.parseAndEval("readBin('igraph.png','raw', 1024*1024)");

 

		byte[] arr = x.asBytes();

		System.out.println(arr.length);

		connection.close();

		System.out.println("igraph end");
		return arr;
	}
}
