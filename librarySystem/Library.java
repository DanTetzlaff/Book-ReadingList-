package librarySystem;

/**
 * Main functionallity and control of the program, contains all readinglists and books
 * @author Daniel Tetzlaff
 * @version 3.0
 * 
 * Last Modified: Sept  9, 2014 - Created (Daniel Tetzlaff)
 *                 Nov  4, 2014 - adjustment of code design, simplification/improvements (Daniel Tetzlaff)
 *                 Nov 14, 2014 - BST implementation (Daniel Tetzlaff)
 */

import userCommunication.*;
import java.util.*;
import fileControl.*;
import dList.*;
import comparisonObjects.*;
import stacks.Stack;
import stacks.ArrayStack;
import tree.treeIterators.*;
import tree.*;

public class Library
{
    private UserInteraction comm = new UserInteraction();
    private Menu mainMenu = new Menu(comm);
    private StringNonCaseSensitiveCompare compare = new StringNonCaseSensitiveCompare();
    private BinarySearchTree<String, Book> booksContained = new BinarySearchTree<String, Book>(compare);
    private BinarySearchTree<String, ReadingList> rLContained = new BinarySearchTree<String, ReadingList>(compare);
    private newFile file;
    private Stack<String> history = new ArrayStack();

    /**
     * Initializes program and loops the users options to proceed within the program
     * @param fileName is used to pass the name of the file being used from args
     */
    public void run(String[] args)
    {
        try {
            String fileName;

            if (args.length == 0)            
                fileName = "readlingListDB";            
            else            
                fileName = args[0];           

            load(fileName);
            if (fileName.equals("")) { load("readlingListDB"); } //restarts program to load file newly created
            createMenu();
            int option;
            comm.clearBlueJTerminal();

            do // loop menu until user selects 'q' to quit
            {

                printRLC();
                comm.println("");
                printBLC();
                comm.println("");
                option = mainMenu.getUserChoiceInt();

                if ( option != 7 )                
                    processChoice(option);                

            }
            while ( option != 7 );

            save();
            comm.print("Program exited. Goodbye.");
        } catch (Exception e) {
            comm.println("an error has occured");
        }
    }

    /**
     * creates the main menu options for the program
     */
    private void createMenu()
    {
        mainMenu.addMenuOption( new IntValueMenuOption("A", "add book", 0) );
        mainMenu.addMenuOption( new IntValueMenuOption("R", "remove book", 1) );
        mainMenu.addMenuOption( new IntValueMenuOption("EB", "Edit a Book", 9) );
        mainMenu.addMenuOption( new IntValueMenuOption("AL", "add reading list", 2) );
        mainMenu.addMenuOption( new IntValueMenuOption("RL", "remove reading list", 3) );
        mainMenu.addMenuOption( new IntValueMenuOption("ER", "Edit a reading list", 8) );
        mainMenu.addMenuOption( new IntValueMenuOption("L", "list all books in a reading list", 4) );
        mainMenu.addMenuOption( new IntValueMenuOption("AR", "add book to a reading list", 5) );
        mainMenu.addMenuOption( new IntValueMenuOption("RR", "remove a book from a reading list", 6) );
        mainMenu.addMenuOption( new IntValueMenuOption("LA", "List all actions/changes that have occured", 10) );
        mainMenu.addMenuOption( new IntValueMenuOption("Q", "quit", 7) );
    }

    /**
     * intializes the requred method based on the choice the user gives
     * @param option is the number associated with the user's choice
     */
    private void processChoice(int option)
    {
        comm.println("user selected: " + option + "\n");
        try
        {
            switch (option) //selects method to required to perform task requested by user
            {
                case 0: addBook();          break;
                case 1: removeBook();       break;
                case 2: addRL();            break;
                case 3: removeRL();         break;
                case 4: listAllBooks();     break;
                case 5: addBookToRL();      break;
                case 6: removeBookFromRL(); break;
                case 8: editRL();           break;
                case 9: editBook();         break;
                case 10: listActions();     break;
            }
        }
        catch (Exception e)
        {
            comm.println("An error has occured");
        }
    }

