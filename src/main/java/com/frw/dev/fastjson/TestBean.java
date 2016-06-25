package com.frw.dev.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.DateFormatDeserializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Administrator
 *
 */
public class TestBean {

	private int id;
	private String name;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
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


	private static SerializeConfig mapping = new SerializeConfig();
	private static String dateFormat;
	static {
		dateFormat = "yyyy-MM-dd HH:mm:ss";
		mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
	}
	public static void main(String args[]){
		//		System.out.println(new Date());
		String jsonStr="{\"id\":1,\"name\":\"frw\",\"time\":\"Fri Jan 22 16:11:55 CST 2016\"}";
		//		String jsonStr="{\"time\":\"2016 16:11:55\"}";

		SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US); 
		try {
			sdf.parse("Fri Jan 22 16:11:55 CST 2016");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		DefaultJSONParser parser = new DefaultJSONParser((String) jsonStr, ParserConfig.getGlobalInstance()); 
		parser.setDateFomrat(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH));
		TestBean reqMsgJson = parser.parseObject(TestBean.class); 
		JSON.handleResovleTask(parser, reqMsgJson); 
		parser.close(); 

		System.out.println(JSON.toJSONString(reqMsgJson));

				ParserConfig.getGlobalInstance().putDeserializer(Date.class, new MyDateFormatDeserializer("EEE MMM dd HH:mm:ss z yyyy"));
		JSON.DEFFAULT_DATE_FORMAT="EEE MMM dd HH:mm:ss z yyyy";
//		ParserConfig.getGlobalInstance().p
		//		JSON.DEFFAULT_DATE_FORMAT="yyyy HH:mm:ss";
		TestBean test=JSON.parseObject(jsonStr,TestBean.class);

//		System.out.println(test.time);
				System.out.println(JSON.toJSONString(test));
		//		System.out.println(JSON.toJSON(test));

		//		TestBean tb=new TestBean();
		//		tb.id=11;
		//		tb.name="eeerer";
		//		tb.time=new Date("Sun Jan 11 00:00:00 CST 2015");
		//		System.out.println(JSON.toJSON(tb));
		//		JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
		//		System.out.println(JSON.toJSONString(tb));
	}
	static class MyDateFormatDeserializer extends DateFormatDeserializer {  
  
        private String myFormat;  
  
        public MyDateFormatDeserializer(String myFormat) {  
            super();  
            this.myFormat = myFormat;  
        }  
  
        @Override  
        protected <Date> Date cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object val) {  
            if (myFormat == null) {  
                return null;  
            }  
            if (val instanceof String) {  
                String strVal = (String) val;  
                if (strVal.length() == 0) {  
                    return null;  
                }  
  
                try {  
                    return (Date) new SimpleDateFormat(myFormat,Locale.ENGLISH).parse((String)val);  
                } catch (ParseException e) {  
                    throw new JSONException("parse error");  
                }  
            }  
            throw new JSONException("parse error");  
        }  
    }  
}
