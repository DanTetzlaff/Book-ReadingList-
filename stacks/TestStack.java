package stacks;
/**
 * JUnit test class for A stack
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Oct 9, 2014 - Created (Jordan Kidney)
 */
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;



public class TestStack 
{

	@SuppressWarnings("rawtypes")
	private static Class specificStackImplimentationType = null;
	@SuppressWarnings("rawtypes")
	public static void setTestStackImplimentationType(Class type) { specificStackImplimentationType = type; }

	private Stack<Integer> testStack;


	/**
	 * Helper method to load stack with values and verify that sie() and isEmpty are correct
	 * @param values
	 */
	private void pushAndVerifySize(int[] values)
	{
		pushAndVerifySize(values, values.length);
	}
	
	private void pushAndVerifySize(int[] values, int expectedSize)
	{
		for(int value : values) testStack.push(value);
		assertEquals("Stack is empty after pushes (size() returned wrong value)",expectedSize, testStack.size());
		assertFalse("Stack is empty after pushes (isEmpty() returned wrong value)", testStack.isEmpty());
	}

	/**
	 * Pops a single value off the stack and verify the value and size is correct
	 * @param expectedValue
	 * @param expectedSizeAfterPop
	 */
	private void verifyPop(int expectedValue, int expectedSizeAfterPop, boolean shouldBeEmpty )
	{
		int popedValue = (Integer) testStack.pop();

		assertEquals("Poped value does not match last pushed value",expectedValue, popedValue);
		assertEquals("Stack is not empty after pop (size() returned wrong value)",expectedSizeAfterPop, testStack.size());

		if(shouldBeEmpty)
			assertTrue("Stack is not empty after pop (isEmpty() returned wrong value)", testStack.isEmpty());
		else
			assertFalse("Stack is empty after pop (isEmpty() returned wrong value)", testStack.isEmpty());
	}

	/**
	 * does a top and verify the value and size is correct
	 * @param expectedValue
	 * @param expectedSizeAfterPop
	 */
	private void verifyTop(int expectedValue, int expectedSizeAfterTop)
	{
		int topValue = (Integer) testStack.top();

		assertEquals("Top value does not match last pushed value",expectedValue, topValue);
		assertEquals("Stack size if wrong after top (size() returned wrong value)",expectedSizeAfterTop, testStack.size());
        assertFalse("Stack is empty after pop (isEmpty() returned wrong value)", testStack.isEmpty());
	}


	/**
	 * Called before the start of each test case below is run ( this is done by JUnit)
	 */
	@SuppressWarnings("unchecked")
	@Before
	public void setUp()
	{
		if(specificStackImplimentationType == null)
			throw new RuntimeException("Test Class type not set, Do not run this class for the tests");

		try 
		{
			testStack = (Stack<Integer>) specificStackImplimentationType.newInstance();
		} 
		catch (Exception error) 
		{
			throw new RuntimeException("Error: Unable to create stack to test");
		}
	}


	/**
	 *  Verifies that the stack is empty when it is first created
	 */
	@Test
	public void test_stack_is_empty_at_start() 
	{
		try
		{
			assertEquals("Stack is not empty when first created (size() returned wrong value)",0, testStack.size());
			assertTrue("Stack is not empty when first created (isEmpty() returned wrong value)", testStack.isEmpty());
		}
		catch(Exception ex)
		{
			fail("Fail: Exception caught: " + ex.getMessage());
		}
	}

	/**
	 *  Verifies that pop throws the proper exception when the stack is empty
	 */
	@Test
	public void test_stack_exception_check_pop_on_empty_stack() 
	{
		try
		{

			testStack.pop();
			fail("Pop() did not throw an exception of type EmptyStackException");
		}
		catch(EmptyStackException error)
		{
			// it it reaches here the test has passed
		}
		catch(Exception ex)
		{
			fail("top() threw the wrong type of exception");
		}
	}

