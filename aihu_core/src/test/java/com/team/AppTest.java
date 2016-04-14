package com.team;

import com.team.util.UuidUtil;

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
       // assertTrue( true );
    	test();
    }
   public void testUuid(){
	   String uuid=UuidUtil.get32UUID();
	   System.out.println(uuid);
   }
   public void test(){
	   String x="12.34";
	   String  y=x.substring(0,x.indexOf("."));
	   Integer z=Integer.parseInt(y);
	   System.out.println(y);
	   
   }
    
}