    /**
     * adds books to the main system
     */
    private void addBook() throws Exception
    {
        String title = getNames("book title");

        if (bookInSystem(title)) //check if book is already in system
        {
            String author = getNames("author of " + title);

            booksContained.add(title, new Book(title, author));
            comm.println(title + " added successfully!\n");

            // push action onto history stack
            history.push("Added book: " + title);
        }
        else
        {
            comm.println("book already in system\n");
        }

        comm.pause();
    }

    /**
     * removes a book from the main system
     */
    private void removeBook() throws Exception
    {
        printBLC(); //print choices
        int value = 0;

        value = comm.getInput_IntBetween("Which book number would you like to remove?\n: ", 1, booksContained.getSize());

        String title = grabBook(value).getTitle(); //hold title to reduce code
        if (comm.yesNo("Are you sure you want to remove: " + title))
        {
            removeBfrmRL(title);

            booksContained.remove(title); //removal of book from main system
            comm.println("Book removed successfully\n");

            // push action onto history stack
            history.push("Removed book: " + title);
        }
        else
        {
            comm.println("No books were removed.\n");
        }

        save();
    }

    /**
     * adds a reading list to the main system
     */
    private void addRL() throws Exception
    {
        String name = getNames("name of reading list");

        if (rlInSystem(name))
        {
            String des = getNames("description of " + name);

            //adding of reading list to system
            rLContained.add(name, new ReadingList(name, des));
            comm.println(name + " added successfully!\n");

            // push action onto history stack
            history.push("Added list: " + name);
        }
        else
        {
            comm.println("Reading list already in system\n");
        }

        comm.pause();
    }

    /**
     * removes a reading list from the main system
     */
    private void removeRL() throws Exception
    {
        printRLC();
        int value = 0;

        value = comm.getInput_IntBetween("Which reading list number would you like to remove?\n: ", 1, rLContained.getSize());

        String name = grabRL(value).getName();
        if (comm.yesNo("Are you sure you want to remove: " + name))
        {
            //checks all books for the reading list, removes accordingly
             BinaryTreeIterator<String,Book> iter = booksContained.getTraversalIterator(1);
             
            while (iter.hasNext())
            {
                if (iter.getCurrentNode().getData().checkRLs(name))               
                    iter.getCurrentNode().getData().removeRL(name); 
                    
                iter.next();
            }

            rLContained.remove(name);
            comm.println("Reading list removed successfully\n");

            // push action onto history stack
            history.push("Removed list: " + name);
        }
        else
        {
            comm.println("No Reading lists were removed.\n");
        }

        save(); //immediatly applies removal change to file
    }

    /**
     * removes a book from reading lists it is contained in
     * @param title is the title of the book to be removed
     */
    private void removeBfrmRL(String title) throws Exception
    {
        //checks all reading lists for book and removes accordingly
        BinaryTreeIterator<String, ReadingList> iter = rLContained.getTraversalIterator(1);
        
        while (iter.hasNext())
        {
            if (iter.getCurrentNode().getData().checkBooks(title))
            {
                iter.getCurrentNode().getData().removeBook(title);
            }
            
            iter.next();
        }
    }

    /**
     * Edits a book's title and/or author
     */
    private void editBook() throws Exception
    {
        printBLC();
        int value;

        value = comm.getInput_IntBetween("Which book number would you like to edit?\n: ", 1, booksContained.getSize());

        //pulls book from system and stores information in temp vars
        Book tmp = booksContained.remove(grabBook(value).getTitle());
        String title = tmp.getTitle();
        removeBfrmRL(title);
        String Otitle = title;
        BinarySearchTree<String, ReadingList> rlTmp = tmp.getReadingList();

        if (comm.yesNo("Do you want to change the title of: " + Otitle + "?\n"))
        {
            int c = 0;
            while ( !bookInSystem(title) || title.equals(Otitle) || title.equals(""))
            {
                title = getNames("a new title of book");
                if (!bookInSystem(title) || title.equals(Otitle) || title.equals("") && c != 0) {comm.println("book already in system!"); c = -1;}
                c++;
            } 

            tmp.setTitle(title);
        }

        String author = tmp.getAuthor();
        String Oauthor = author;

        if (comm.yesNo("Do you want to change author of: " + title + "?\n"))
        {
            while (author.equals(Oauthor))           
                author = getNames("author of " + title);           

            tmp.setAuthor(author);
        }

        booksContained.add(title, tmp); //returns book to system

        for (int i = 0; i < rLContained.getSize(); i++)
        {
            ReadingList RLtmp = grabRL(i);
            if (tmp.checkRLs(RLtmp.getName()))          
                RLtmp.addBook(tmp);             
        }

        comm.println(title + " edited successfully!\n");

        // push action onto history stack
        history.push("Edited book: " + Otitle);
        comm.pause();
    }

