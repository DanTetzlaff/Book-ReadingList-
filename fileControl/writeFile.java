package fileControl;

/**
 * prints information to the currently opened file, will save once closed if users chooses such
 * @author Daniel Tetzlaff
 * @version 1.0
 * Last Modified: Mar 10, 2014 - Created (By Daniel Tetzlaff)
 */

import java.io.*;
public class writeFile
{
    private PrintStream outFile;
    
    public writeFile(FileOutputStream file) throws IOException
    {
        outFile = new PrintStream(file);
    }
    
    public void printLine(String line)
    {
        outFile.println(line);
    }
    
    public void printInt(int num)
    {
        outFile.println(num);
    }
    
    public void skipLine()
    {
        outFile.println();
    }
    
    public void save() throws IOException
    {
        outFile.close();
    }
    
    public void writeSave(String libraryInfo, FileOutputStream save) throws IOException
    {
        PrintStream saveFile = new PrintStream(save);
        
        saveFile.print(libraryInfo);
        
        save.close();
    }
}
