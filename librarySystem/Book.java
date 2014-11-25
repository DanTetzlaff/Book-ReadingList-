package librarySystem;

/**
 * Contained contents of individual books in the system
 * @author Daniel Tetzlaff
 * @version 2.0
 * Last Modified: Sept  9, 2014 - Created (By Daniel Tetzlaff)
 *                 Nov 14, 2014 - BST Implementation (Daniel Tetzlaff)
 */

import java.util.*;
import dList.*;
import comparisonObjects.*;
import tree.*;
import tree.treeIterators.*;

public class Book
{
    private String title;
    private String author;
    private StringNonCaseSensitiveCompare compare = new StringNonCaseSensitiveCompare();
    private BinarySearchTree<String, ReadingList> readingLists = new BinarySearchTree<String, ReadingList>(compare);

    /**
     * basic contructor for a book
     * @param title is the Title of the book
     * @param author is the author of the book
     */
    public Book(String title, String author)
    {
        super();
        this.title = title.trim();
        this.author = author.trim();
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    /**
     * sets reading lists books is contained in based on file
     * @param rlInfo is a list of string containing the information for a reading list
     * @param rl main system list of all reading lists
     * @param blc main system list of all books
     */
    public void setRL(String[] rlInfo, BinarySearchTree<String, ReadingList> rl, BinarySearchTree<String, Book> blc) throws Exception
    {
        BinaryTreeIterator<String, ReadingList> iter = rl.getTraversalIterator(1);

        while (iter.hasNext())
        {
            String nme = iter.getCurrentNode().getData().getName();

            for (int index = 0; index < rlInfo.length; index++)
            {
                String tmp = rlInfo[index].trim();

                if (tmp.equals(nme))
                {
                    readingLists.add(nme, iter.getCurrentNode().getData());

                    iter.getCurrentNode().getData().addBook(title, blc);
                    //System.out.println("--- " + nme + " --- " + tmp); //Debugging tool
                }

            }
            iter.next();
        }
    }

    /**
     * adds a single reading list object
     * @param rl is a passed through reading list object
     */
    public void addRL(ReadingList rl)
    {
        readingLists.add(rl.getName(), rl);
    }

    /**
     * removes a reading list from the specific book
     * @param name ensures the proper reading list is removed
     */
    public void removeRL(String name) throws Exception
    {
        readingLists.remove(name);
    }

    /**
     * checks for a reading list with same name
     * @param name being tested 
     * @return true if same reading list is found
     */
    public boolean checkRLs(String name) throws Exception
    {
        return readingLists.containskey(name);
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public int getRL()
    {
        return readingLists.getSize();
    }

    public BinarySearchTree<String, ReadingList> getReadingList()
    {
        return readingLists;
    }

    public String toString()
    {
        return "Book [title=" + title + ", author=" + author + ", readingLists="
        + readingLists + "]";
    }

    /**
     * provides a string of book info for printing to a file
     * @return string in file reading format created
     */
    public String toPrint() throws Exception
    {
        return title + ", " + author + " # " + printList();
    }

    /**
     * prints reading lists that book belongs to
     * @return list of reading list names seperated by comma
     */
    public String printList() throws Exception
    {
        String print = "";
        BinaryTreeIterator<String, ReadingList> iter = readingLists.getTraversalIterator(1);

        while (iter.hasNext())
        {
            print += iter.getCurrentNode().getData().getName();
            if ( iter.hasNext() )
            {
                print += ",";  
            }
            iter.next();
        }

        return print;
    }
}
