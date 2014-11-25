import librarySystem.*;

/**
 * Main startup point for the program
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Aug 19, 2014 - Created (Jordan Kidney)
 */
public class Loader 
{
    public static void main(String[] args)
    {
        Library bookSystem = new Library();
        bookSystem.run(args);
    }
}
