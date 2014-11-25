package stacks;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;


public class StackTimer 
{
	public static final int MAX_OPERATION_AMT = 200; // the maximum number of operation executions included in timing 
	public static final int TIMING_REPS = 10000;


	@SuppressWarnings("rawtypes")
	private ArrayList<Class> stacksToTime;
	private PrintStream outFile;
	private Date runDateTime;
	
	
	@SuppressWarnings("rawtypes")
	public StackTimer()
	{
		stacksToTime = new ArrayList<Class>();
	}
	/**
	 * Add a stack to time
	 * @param stack the stack to time
	 */
	public void addStack(Stack<Integer> stack)
	{
		stacksToTime.add(stack.getClass() );
	}

	/**
	 * Times the provided stack for how long it takes to complete N push operations
	 * @param numPush the number of push operations to execute
	 * @param stackTypeToTest the stack to time
	 * @return the time to complete the task
	 */
	@SuppressWarnings("rawtypes")
	private long timePushOperation(int numPush,Class stackTypeToTest)
	{
		long startTime=0, endTime=0, time=0;

		try
		{
			@SuppressWarnings("unchecked")
			Stack<Integer> testStack = (Stack<Integer>) stackTypeToTest.newInstance();
			startTime = System.nanoTime();
			for(int count=0; count < numPush; count++)
				testStack.push(count);
			endTime = System.nanoTime();
			time = endTime - startTime;	   

		}
		catch(Exception ex) {}
		return time;
	}
	/**
	 * Times the provided stack for how long it takes to complete N pop operations
	 * @param numPop the number of pop operations to execute
	 * @param stackTypeToTest the stack to time
	 * @return the time to complete the task
	 */
	@SuppressWarnings("rawtypes")
	private long timePopOperation(int numPop,Class stackTypeToTest)
	{
		long startTime=0, endTime=0, time=0;

		try
		{
			@SuppressWarnings("unchecked")
			Stack<Integer> testStack = (Stack<Integer>) stackTypeToTest.newInstance();

			for(int count=0; count < numPop; count++)
				testStack.push(count);

			startTime = System.nanoTime();
			for(int count=0; count < numPop; count++)
				testStack.pop();
			endTime = System.nanoTime();

			time = endTime - startTime;	   

		}
		catch(Exception ex) {}
		return time;
	}
	/**
	 * Times the provided stack for how long it takes to complete N top operations
	 * @param numTop the number of top operations to execute
	 * @param stackTypeToTest the stack to time
	 * @return the time to complete the task
	 */
	@SuppressWarnings("rawtypes")
	private long timeTopOperation(int numTop,Class stackTypeToTest)
	{
		long startTime=0, endTime=0, time=0;

		try
		{
			@SuppressWarnings("unchecked")
			Stack<Integer> testStack = (Stack<Integer>) stackTypeToTest.newInstance();

			for(int count=0; count < numTop; count++)
				testStack.push(count);

			startTime = System.nanoTime();
			for(int count=0; count < numTop; count++)
				testStack.top();
			endTime = System.nanoTime();

			time = endTime - startTime;	   

		}
		catch(Exception ex) {}
		return time;
	}
	/**
	 * Gets the average time for executing N push operations on the specific test
	 * This helps to remove random time values due to process scheduling  
	 * @param n the number of push operations to run
	 * @param stackTypeToTest the stack to time push operations for
	 * @return the average time
	 */
	@SuppressWarnings("rawtypes")
	private long getTimeaveragePushOperation(int n,Class stackTypeToTest)
	{
		long average = 0;

		for(int count=0; count < TIMING_REPS; count++)
			average += timePushOperation(n,stackTypeToTest);

		return average/TIMING_REPS;
	}
	/**
	 * Gets the average time for executing N pop operations on the specific test
	 * This helps to remove random time values due to process scheduling  
	 * @param n the number of pop operations to run
	 * @param stackTypeToTest the stack to time pop operations for
	 * @return the average time
	 */
	@SuppressWarnings("rawtypes")
	private long getTimeaveragePopOperation(int n,Class stackTypeToTest)
	{
		long average = 0;

		for(int count=0; count < TIMING_REPS; count++)
			average += timePopOperation(n,stackTypeToTest);

		return average/TIMING_REPS;
	}

