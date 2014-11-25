package comparisonObjects;
import java.util.*;


public class StringCaseSensitiveCompare implements Comparator<String> 
{
	@Override
	public int compare(String obj1, String obj2) 
	{
		return obj1.compareTo(obj2);
	}

}
