package test;

import java.io.File;

public class Path {
	public static void main(String[] args) {
		getPath();
	}
	public static void getPath() {
		String path = (new File("").getAbsolutePath()+"\\WebContent\\").replace("\\", "/");
		System.out.println(path);
	}
}