	/**
	 * Gets the average time for executing N top operations on the specific test
	 * This helps to remove random time values due to process scheduling  
	 * @param n the number of top operations to run
	 * @param stackTypeToTest the stack to time top operations for
	 * @return the average time
	 */
	@SuppressWarnings("rawtypes")
	private long getTimeaverageTopOperation(int n,Class stackTypeToTest)
	{
		long average = 0;

		for(int count=0; count < TIMING_REPS; count++)
			average += timeTopOperation(n,stackTypeToTest);

		return average/TIMING_REPS;
	}

	/**
	 * Times all operations for each type of stack, each operation
	 * is timed for how long it takes to execute n repetitions of the same operation
	 * @param n the number of repetition for each operations
	 */
	@SuppressWarnings("rawtypes")
	private void timeOperationsForN(int n)
	{

		outFile.print(""+n);
		for(Class cur : stacksToTime)
		{
			long popTime  = getTimeaveragePopOperation(n,cur);
			outFile.print(","+popTime);
		}
		outFile.print(",");
		for(Class cur : stacksToTime)
		{
			long topTime  = getTimeaverageTopOperation(n,cur);
			outFile.print(","+topTime);
		}
		outFile.print(",");
		for(Class cur : stacksToTime)
		{
			long pushTime = getTimeaveragePushOperation(n,cur);
			outFile.print(","+pushTime);
		}
		outFile.println();
	}

	/**
	 * Prints basic comp and user information to the output file
	 */
	private void printUserAndSystemInfo()
	{

		outFile.println("PROCESSOR ARCH,"+System.getenv("PROCESSOR_ARCHITECTURE"));
		try {
			outFile.println("COMP NAME,"+InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}

		
		outFile.println("USER,"+System.getProperty("user.name"));
		outFile.println("DATE,"+String.format("%tB-%<te-%<tY",runDateTime));
		outFile.println("TIME,"+String.format("%tH-%<tM-%<tS",runDateTime));
	}

	/**
	 * writes table headers to the file
	 */
	private void writeHeaders()
	{
		outFile.print("N");
		for(Class cur : stacksToTime)
			outFile.print(",POP-"+cur.getSimpleName());
		outFile.print(",");
		for(Class cur : stacksToTime)
			outFile.print(",TOP-"+cur.getSimpleName());
		outFile.print(",");
		for(Class cur : stacksToTime)
			outFile.print(",PUSH-"+cur.getSimpleName());
		
		outFile.println();
	}
	
	/**
	 * Times all operations for each stack currently in the time.
	 * Test is Based upon the time it takes to execute multiple executions of the same operation
	 * Test results will be saved to a csv file in the current working directory
	 */
	@SuppressWarnings("rawtypes")
	public void timeAllOperations()
	{
		try 
		{
			//Create CSV file
			runDateTime = new Date();
			String fileName = String.format("StackTimeResults-%tB-%<te-%<tY-%<tH-%<tM-%<tS.csv",runDateTime);
			outFile = new PrintStream(new FileOutputStream(fileName));

			printUserAndSystemInfo();
			//stackTypeToTest.getSimpleName();

			writeHeaders();

			System.out.println("Timing stacks, this may take a while");
			for(int n=1; n < MAX_OPERATION_AMT; n++ )
			{
				timeOperationsForN(n);
				if(n%10==0)
				{
					double value = ((double)n)/((double)MAX_OPERATION_AMT);
					value*= 100.0;
					System.out.format("%3.2f%% done\n", value);
				}
			}
			System.out.println("Done");

			outFile.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("ERROR: unable to write times out to file");
			e.printStackTrace();
		}    

	}
}
