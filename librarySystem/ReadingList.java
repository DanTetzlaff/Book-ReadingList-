package librarySystem;

/**
 * contains contents of individual reading lists in the system
 * @author Daniel Tetzlaff
 * @version 2.0
 * Last Modified: Sept  9, 2014 - Created (By Daniel Tetzlaff)
 *                 Nov 14, 2014 - BST Implemented (Daniel Tetzlaff)  
 */

import java.util.*;
import dList.*;
import comparisonObjects.*;
import tree.*;
import tree.treeIterators.*;

public class ReadingList
{
    private String name;
    private String des;
    private StringNonCaseSensitiveCompare compare = new StringNonCaseSensitiveCompare();
    private BinarySearchTree<String, Book> books = new BinarySearchTree<String, Book>(compare);   

    public ReadingList(String name, String des)
    {
        super();
        this.name = name.trim();
        this.des = des.trim();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDes(String des)
    {
        this.des = des;
    }

    /**
     * adds a book to reading list from a file
     * @param name name of book to ensure it is not already in list
     * @param blc main system list of all books
     */
    public void addBook(String name, BinarySearchTree<String, Book> blc) throws Exception
    {
        BinaryTreeIterator<String, Book> iter = blc.getTraversalIterator(1);
        
        while (iter.hasNext())
        {
            String tmp = iter.getCurrentNode().getData().getTitle();
            if (tmp.equals(name))
            {
                books.add(name, iter.getCurrentNode().getData());
            }
            
            iter.next();
        }
    }

    /**
     * adds specific book to readinglist when passed the object
     * @param book is the object to be added to specific reading list
     */
    public void addBook(Book book)
    {
        books.add(book.getTitle(), book);
    }

    /**
     * removes a specific book from the reading list
     * @param title ensures proper book is removed
     */
    public void removeBook(String title) throws Exception
    {
        BinaryTreeIterator<String, Book> iter = books.getTraversalIterator(1);
        
        while (iter.hasNext())
        {
            String tmp = iter.getCurrentNode().getData().getTitle();
            if (tmp.equals(title))
            {
                books.remove(title);
            }
            
            iter.next();
        }
    }

    /**
     * checks for duplicate books in reading list
     * @param title of book used for comparison
     * @return result of true if book is already present in list, false if not
     */
    public boolean checkBooks(String title) throws Exception
    {
        return books.containskey(title);
    }

    public String getName()
    {
        return name;
    }

    public String getDes()
    {
        return des;
    }

    public int getNum()
    {
        return books.getSize();
    }

    public BinarySearchTree<String, Book> getList()
    {
        return books;
    }

    public String toString()
    {
        return "ReadingList [name=" + name + ", des=" + des + ", books="
        + books + "]";
    }

    /**
     * creates a string that can be printed to a file
     * @return string of reading list info, suitable for printing to a file
     */
    public String toPrint()
    {
        return name + "," + des +"\n";
    }

    /**
     * prints a formatted list of all books contained in list
     * @return formatted string of books contained
     */
    public String printBooks() throws Exception
    {
        String result = String.format("%3s| %-40s|%-30s|(%2s) %-12s %n", "#", "Book Title", "Author"," #", "Readin Lists");
        result += "--------------------------------------------------------------------------------------------------\n";
        BinaryTreeIterator<String, Book> iter = books.getTraversalIterator(1);
        int i = 1;
        
        while (iter.hasNext())
        {
            Book tmp = iter.getCurrentNode().getData();
            result += String.format("%3s| %-40s|%-30s|(%2s) %-12s %n", i, tmp.getTitle(), tmp.getAuthor(), tmp.getRL(), tmp.printList());
            i++;
            iter.next();
        }

        return result;
    }
}
