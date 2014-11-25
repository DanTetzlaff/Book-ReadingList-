package userCommunication;

/**
 * Methods used to interact with the user 
 * @author Jordan Kidney
 * @version 5.0
 * 
 * Last Modified: 
 *                  Aug 18, 2014 - added new features (Jordan Kidney)
 *                March 13, 2014 - added getInput_IntBetween method and depubg message functionality
 *                  Feb 10, 2014 - complete redesign of the class
 *                  Jan  8, 2014 - Added yesNo Method
 *                  Jan  7, 2013 - Created
 */
import java.util.*;

public class UserInteraction

{
	//class variables and methods used for control to debugging messages and access
	private static boolean debug = false;
    private static UserInteraction singleInstance = null;
    
    private String endOfLine;
    
  
    
    /**
     * Basic singleton design pattern 
     * @return
     */
    public static UserInteraction getInstance()
    {
    	if(singleInstance == null)
    		singleInstance = new UserInteraction();
    	
    	return singleInstance;
    }
	
	
	/**
	 * used to toggle the state of debug messages printing
	 */
	public static void toggleDebug() {  debug = !debug; }
	public static void setDebug(boolean value) { debug = value; }

	//Instance variables
	public static final char NO_CHAR_INPUT = ' ';   //default for character input
	private  Scanner input;


	/**
	 * default constructor
	 */
	public UserInteraction()
	{
		input = new Scanner(System.in); 
		endOfLine = System.getProperty("line.separator");
	}

	/**
	 * method to contain printing of messages to the console
	 * @param message the message to print
	 */
	public void print(String message)
	{
		System.out.print(message); 
	}
	/**
	 * method to contain printing of objects to the console (calls toString())
	 * @param obj the object to print
	 */
	public void print(Object obj)
	{
		System.out.print(obj); 
	}


	/**
	 * method to contain printing of messages to the console with a new line
	 * @param message the message to print
	 */
	public void println(String message)
	{
		System.out.println(message); 
	}

	/**
	 * method to contain printing of objects to the console (calls toString())
	 * @param obj the object to print
	 */
	public void println(Object obj)
	{
		System.out.println(obj); 
	}
	/**
	 * method to contain printing of messages to the console with a new line in debug mode
	 * @param message the message to print
	 */
	public void println_debug(String message)
	{
		if(debug) System.out.println("DEBUG: " + message); 
	}

	

	/**
	 * method to contain printing of messages to the console specific to errors. 
	 * @param message the message to print
	 */
	public void println_error(String message)
	{
		System.out.println("ERROR: " + message); 
	}
	

	/**
	 * Writes a formatted string to this output stream using the specified format string and arguments. 
	 * The locale always used is the one returned by Locale.getDefault(), regardless of any previous 
	 * invocations of other formatting methods on this object.
	 * @param format A format string as described in Format string syntax
	 * @param args Arguments referenced by the format specifiers in the format string. If there are more arguments than format specifiers, the extra arguments are ignored. The number of arguments is variable and may be zero. The maximum number of arguments is limited by the maximum dimension of a Java array as defined by The Java™ Virtual Machine Specification. The behaviour on a null argument depends on the conversion.
	 */
	public void printFormat(String format, Object... args)
	{
		System.out.format(format, args);
	}
	
	/**
	 * Writes a formatted string to this output stream using the specified format string and arguments. 
	 * The locale always used is the one returned by Locale.getDefault(), regardless of any previous 
	 * invocations of other formatting methods on this object.
	 * @param format A format string as described in Format string syntax
	 * @param args Arguments referenced by the format specifiers in the format string. If there are more arguments than format specifiers, the extra arguments are ignored. The number of arguments is variable and may be zero. The maximum number of arguments is limited by the maximum dimension of a Java array as defined by The Java™ Virtual Machine Specification. The behaviour on a null argument depends on the conversion.
	 */
	public void printlnFormat(String format, Object... args)
	{
		System.out.format(format + endOfLine, args);
	}
	
	/**
	 * Displays a message and waits to read an integer from the user
	 * @param message the message to display
	 * @return the integer inputed by the user
	 */
	public int getInput_Int(String message)
	{
		int userInput = 0;
		print(message);
		userInput = input.nextInt();
		input.nextLine(); // remove the newline character entered by the user
		return userInput;   
	}

	/**
	 * Displays a message and waits to read an integer from the user. Verifies
	 * that the entered number is greater than or equal to smallestValue
	 * @param message the message to display
	 * @param smallestValue the smallest possible legal value the user can enter
	 * @return the integer inputed by the user
	 */
	public int getInput_IntGreaterThan(String message, int smallestValue)
	{
		int userInput = 0;
		boolean end = false;

		do
		{
			print(message);
			userInput = input.nextInt();
			input.nextLine(); // remove the newline character entered by the user

			if( userInput < smallestValue)
			{
				println("Error: value must be >= " + smallestValue);
			}
			else
				end = true;

		}
		while(!end);

		return userInput;   
	}

