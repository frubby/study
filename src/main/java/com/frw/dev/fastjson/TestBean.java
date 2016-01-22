package com.frw.dev.fastjson;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.DateDeserializer;
import com.alibaba.fastjson.parser.deserializer.DateFormatDeserializer;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.ObjectFieldSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * @author Administrator
 *
 */
public class TestBean {

	private int id;
	private String name;
//	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date time;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getTime() {
		return time;
	}

//	@JSONField(name="time")
//	public void setTime(String time) {
//		this.time = new Date(time);
//	}
	public void setTime(Date time) {
		this.time = time;
	}


 

	public static void main(String args[]){
//		System.out.println(new Date());
		String jsonStr="{\"id\":1,\"name\":\"frw\",\"time\":\"Fri Jan 22 16:11:55 CST 2016\"}";
//		String jsonStr="{\"time\":\"Fri Jan 22 16:11:55 CST 2016\"}";


//		ParserConfig.getGlobalInstance().putDeserializer(TestBean.class, new MyDateFormatDeserializer());

		TestBean test=JSON.parseObject(jsonStr,TestBean.class);
		System.out.println(test.time);
		System.out.println(JSON.toJSONString(test));
		System.out.println(JSON.toJSON(test));
//		TestBean tb=new TestBean();
//		tb.id=11;
//		tb.name="eeerer";
//		tb.time=new Date("Sun Jan 11 00:00:00 CST 2015");
//		System.out.println(JSON.toJSON(tb));

	}
}
