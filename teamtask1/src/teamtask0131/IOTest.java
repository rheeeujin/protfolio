package teamtask0131;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

public class IOTest {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("file.txt");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		FileInputStream fis = new FileInputStream("file.txt");
		BufferedInputStream bis = new BufferedInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(bis);

		for(int i = '0'; i < '9'; i++) {
			bos.write(i);
		}
		bos.close();
		
		System.out.println(ois.read());
		ois.close();
//		FileWriter fw = new FileWriter("file1.txt");
//		BufferedWriter bw = new BufferedWriter(fw);
//		FileReader fr = new FileReader("file1.txt");
//		BufferedReader br = new BufferedReader(fr);
//		
//		for (int i = '0'; i < '9'; i ++) {
//			bw.write(i);
//		}
//		bw.close();
//		System.out.println(br.read());
//		br.close();
	}
}
