package userCommunication;
/**
 * Represents a single menu option that can be printed and selected by the user
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Feb 18, 2014 - Created (Jordan Kidney)
 */

public class MenuOption 
{
	private String option;
	private String description;

	/**
	 * Constructor 
	 * @param option the option/character the user will select
	 * @param description the description for this option
	 */
	public MenuOption(String option, String description) 
	{
		this.option = option.toLowerCase();
		this.description = description;
	}

	/**
	 * Determines if the given character matches the current menu option object
	 * @param usersChoice the char given by the user
	 * @return true for a match, false otherwise
	 */
    public boolean isAMatch(String usersChoice )
    {
      return (option.compareToIgnoreCase(usersChoice) == 0);	
    }
  
    public String getOptionUpperCase() { return option.toUpperCase(); }
    public String getOptionLowerCase() { return option.toLowerCase(); }
    
    public String getdescription(){ return description; }
    
    
    /**
     * Formated string for use when displaying all options for the menu
     * @param firstLength the minim number of characters used for spacing of the option string
     * @return
     */
    public String formatedOutput(int firstLength)
    {
    	return String.format("%"+firstLength+"s: %-20s", option, description);	
    }
    
	/**
	 *  Formated string for use when displaying all options for the menu
	 */
	public String toString()
	{
		return formatedOutput(5);
	}
}