    /**
     * Edits a reading list's name and/or description
     */
    private void editRL() throws Exception
    {
        printRLC();
        int value;

        value = comm.getInput_IntBetween("Which reading list number would you like to edit?\n: ", 1, rLContained.getSize());

        //temp vaiables for holding past information and used when transfering data
        ReadingList tmp = rLContained.remove(grabRL(value).getName()); //removes reading list and holds as temp
        String name = tmp.getName();
        String Oname = name;          
        BinarySearchTree<String, Book> bTmp = tmp.getList();

        //check if user wants to change name, processes the name change
        if (comm.yesNo("Do you want to change the name of: " + Oname + "?\n"))
        {
            int c = 0;
            while (name.equals(Oname) || !rlInSystem(name) || name.equals("")) //checks that title is more than a blank character
            {
                name = getNames("a new name of reading list");
                if (!rlInSystem(name) || name.equals(Oname) || name.equals("") && c != 0) {comm.println("name already in system!"); c = -1;}
                c++;
            }

            tmp.setName(name);
        }

        String des = tmp.getDes();
        String Odes = des;

        //check if user ants to change description, independant of name chnage
        if (comm.yesNo("Do you want to change the description of: " + tmp.getName() + "?\n"))
        {
            while (des.equals(Odes)) //ask user for description           
                des = getNames("description of " + name);            

            tmp.setDes(des);
        }

        rLContained.add(name, tmp); // returns edited reading list to system

        comm.println(name + " edited successfully!\n");

        // push action onto history stack
        history.push("Edited list: " + Oname);
        comm.pause();
    }

    /**
     * lists all books in a selected reading list
     */
    private void listAllBooks() throws Exception
    {
        if (rLContained.getSize() == 0)        
            comm.println("There are no lists in the system.\n");        
        else
        {
            printRLC();
            int value = 0;

            value = comm.getInput_IntBetween("Which list number would you like to list books from?\n: ", 1, rLContained.getSize());

            comm.println(grabRL(value).printBooks());
            // push action onto history stack
            history.push("Listed all books in \""+ grabRL(value).getName() +"\"");
            comm.pause();
        }
    }

    /**
     * adds a single book to a specific reading list
     */
    private void addBookToRL() throws Exception
    {
        if (rLContained.getSize() == 0) //step 1 check for lists in system        
            comm.println("There are no lists in the system.\n");       
        else
        {
            if (booksContained.getSize() == 0) //step 2 check for books in system            
                comm.println("There are no books in the system.\n");            
            else
            {
                printRLC();
                int rValue = 0;
                int bValue = 0;

                rValue = comm.getInput_IntBetween("Which list number would you like to add a book to?\n: ", 1, rLContained.getSize());

                printBLC();

                bValue = comm.getInput_IntBetween("Which book number would you like to add to reading list? ", 1, booksContained.getSize());

                ReadingList tmp = grabRL(rValue); //temp vars to reduce duplication of complicated code
                Book btmp = grabBook(bValue);

                if (tmp.checkBooks(btmp.getTitle())) //check if book is already in list
                {
                    comm.println("book selected is already in list\n");          
                }
                else // add book to reading list and reading list to book
                {
                    tmp.addBook(btmp);
                    btmp.addRL(tmp);
                    comm.println("Book added successfully\n");

                    // push action onto history stack
                    history.push("Added book \"" + btmp.getTitle() + "\" to reading list \"" + tmp.getName() + "\"");
                }

                comm.pause();
            }
        }
    }

