package stacks;

/**
 * LinkList implementation of a Stack
 * @author Daniel Tetzlaff
 * @version 2.0
 * 
 * Last Modified: Oct 21, 2014 - Created (Daniel Tetzlaff)
 *                Nov  4, 2014 - Use from scratch singly linked list (Daniel Tetzlaff)
 */
public class LinkListStack<type> implements Stack<type>
{
    private SNode<type> start;
    private int size = 0;

    public LinkListStack() { start = null; }

    public void push(type element)
    {
        SNode<type> nodeToAdd = new SNode<type>(element);

        if (isEmpty())
        {
            start = nodeToAdd;
        }
        else
        {
            nodeToAdd.setNext(start);
            start = nodeToAdd;
        }

        size++;
    }

    public type pop() throws EmptyStackException
    {
        if (isEmpty())
            throw new EmptyStackException();

        SNode<type> tmp = start;

        if (start.getNext() == null)
        {
            start = null;
        }
        else
        {
            start = start.getNext();
        }
        
        size--;
        return tmp.getData();
    }

    public type top() throws EmptyStackException
    {
        if (isEmpty())
            throw new EmptyStackException();

        try {
            return start.getData();
        }
        catch (Exception e) {
            throw new EmptyStackException();
        }
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