	/**
	 *  Verifies that top throws the proper exception when the stack is empty
	 */
	@Test
	public void test_stack_exception_check_top_on_empty_stack() 
	{
		try
		{
			testStack.top();
			fail("top() did not throw an exception of type EmptyStackException");
		}
		catch(EmptyStackException error)
		{
			// it it reaches here the test has passed
		}
		catch(Exception ex)
		{
			fail("top() threw the wrong type of exception");
		}
	}

	/**
	 *  Verifies a single push and pop
	 */
	@Test
	public void test_stack_single_push_and_pop() 
	{
		try
		{
			int[] values = {5};
			pushAndVerifySize(values);
			verifyPop(5,0, true);
		}
		catch(Exception ex)
		{
			fail("Fail: Exception caught: " + ex.getMessage());
		}
	}

	/**
	 *  Verifies a single push and top
	 */
	@Test
	public void test_stack_single_push_and_top() 
	{
		try
		{
			int[] values = {5};
			pushAndVerifySize(values);
			verifyTop(5, 1);
		}
		catch(Exception ex)
		{
			fail("Fail: Exception caught: " + ex.getMessage());
		}
	}

	/**
	 *  Verifies a single pop after multiple push
	 */
	@Test
	public void test_stack_multiple_push_and_single_pop() 
	{
		try
		{
			int[] values = {5,6,8,1,9};
			pushAndVerifySize(values);
			verifyPop(9,4,false);
		}
		catch(Exception ex)
		{
			fail("Fail: Exception caught: " + ex.getMessage());
		}
	}

	/**
	 *  Verifies a single top after multiple push
	 */
	@Test
	public void test_stack_multiple_push_and_single_top() 
	{
		try
		{
			int[] values = {5,6,8,1,9};
			pushAndVerifySize(values);
			verifyTop(9, values.length);
		}
		catch(Exception ex)
		{
			fail("Fail: Exception caught: " + ex.getMessage());
		}
	}
	
	/**
	 *  Verifies multiple pushes followed by popping off everything from the stack
	 */
	@Test
	public void test_stack_multiple_push_and_pop_off_everything() 
	{
		try
		{
			int[] values = {5,6,8,1,9};
			pushAndVerifySize(values);
			boolean expectEmpty = false;
			
			for(int index = values.length-1; index < values.length; index++)
			{
				if(index == 0) expectEmpty = true;
				
				verifyPop(values[index],index,expectEmpty);
			}
			
		}
		catch(Exception ex)
		{
			fail("Fail: Exception caught: " + ex.getMessage());
		}
	}
	
	/**
	 *  Verifies random pushes and pops in sequence
	 */
	@Test
	public void test_stack_random_push_and_pop() 
	{
		try
		{
			int[] values1 = {5,6,8,1,9};
			int[] values2 = {10,20,30};
			int size2 = (values1.length-2) + values2.length;
			pushAndVerifySize(values1);
			verifyPop(9, values1.length-1, false);
			verifyPop(1, values1.length-2, false);
			
			pushAndVerifySize(values2, size2);
			verifyPop(30, size2-1, false);
			verifyPop(20, size2-2, false);
			
		}
		catch(Exception ex)
		{
			fail("Fail: Exception caught: " + ex.getMessage());
		}
	}
	
	/**
	 *  Verifies VERY large amount of pushes to the stack
	 */
	@Test
	public void test_stack_large_amount_added() 
	{
		try
		{
			System.out.print("    Running large number of pushes. This may take a while to run ... ");
			int numToAdded = 5000000;
			
			for(int count=0; count < numToAdded; count++)
				testStack.push(count);
			
			System.out.println(" Done."); 
			assertEquals("Stack is empty after pushes (size() returned wrong value)", numToAdded, testStack.size());
			assertFalse("Stack is empty after pushes (isEmpty() returned wrong value)", testStack.isEmpty());
			
		}
		catch(Exception ex)
		{
			fail("Fail: Exception caught: " + ex.getMessage());
		}
	}
}
