package stacks;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * JUnit test class to run all tests for each stack implementation
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Oct 9, 2014 - Created (Jordan Kidney)
 */

public class TestAllStacks 
{
	/**
	 * Runs all tests for each type of stack implementation
	 * @param args
	 */
	public static void main(String[] args) 
	{
		TestAllStacks tester = new TestAllStacks();
		tester.runSingleTestOfStack(new ArrayStack<Integer>());
		tester.runSingleTestOfStack(new LinkListStack<Integer>());
		tester.runSingleTestOfStack(new ArrayListStack<Integer>());
		tester.runSingleTestOfStack(new VectorStack<Integer>());
		
	}
	
	/**
	 * Runs all tests against a specific stack implementation 
	 * @param stackToTest the type of stack to test
	 * @return true if the test passed, false otherwise
	 */
	private boolean runSingleTestOfStack(Stack<Integer> stackToTest)
	{
		boolean testResult = false;
		System.out.println("============================================================================");
		System.out.println("Testing Stack: " + stackToTest.getClass().getName());
		
		TestStack.setTestStackImplimentationType( stackToTest.getClass() );
		Result result = JUnitCore.runClasses(TestStack.class);
		testResult = result.wasSuccessful();
		System.out.println("  Test Result: " + (testResult ? "PASSED ALL TESTS" : "FAIL"));
		
		for (Failure failure : result.getFailures()) 
		{
			System.out.println("\t FAIL: " + failure.toString());
		}

		return testResult;
	}



}
