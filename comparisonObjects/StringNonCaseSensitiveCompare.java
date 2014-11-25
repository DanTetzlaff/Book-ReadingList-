package comparisonObjects;

 

import java.util.*;


public class StringNonCaseSensitiveCompare implements Comparator<String> 
{
	@Override
	public int compare(String obj1, String obj2) 
	{   
		int result = 0;
		int cmp = obj1.compareToIgnoreCase(obj2);
        
		if(cmp == 0) result = 0;
		else if( cmp < 0) result = -1;
		else
			result = 1;
	
		return result;
	}

}