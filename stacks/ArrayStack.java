package stacks;

import java.util.Arrays;

/**
 * Array implementation of a Stack
 * @author Daniel Tetzlaff
 * @version 1.0
 * 
 * Last Modified: Oct 20, 2014 - Created (Daniel Tetzlaff)
 */
public class ArrayStack<type> implements Stack<type> 
{
    private Object[] storage;
    private int size = 0;

    public ArrayStack() { storage = new Object[100]; }

    /**
     * added an element to stack, places it in the first unfilled position n the array
     * @param element is the object to be added to the stack
     */
    public void push(type element) 
    {
        if (size == storage.length-1)
            resize();        
          
        size++; 
        storage[size] = element;
    }

    /**
     * resize method double the size of the array once it is filled fully with data
     */
    private void resize()
    {
        Object[] temp = new Object[size*2];

        for (int c = 0; c < size; c++)
            temp[c] = storage[c];

        storage = temp;
    }

    public type pop() throws EmptyStackException
    {
        if (isEmpty())
            throw new EmptyStackException();

        Object tmp = storage[size];
        storage[size] = null;
        size--;

        return (type)tmp;
    }

    public type top() throws EmptyStackException
    {
        if (isEmpty())
            throw new EmptyStackException();

        Object tmp = storage[size];

        return (type)tmp;
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
