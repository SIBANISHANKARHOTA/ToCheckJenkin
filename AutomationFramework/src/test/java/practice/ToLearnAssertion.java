package practice;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ToLearnAssertion
{
	@Test
	public void sample()
	{
		System.out.println("Step 1");
		System.out.println("Step 2");
		//validation using Hard assert
//		Assert.assertEquals(false,true);//expect data is false and actual data is true
//		System.out.println("Step 3");
//		System.out.println("Step 4");
		
		//soft assert
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(false,true);
		System.out.println("Step 3");
		System.out.println("Step 4");
	//	sa.assertAll();	
	}
}