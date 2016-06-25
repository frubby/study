package com.frw.dev.app;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	
    	JsonParser jparser=new JsonParser();
    	JsonObject obj=jparser.parse("{username:\"frw\"}").getAsJsonObject();
       	System.out.println(obj.get("username").toString());
    	System.out.println(obj.get("username").getAsString());
    	
    	System.out.println(obj.toString());
        assertTrue( true );
        
         
    }
}
