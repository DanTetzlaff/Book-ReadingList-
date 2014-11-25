package stacks;

import java.util.ArrayList;

/**
 * ArrayList implementation of a Stack
 * @author Daniel Tetzlaff
 * @version 1.0
 * 
 * Last Modified: Oct 21, 2014 - Created (Daniel Tetzlaff)
 */
public class ArrayListStack<type> implements Stack<type>
{
    private ArrayList<type> storage;
    private int size = 0;
    
    public ArrayListStack() { storage = new ArrayList<type>(); }
    
    public void push(type element)
    {
        storage.add(element);
        size++;
    }
    
    public type pop() throws EmptyStackException
    {
        if (isEmpty())
            throw new EmptyStackException();
        
        size--;
        return storage.remove(size);
    }
    
    public type top() throws EmptyStackException
    {
        if (isEmpty())
            throw new EmptyStackException();
        
        return storage.get(size-1);
    }
    
    public int size()
    {
        return size;
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
}
