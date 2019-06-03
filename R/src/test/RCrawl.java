package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class RCrawl {
	public static void crawl(String path, String url, String type) throws Exception {
		BufferedWriter output = null;
		if (type.equals("Nblog")) {
			Document doc = Jsoup.parse(new URL(url), 5000);

			String page = doc.select(".title_num").text();
			page = page.substring(page.indexOf("/") + 1,page.length()-1);
			int endpage = Integer.parseInt(page.trim().replace(",", ""));
			if (endpage / 10 > 1000) {
				endpage = 1000;
			} else {
				endpage = endpage / 10;
			}

			output = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(path + "rcrawl.txt", false), "utf8"));
			output.write("");
			output.close();
			url = url + "&start=";
			for (int i = 1; i < endpage; i = i + 10) {
				System.out.println(i);
				url = url.substring(0, url.lastIndexOf("=")+1)+i;
				System.out.println(url);
				doc = Jsoup.parse(new URL(url), 5000);
				// System.out.println(doc);
				page = doc.select(".title_num").text();
				// System.out.println(page);
				// page = page.substring(page.indexOf("/ ")+2, page.indexOf("건"));
				// page = page.replace(",", "");
				// int total = Integer.parseInt(page);
				// System.out.println(total);
				output = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(path + "rcrawl.txt", true), "utf8"));
				List<Element> eles = doc.select(".sh_blog_title");
				// output.write(page);
				// output.newLine();
				System.out.println(page);
				for (Element ele : eles) {
					System.out.println(ele.attr("title"));
					output.write(ele.attr("title"));
					output.newLine();
				}
				output.close();
			}

		}
	}
}
