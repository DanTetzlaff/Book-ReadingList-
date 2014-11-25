package userCommunication;
/**
 * Represents a single menu option that can be printed and selected by the user with an associated value
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Aug 20, 2014 - Created (Jordan Kidney)
 */
public class DoubleValueMenuOption extends MenuOption 
{
	private double data;
	
	/**
	 * Constructor 
	 * @param option the option/character the user will select
	 * @param description the description for this option
	 * @param data associated data value from the menu option 
	 */
	public DoubleValueMenuOption(String option, String description,double data)
	{
	  super(option,description);
	  this.data = data;
	}

	public double getData() { return data; }
}