	/**
	 * Displays a message and waits to read an integer from the user. Verifies
	 * that the entered number is between the provided values (inclusive)
	 * @param message the message to display
	 * @param smallestValue the smallest possible legal value the user can enter
	 * @param largestValue the largest possible legal value the user can enter
	 * @return the integer inputed by the user
	 */
	public int getInput_IntBetween(String message, int smallestValue,int largestValue)
	{
		int userInput = 0;
		boolean end = false;

		do
		{
			print(message);
			userInput = input.nextInt();
			input.nextLine(); // remove the newline character entered by the user

			if( userInput < smallestValue || userInput > largestValue)
			{
				println("Error: value must be >= " + smallestValue + " <= " + largestValue);
			}
			else
				end = true;

		}
		while(!end);

		return userInput;   
	}

	/**
	 * gets a double from the input without displaying a message
	 * @param message the message to display
	 * @return the entered double
	 */
	public double getInput_Double(String message)
	{
		double userInput = 0;
		print(message);
		userInput = input.nextDouble();
		input.nextLine(); // remove the newline character entered by the user
		return userInput;   
	}
	
	/**
	 * Displays a message and waits to read an double from the user. Verifies
	 * that the entered number is greater than or equal to smallestValue
	 * @param message the message to display
	 * @param smallestValue the smallest possible legal value the user can enter
	 * @return the double inputed by the user
	 */
	public double getInput_DoubleGreaterThan(String message, double smallestValue)
	{
		double userInput = 0;
		boolean end = false;

		do
		{
			print(message);
			userInput = input.nextDouble();
			input.nextLine(); // remove the newline character entered by the user

			if( userInput < smallestValue)
			{
				println("Error: value must be >= " + smallestValue);
			}
			else
				end = true;

		}
		while(!end);

		return userInput;   
	}

	/**
	 * Displays a message and waits to read an double from the user. Verifies
	 * that the entered number is between the provided values (inclusive)
	 * @param message the message to display
	 * @param smallestValue the smallest possible legal value the user can enter
	 * @param largestValue the largest possible legal value the user can enter
	 * @return the double inputed by the user
	 */
	public double getInput_DoubleBetween(String message, double smallestValue,double largestValue)
	{
		double userInput = 0;
		boolean end = false;

		do
		{
			print(message);
			userInput = input.nextDouble();
			input.nextLine(); // remove the newline character entered by the user

			if( userInput < smallestValue || userInput > largestValue)
			{
				println("Error: value must be >= " + smallestValue + " <= " + largestValue);
			}
			else
				end = true;

		}
		while(!end);

		return userInput;   
	}

	/**
	 * clears one line from the input console
	 */
	public void clearInputLine()
	{
		input.nextLine();  
	}

	/**
	 * gets an integer from the input without displaying a message
	 * @return the entered integer
	 */
	public int getInput_Int()
	{
		int userInput = 0;
		userInput = input.nextInt();
		input.nextLine();
		return userInput;   
	}

	/**
	 * Displays a message and waits to read a line of text from the user
	 * @param message the message to display
	 * @return the line inputed by the user
	 * */
	public String getInput_String(String message)
	{
		print(message);
		return input.nextLine().trim();
	}

	/**
	 * reads one character from the input
	 * @param message the message to display
	 * @return the entered character or NO_CHAR_INPUT if no input
	 */
	public char getInput_char(String message)
	{
		char userData = NO_CHAR_INPUT;
		print(message);
		String userReply = input.nextLine().toUpperCase();
		// make sure the user did not just hit enter
		if(!userReply.isEmpty())
			userData = userReply.charAt(0);

		return userData;
	}

	/**
	 * reads one character from the input and validates that a valid character has been entered
	 * @param message the message to display
	 * @param validChars a string that contains all valid characters that can be entered
	 * @return the entered character or NO_CHAR_INPUT if no input
	 */
	public char getInput_char(String message, String validChars)
	{
		char userData = NO_CHAR_INPUT;
		String userReply = "";
		boolean end = false ;
		validChars = validChars.toUpperCase();
		do
		{
			userData = NO_CHAR_INPUT;
			print(message);
			userReply = input.nextLine().toUpperCase();
			// make sure the user did not just hit enter
			if(!userReply.isEmpty())
				userData = userReply.charAt(0);

			// check to see if the entered char is among the valid chars
			if(validChars.indexOf(userData) == -1)
				System.out.println("Error: wrong input");
			else
				end = true;

		} while(!end);

		return userData;
	}

	/**
	 * Presents the user with a yes/no question used for verification. Adds the (y,n) to the provided message
	 * @param message the message to display
	 * @return true if the user answered yes, false otherwise
	 */
	public boolean yesNo(String message)
	{
		String userReply = "";
		boolean result = false;
		System.out.print(message + "(y,n)");
		userReply = input.nextLine();
		// make sure the user did not just hit enter
		if(!userReply.isEmpty())
		{
			switch(userReply.charAt(0))
			{
			case 'y':
			case 'Y': result = true;
			break;
			}
		}

		return result;
	}

	/**
	 * pauses the program until the user hits enter 
	 */
	public void pause()
	{
		System.out.println("<hit enter to continue>");
		input.nextLine();
	}

	/**
	 * Can be used to clear the bluej terminal of all text
	 */
	public void clearBlueJTerminal()
	{
		if(!debug) System.out.print('\u000C');
	}
}
