package com.graduation.util.database.sqlserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.graduation.util.Commons;

public class ServiceSoap { 
//	public static String PORT="15126";
//	public static String DOMAIN= "http://10.0.2.2:"+PORT+"/Web/";
	public static String DOMAIN = "http://www.commelp.org/";
	 
	static String SERVER_URL =DOMAIN + "WebService.asmx";
//	
	
	public static HttpURLConnection con;
	
	private static String getRequestStr( 
		 String methodName,
		 ArrayList<String>  params,
		 ArrayList<String> values) { 
		String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
				"xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
				"xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "<soap:Body />";
		String tps, vps, ts;
		String mreakString = "";
		
		Log.i(" ServerSoap ","===RequestStr==="); 
		 
			mreakString = "<" + methodName + " xmlns=\"http://tempuri.org/\">"; 
			for (int i = 0; i < params.size(); i++) {
				tps =params.get(i).toString();
				//设置该方法的参数为.net webService中的参数名称
				vps = values.get(i).toString();
				ts = "<" + tps + ">" + vps + "</" + tps + ">";
				mreakString = mreakString + ts;
			}
			mreakString = mreakString + "</" + methodName + ">";
 
		 
		/*
		+"<HelloWorld xmlns=\"http://tempuri.org/\">"
		+"<x>string11661</x>"
		+"<SF1>string111</SF1>"
		+ "</HelloWorld>"
		*/ 
		String requestData = soap + mreakString + "</soap:Envelope>";

		Log.i(" ServerSoap ","===RequestString==="); 
		Log.i("==RequestString==",requestData);  
		return requestData;
 }

	public static ArrayList<String> GetWebServre(
			String methodName, 
			ArrayList<String> params,
			ArrayList<String> values){
	//		ArrayList<String> Parameters, 
	//		ArrayList<String> ParValues) {
		
	//	Log.i("==========WEB Server = Response Value==============","");
		
		ArrayList<String> listValues = new ArrayList<String>();
		 
		String soapAction = "http://tempuri.org/" + methodName; 
		String requestData =getRequestStr(methodName,params,values);
		
		try { 
			con=getHttpURLConnection();
			byte[] bytes = requestData.getBytes("utf-8");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setConnectTimeout(Commons.CONNECTION_MAX_TIME);// 设置超时时间
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
			con.setRequestProperty("SOAPAction", soapAction);
			con.setRequestProperty("Content-Length", "" + bytes.length);
			
			OutputStream outStream = con.getOutputStream();
			outStream.write(bytes);
			outStream.flush();
			outStream.close();
			InputStream inStream = con.getInputStream();
	
			//data=parser(inStream);
			//System.out.print("11");
			listValues=inputStreamToValueList(inStream, methodName);
			
	//		Log.i("==========WEB Server = Response Value==============","===============获取的返回值===============");
	//		for(String str:listValues){
	//			Log.i("===Value===","======="+str+"=======");
	//		}
	//		System.out.println(listValues);
	//		Log.i("==========WEB Server = Response Value==============","===============获取的返回值===============");		
			return listValues;
			
		} catch (Exception ex) { 
		
			ex.printStackTrace();
	//		Log.i("ServerSoap Exception",ex.getMessage());
	//		System.out.println();
			return null;
		}
	}
 
	public static HttpURLConnection  getHttpURLConnection() throws IOException{
		URL url = new URL(SERVER_URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		return con;
	}

	private static ArrayList<String> inputStreamToValueList(
			InputStream in, String MonthsName) throws IOException {
		StringBuffer out = new StringBuffer();
		String s1 = "";
		byte[] b = new byte[4096];
		ArrayList<String> values = new ArrayList<String>();
		values.clear();
	
		for (int n; (n = in.read(b)) != -1;) {
			s1 = new String(b, 0, n);
			out.append(s1);
		} 
		Log.i("inputStreamToValueList",out.toString());
		Document doc=Jsoup.parse(out.toString());
		Elements links=doc.getElementsByTag("string");
		for(Element link:links){
	//		String attribute=link.attr(""); 
			values.add(link.text());
		}  
	
		return values;
	}

}
