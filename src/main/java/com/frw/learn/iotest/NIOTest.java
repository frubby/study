package com.frw.learn.iotest;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {

	public static void nioCopyFile(String res,String dest) throws IOException{
		
		FileInputStream fis=new FileInputStream(res);
		FileOutputStream fos=new FileOutputStream(dest);
		FileChannel readChl=fis.getChannel();
		FileChannel writeChl=fos.getChannel();
		ByteBuffer bf=ByteBuffer.allocate(10240);
		while(true){
			bf.clear();
			int len=readChl.read(bf);
			if(len<0){
				break;
			}
			bf.flip();
			writeChl.write(bf);
			
		}
		readChl.close();
		writeChl.close();
		
	}
	
	public static void copyFile(String res,String dest) throws IOException{
		
		FileInputStream fis=new FileInputStream(res);
		FileOutputStream fos=new FileOutputStream(dest);
		BufferedInputStream reader=new BufferedInputStream(fis);
		BufferedOutputStream writer=new BufferedOutputStream(fos);
		byte[] bf=new byte[10240];
		while(true){
			int len=reader.read(bf);
			if(len<0){
				break;
			} 
			writer.write(bf,0,len);
			
		}
		reader.close();
		writer.close();
		
	}
	
	public static String res="E:\\Redhat5.6_super5000\\Redhat5.6_super5000-Snapshot17.vmem";
	public static void main(String args[]){
		long start,end;
		try {
			start=System.currentTimeMillis();
			NIOTest.nioCopyFile(res, "C:\\test2.war");
			end=System.currentTimeMillis();
		
			System.out.println("nio time "+(end-start));
			
			start=System.currentTimeMillis();
			NIOTest.copyFile(res, "C:\\test23.war");
			end=System.currentTimeMillis();
			System.out.println("io time "+(end-start));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
