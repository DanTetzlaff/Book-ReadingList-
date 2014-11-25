package comparisonObjects;

import librarySystem.*;
import java.util.*;

/**
 * Operates omparisons of readinglist names
 * @author Daniel Tetzlaff
 * @version 1.0
 * 
 * Last Modified: Oct  6, 2014 - Created (Daniel Tetzlaff)
 */
public class ReadingListCompare implements Comparator<ReadingList>
{
    private StringNonCaseSensitiveCompare compare = new StringNonCaseSensitiveCompare();
    
    @Override
    public int compare(ReadingList rl1, ReadingList rl2)
    {
        return compare.compare(rl1.getName(), rl2.getName());
    }
}
