package com.frw.learn.iotest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BIOClient {

	
	public static void connect(int i){
		Socket client=null;
		PrintWriter writer=null;
		BufferedReader reader=null;


		try{
			client=new Socket();
			client.connect(new InetSocketAddress("localhost",8000));
			writer=new PrintWriter(client.getOutputStream());
			reader =new BufferedReader(new InputStreamReader(client.getInputStream()));
			writer.println("client start ");
			Thread.sleep(1000);
			writer.println("time : "+System.currentTimeMillis());
			Thread.sleep(1000);
			writer.println("client end ");
			writer.flush();
			String str=null;
			System.out.println("from server :"+reader.readLine());
			System.out.println("from server :"+reader.readLine());
			System.out.println("from server :"+reader.readLine());

		}catch(IOException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(writer!=null)
				writer.close();
			if(reader!=null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(client!=null)
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public static void main(String[] args){
		
		for(int i=0;i<10;i++){
			final int mark=i;
			new Thread(){
				public void run(){
					connect(mark);
				}
			}.start();
		}
	

	}
}
