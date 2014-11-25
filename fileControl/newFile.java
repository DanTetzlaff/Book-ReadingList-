package fileControl;

/**
 * interacts with files, opens and creates an object for a current file, organizes reading and writing functions
 * @author Daniel Tetzlaff
 * @verison 1.0
 * Last Modified: Mar 10, 2014 - Created (By Daniel Tetzlaff)
 *                Sept 10, 2014 - Edited for specific program use (By Daniel Tetzlaff)
 */

import java.io.*;
public class newFile
{
    private readFile reader;
    private writeFile writer;
    private FileInputStream file;
    String fileName;

    public newFile(String name) throws FileNotFoundException, IOException
    {
        fileName = parse(name);
        file = new FileInputStream("files/"+fileName);
        FileOutputStream edit = new FileOutputStream("files/temp_"+parse(fileName));
        reader = new readFile(file);
        writer = new writeFile(edit);
    }

    public newFile(char d) 
    {
        if (d == 'd')
        {
            try
            {
                FileOutputStream edit = new FileOutputStream("files/readlinglistDB.txt");
                edit.close();
            }
            catch (Exception e)
            {
                System.out.println("Error in setting default file");
            }
        }
    }

    private String parse(String original)
    {
        String fileName;
        String[] broken = original.split("\\.");

        if ( !broken[broken.length - 1].equals("txt") )
        {
            fileName = original + ".txt";
        }
        else
        {
            fileName = original;
        }

        return fileName;
    }

    public String nextLine()
    {
        return reader.getLine();
    }

    public boolean hasNext()
    {
        return reader.hasNext();
    }

    public void writeLine(String contents) throws IOException
    {
        writer.printLine(contents);
    }

    public void close() throws IOException
    {
        writer.save();
    }

    public void writeInt(int num)
    {
        writer.printInt(num);
    }

    public int nextInt()
    {
        int value = 0;

        if ( reader.hasInt() )
        {
            value = reader.getInt();
            reader.getLine();
        }

        return value;
    }

    public void writeSave(String libraryInfo) throws IOException
    {
        FileOutputStream save = new FileOutputStream("files/"+parse(fileName));

        writer.writeSave(libraryInfo, save);
        new File("files/temp_"+fileName).delete();
        file.close();
    }
}