    /**
     * removes a single book from a specified reading list
     */
    private void removeBookFromRL() throws Exception
    {
        if (rLContained.getSize() == 0) //check for lists in system 
            comm.println("There are no lists in the system\n");
        else
        {
            printRLC();
            int rValue = 0;

            // user selects which reading list to remove book from
            rValue = comm.getInput_IntBetween("Which list number would you like to remove from?\n: ", 1, rLContained.getSize());

            ReadingList tmp = grabRL(rValue);

            if (tmp.getNum() > 0) //check for books in list
            {
                comm.println(tmp.printBooks());
                BinarySearchTree<String, Book> tempList = tmp.getList();
                int bValue = 0;

                //have user select a book to remove
                bValue = comm.getInput_IntBetween("Which book number would you like to remove? ", 1, tempList.getSize());

                BinaryTreeIterator<String, Book> iter = booksContained.getTraversalIterator(1);

                for (int index = 1; index < bValue; index++)
                {
                    iter.next();
                }

                Book btmp = iter.getCurrentNode().getData();

                if (comm.yesNo("Are you sure you want to remove: " + btmp.getTitle())) //comfirm choice
                {
                    tmp.removeBook(btmp.getTitle()); // remove book from reading list
                    btmp.removeRL(tmp.getName());    // remover reading list from book
                    comm.println("Removed book successfully!\n");

                    // push action onto history stack
                    history.push("Removed book \"" + btmp.getTitle() + "\" from reading list \"" + tmp.getName() + "\"");
                }
                else
                {
                    comm.println("No books were removed\n");
                }
            }
            else
            {
                comm.println("No books to remove\n");
            }
        }

        save();
    }

    /**
     * loads a file for the system (if no file is entered it creates/uses a default file)
     * @param fileName is the name of the selected file
     */
    private void load(String fileName)
    {
        try
        {
            file = new newFile(fileName);
            readFile(file);
            comm.println("File loaded successfully!\n");
        }
        catch (Exception FileNotFoundException)
        {
            try
            {
                file = new newFile("readlingListDB.txt");
                readFile(file);
                comm.println("File loaded successfully!\n");
            }
            catch (Exception e)
            {
                file = new newFile('d');
                comm.println("Default file not found, program will create a new one.\n");
            }
        }

        comm.pause();
    }

    /**
     * checks if a reading list is already in the system
     * @param name is the name of reading list
     * @return true if same reading list name exists in system
     */
    private boolean rlInSystem(String name) throws Exception 
    {
        boolean add = true;

        for (int i = 1; i < rLContained.getSize() && add; i++) //checks if book is already in reading list
        {
            String tmp = grabRL(i).getName();
            if (tmp.equals(name))
                add = false;
            else
                add = true;
        }

        return add;
    }

    /**
     * checks if a book is already in the system
     * @param title of the book
     * @return true if the same book title exists in system
     */
    private boolean bookInSystem(String title) throws Exception
    {
        boolean add = true;

        for (int i = 1; i < booksContained.getSize() && add; i++) //checks for book already in system
        {
            String tmp = grabBook(i).getTitle();
            if (tmp.equals(title))
                add = false;
            else
                add = true;
        }

        return add;
    }

    /**
     * retrieves a name or title from the user
     * @param the thing that the user is being requested to name
     * @return the input from the user
     */
    private String getNames(String topic)
    {
        String response = "";
        while (response.equals(""))
            response = comm.getInput_String("Please enter " + topic + ": ");

        return response;
    }

    /**
     * receives a location based on the printed menu and finds book
     * @param location is the numerical location of book on menu
     * @return book object found in the tree
     */
    private Book grabBook(int location)
    {
        BinaryTreeIterator<String, Book> iter = booksContained.getTraversalIterator(1);

        for (int index = 1; index < location; index++)
        {
            iter.next();
        }

        return iter.getCurrentNode().getData();
    }

    /**
     * receives a location based on the printed menu and finds readinglist
     * @param location is the numerical location of reading list on menu
     * @return readinglist object found in the tree
     */
    private ReadingList grabRL(int location)
    {
        BinaryTreeIterator<String, ReadingList> iter = rLContained.getTraversalIterator(1);

        for (int index = 1; index < location; index++)
        {
            iter.next();
        }

        return iter.getCurrentNode().getData();
    }

    private void listActions()
    {
        Stack<String> tmp = new ArrayStack();
        String result = "Previous Actions:\n";
        int count = 1;

        while (!history.isEmpty())
        {
            result += String.format("(%3s) ", count);
            result += history.top() + "\n";
            tmp.push(history.pop());
            count++;
        }

        while (!tmp.isEmpty())        
            history.push(tmp.pop());

        comm.println(result);
        comm.pause();
    }

