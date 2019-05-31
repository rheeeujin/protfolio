<%@page import="java.io.BufferedReader"%><%@page import="java.io.InputStreamReader"%><%@page import="java.io.InputStream"%><%@page import="java.net.URL"%><%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%><%
	URL url = new URL("http://www.chosun.com/site/data/rss/ent.xml");
	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
	int b = 0;
	while((b = br.read())!= -1) {
		out.write(b);
	}
	br.close();
%>
