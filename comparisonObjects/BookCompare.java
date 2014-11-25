package comparisonObjects;

import java.util.*;
import librarySystem.*;

/**
 * Opperates comparasons of book titles
 * @author Daniel Tetzlaff
 * @version 1.0
 * 
 * Last Modified: Oct  6, 2014 - Created (Daniel Tetzlaff)
 */
public class BookCompare implements Comparator<Book>
{
    private StringNonCaseSensitiveCompare compare = new StringNonCaseSensitiveCompare();
    
    @Override
    public int compare(Book book1, Book book2)
    {
        return compare.compare(book1.getTitle(), book2.getTitle());
    }
}