    /**
     * reads the contents of a given file
     * @param file is a newFile object which controls the reading a writing to a file
     */
    private void readFile(newFile file) throws Exception
    {
        String line = "";
        String[] info;
        String[] splitInfo;
        String[] rlInfo;

        do
        {
            if (file.hasNext())
            {
                line = file.nextLine();
                if (!line.equals("####"))
                {
                    info = parse(line, ",");
                    rLContained.add(info[0], new ReadingList(info[0], info[1]));   
                    
                    //rLContained.printAllNodes(1);
                    //comm.pause();
                    //comm.println("added RL: " + line); //debugging tool
                }
            }
            else
            {
                comm.println("File has no reading lists");
            }
        } 
        while (file.hasNext() && !line.equals("####"));

        do
        {
            if (file.hasNext())
            {
                line = file.nextLine();
                splitInfo = parse(line, "#");
                info = parse(splitInfo[0], ",");
                rlInfo = parse(splitInfo[1], ",");
                Book temp = new Book(info[0], info[1]);
                booksContained.add(info[0], temp);

                for (int c = 0; c < booksContained.getSize(); c++)
                {
                    if (temp.getTitle().equals(info[0]))
                        temp.setRL(rlInfo, rLContained, booksContained);
                }

                //booksContained.printAllNodes(1);
                //comm.pause();
                //comm.println("added B: " + line); //debugging tool
            }
            else
            {
                comm.println("File has no Books");
            }
        }
        while (file.hasNext());
    }

    /**
     * uses split to break up a string 
     * @param original is original string
     * @param delim is String determining point of splitting
     * @return array of split strings
     */
    private String[] parse(String original, String delim)
    {
        return original.split(delim);
    }

    /**
     * saves contents of system to a file
     */
    private void save()
    {
        try
        {
            file.writeSave(toString());
            comm.println("File saved Successfully!");
        }
        catch (Exception IOException)
        {
            comm.println("Error in saving file");
        }
        comm.pause();
    }

    /**
     * prints a formatted list of reading list information in system
     */
    private void printRLC() throws Exception
    {
        String result = String.format("%3s| %-40s|%-30s|%-15s %n", "#", "Reading List", "# of books in list", "Description");
        result += "--------------------------------------------------------------------------------------------------\n";
        BinaryTreeIterator<String, ReadingList> iter = rLContained.getTraversalIterator(1);
        int i = 1;

        while (iter.hasNext())
        {
            ReadingList tmp = iter.getCurrentNode().getData();
            result += String.format("%3s| %-40s|%-30s|%-15s %n", i, tmp.getName(), tmp.getNum(), tmp.getDes());
            i++;
            iter.next();
        }

        comm.println(result);
    }

    /**
     * prints a formatted list of book information in system
     */
    private void printBLC() throws Exception
    {
        String result = String.format("%3s| %-40s|%-30s|(%2s) %-12s %n", "#", "Book Title", "Author"," #", "Readin Lists");
        result += "--------------------------------------------------------------------------------------------------\n";
        BinaryTreeIterator<String, Book> iter = booksContained.getTraversalIterator(1);
        int i = 1;

        while (iter.hasNext())
        {
            Book tmp = iter.getCurrentNode().getData();
            result += String.format("%3s| %-40s|%-30s|(%2s) %-12s %n", i, tmp.getTitle(), tmp.getAuthor(), tmp.getRL(), tmp.printList());
            i++;
            iter.next();
        }

        comm.println(result);
    }

    /**
     * makes a string representaion of system info
     * @return a string in the same format as used in a file
     */
    public String toString()
    {
        String info = "";
        BinaryTreeIterator<String, ReadingList> rIter = rLContained.getTraversalIterator(3);
        BinaryTreeIterator<String, Book> bIter = booksContained.getTraversalIterator(3);

        try
        {
            while (rIter.hasNext())
            {
                info += rIter.getCurrentNode().getKey() + ",";
                info += rIter.getCurrentNode().getData().getDes();
                info += "\n";
                rIter.next();
            }
            info += "####\n";
            while (bIter.hasNext())
            {
                info += bIter.getCurrentNode().getKey() + ",";
                info += bIter.getCurrentNode().getData().getAuthor();
                info += " # ";
                info += bIter.getCurrentNode().getData().printList();
                info += "\n";
                bIter.next();
            }  
        }
        catch (Exception e)
        {
            comm.println("An error has occured");
        }

        return info;
    }
}
