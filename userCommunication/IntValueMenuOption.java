package userCommunication;
/**
 * Represents a single menu option that can be printed and selected by the user with an associated value
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Aug 20, 2014 - Created (Jordan Kidney)
 */
public class IntValueMenuOption extends MenuOption 
{
	private int data;
	
	/**
	 * Constructor 
	 * @param option the option/character the user will select
	 * @param description the description for this option
	 * @param data associated data value from the menu option 
	 */
	public IntValueMenuOption(String option, String description,int data)
	{
	  super(option,description);
	  this.data = data;
	}

	public int getData() { return data; }
}
