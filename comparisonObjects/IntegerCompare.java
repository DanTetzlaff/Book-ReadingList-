package comparisonObjects;
import java.util.Comparator;


public class IntegerCompare implements Comparator<Integer> 
{

	@Override
	public int compare(Integer obj1, Integer obj2) 
	{
		int result = 0;
		
		if(obj1 < obj2 ) result = -1;
		else if(obj1 == obj2 ) result = 0;
		else if (obj1 > obj2 ) result = 1;
		
		return result;
	}

}
