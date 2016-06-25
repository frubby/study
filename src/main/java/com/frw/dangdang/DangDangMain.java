package com.frw.dangdang;

import java.io.IOException;
import java.net.URI;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.frw.util.FileOperate;

public class DangDangMain {

	ConcurrentHashMap<String,String> map=new ConcurrentHashMap<String,String> ();
	public static String DD_UTL="http://promo.dangdang.com/subject.php?pm_id=3238481&tag_id=&sort=priority_asc&province_id=132&p=";
	public static  final int THREAD_NUM=10;
	HttpClient ht;
	ThreadPoolExecutor exes;

	ThreadLocal<HttpClient>  localClient;
	public DangDangMain(){
		init();
	}


	public void init(){
		exes=(ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_NUM);


		localClient=new ThreadLocal<HttpClient>(){
			@Override
			protected HttpClient initialValue() {
				return HttpClients.createDefault();
			}

		};
	}


	public void get(int page){

		HttpClient ht=localClient.get();
		HttpGet get=new HttpGet();
		get.setURI(URI.create(DD_UTL+page));
		try {
			HttpResponse rep= ht.execute(get);
			String html = EntityUtils.toString(rep.getEntity());
			Document doc = Jsoup.parse(html);
			Element ele= doc.getElementsByClass("pro_table").get(0);
			Elements links=ele.getElementsByClass("name");
			StringBuilder sb=new StringBuilder();
			for (Element link : links) {
				Element ea= link.getElementsByTag("a").get(0);
				String linkHref = ea.attr("href");
				String linkText = ea.attr("title");
//				 System.out.print(linkHref+"   ");
//				 System.out.println(linkText);
				 map.put(linkHref, linkText);
//				 sb.append(linkText+"  \t\t\t"+linkHref+"\r\n");
				 
				 
			}
			
//			FileOperate fop=new FileOperate();
//			fop.createFile("E:/page/"+page, sb.toString(), "UTF-8");
			//System.out.println(ele.toString());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}




	}


	public  class MyTask implements Runnable{

		int page;
		public MyTask(int i){
			page=i;
		}
		public void run() {

			get(page);
		}

	}

	public void start(){
		for(int i=1;i<=501;i++){
			exes.submit(new MyTask(i));
		}
	}

	public void end(){
		while(exes.getActiveCount()!=0&&exes.getTaskCount()!=exes.getCompletedTaskCount()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		exes.shutdown();
		try {
			exes.awaitTermination(100,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		Enumeration<String> iter=map.keys();
		StringBuilder sb=new StringBuilder();
		while(iter.hasMoreElements()){
			String url=iter.nextElement();
			String name=map.get(url);
//			System.out.println(name+"  "+url);
			 sb.append(name+"\t\t\t"+url+"\r\n");
		}
		
		FileOperate fop=new FileOperate();
		fop.createFile("E:/page/all.txt", sb.toString(), "UTF-8");
	}
	public static void main(String args[]){

		DangDangMain dd=new DangDangMain();
		dd.start();

		dd.end();


		

	}
}
