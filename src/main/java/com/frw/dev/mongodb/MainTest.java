package com.frw.dev.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;

public class MainTest {


 
	
	public static void main(String args[]){
		MongoClient mongoClient = new MongoClient("10.10.195.125");
		MongoDatabase db=mongoClient.getDatabase("sp5000");
		MongoCollection colc=db.getCollection("log");
	
		Document doc=new Document();
		doc.append("name", "log_1").append("date", new Date());
		
		
		colc.insertOne(doc);
		
// 		colc.find( )
		
		
	}
	
	
}
