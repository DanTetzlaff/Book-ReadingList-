package dList;
/**
 * Generic Doubly linked list
 * @author Jordan Kidney
 * @author Daniel Tetzlaff
 * @version 1.0
 * 
 * Last Modified: Sept 18, 2014 - Created (Jordan Kidney)
 *                Oct   6, 2014 - Completed Functionality (Daniel Tetzlaff)
 */
import java.util.Comparator;

import javax.management.RuntimeErrorException;

public class DLinkList<type> 
{
    private DNode<type> start; // will always keep a reference to the first node in the list
    private DNode<type> end;   // will always keep a reference to the last node in the list
    private int size = 0;      // keep variable of total number of nodes in list

    /**
     * default constructor
     */
    public DLinkList()
    {
        start = end = null;
    }

    public boolean isEmpty() {return (start == null);}

    /**
     * gives back the size of the list
     * @return the number of nodes in the list
     */
    public int getSize() 
    { 
        /*int size = 0;

        DNode<type> m = start;
        while ( m != null )
        {
        m = m.getNext();
        size++;
        }

        this.size = size;*/ //Debugging help code
        return size;
    }

    /**
     * creates an Iterator object that begins at the start of the list
     * @return the iterator object
     */
    public DLinkListIterator<type> getStartIterator()
    {
        //DO NOT CHANGE THIS METHOD
        return new DLinkListIterator<type>(start);
    }

    /**
     * creates an Iterator object that begins at the end of the list
     * @return the iterator object
     */
    public DLinkListIterator<type> getEndIterator()
    {
        //DO NOT CHANGE THIS METHOD
        return new DLinkListIterator<type>(end);
    }

    /**
     * This method will create a new node to contain the data and add it to the
     * front of the list
     * @param data the data element to add to the start of the list
     */
    public void addToFront(type data)
    {
        DNode<type> nodeToAdd = new DNode<type>(data);

        if (isEmpty())
        {
            start = nodeToAdd;
            end = nodeToAdd;
        }
        else
        {
            nodeToAdd.setNext(start);
            start.setPrev(nodeToAdd);
            start = nodeToAdd;
        }

        size++;
    }

    /**
     * This method will create a new node to contain the data and add it to the end of the list
     * You should try to do this add in O(1)
     * @param data the data element to add to the end of the list
     */
    public void addToEnd(type data)
    {
        DNode<type> nodeToAdd = new DNode<type>(data);

        if (isEmpty())
        {
            start = nodeToAdd;
            end = nodeToAdd;
        }
        else
        {
            nodeToAdd.setPrev(end);
            end.setNext(nodeToAdd);
            end = nodeToAdd;
        }

        size++;
    }

    /**
     * This method will create a new node to contain the data and add it sorted into the list.
     * It should apply an insertion sort step to place the node properly in the list. The first node
     * should be the smallest and the end node should be the largest. Note: This method only
     * works if all insertions to the list are done using this method rather then the other
     * add methods. 
     * @param data the data element to add to the list
     * @param comparator the compare object used to determine the proper insertion point
     * @see Comparator
     */
    public void addSorted(type data, Comparator<type> comparator)
    {
        DNode<type> nodeToAdd = new DNode<type>(data);

        if (isEmpty())
        {
            start = nodeToAdd;
            end = nodeToAdd;
            size++;
        }
        else
        {
            DNode<type> m = start;

            int result = comparator.compare(nodeToAdd.getData(), m.getData());
            if (result < 0) {
                if (start == m) {
                    addToFront(nodeToAdd.getData());
                }
            }
            else if (result == 0) {
                if (start == m) {
                    addToFront(nodeToAdd.getData());
                }
                else
                {
                    DNode<type> next = m.getNext();

                    nodeToAdd.setNext(next);
                    nodeToAdd.setPrev(m);
                    m.setNext(nodeToAdd);
                    next.setPrev(nodeToAdd);
                    size++;
                }
            }
            else
            {
                if (end == m) {
                    addToEnd(nodeToAdd.getData());
                }
                else
                {
                    boolean added = false;

                    while (!added)
                    {
                        DNode<type> tmp = m.getNext();

                        int nextResult = comparator.compare(nodeToAdd.getData(), tmp.getData());
                        if (nextResult < 0)
                        {
                            DNode<type> next = m.getNext();

                            nodeToAdd.setNext(next);
                            nodeToAdd.setPrev(m);
                            m.setNext(nodeToAdd);
                            next.setPrev(nodeToAdd);
                            size++;
                            added = true;
                        }
                        else
                        {
                            if (tmp == end)
                            {
                                addToEnd(nodeToAdd.getData());
                                added = true;
                            }
                            else
                            {
                                m = m.getNext();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Retrieves the data stored at the given index/node in the list. The first
     * node is considered to be stored at index 0
     * @param index the index to get data from
     * @return the data element stored at the given location if the index is within the bounds
     * @throws Exception if the index is out of bounds or the list is empty
     */
    public type get(int index) throws Exception
    {
        type data;

        if (isEmpty())
        {
            throw new Exception("List Empty");
        }
        else
        {
            if (index >= 0 && index < size)
            {
                DNode<type> m = start;

                for (int c = 0; c < index; c++)
                {
                    m = m.getNext();
                }  

                data = m.getData();
            }
            else
            {
                throw new Exception("index out of bounds");
            }
        }

        return data;
    }

    /**
     * Removes the data stored at the given index/node in the list. The first
     * node is considered to be stored at index 0. The data and node from this index will be removed.
     * @param index the index to get data/node to remove
     * @return the data element stored at the given location if the index is within the bounds
     * @throws Exception if the index is out of bounds or the list is empty
     */
    public type removeAt(int index) throws Exception
    {
        type data;

        if (isEmpty())
        {
            throw new Exception("List Empty");
        }
        else
        {
            if (index >= 0 && index < size)
            {
                DNode<type> m = start;

                for (int c = 0; c < index; c++)
                {
                    m = m.getNext();
                }

                data = m.getData();
                remove(m);
            }
            else
            {
                throw new Exception("index out of bounds");
            }
        }     

        return data;
    }

    /**
     * removes a specific node from the list
     * @param m is the node to be roemoved
     */
    private void remove(DNode<type> m)
    {
        DNode<type> prev = m.getPrev();
        DNode<type> next = m.getNext();

        if (prev == null && next == null)
        {
            start = end = null;
        }
        else if (prev == null)
        {
            start = next;
            next.setPrev(null);
        }
        else if (next == null)
        {
            end = prev;
            prev.setNext(null);
        }
        else
        {
            prev.setNext(next);
            next.setPrev(prev);

            prev = next = m = null;
        }

        size--;
    }

    /**
     * Determines if the provided data element is in the list or not
     * @param other the data element to search for
     * @param comparator used to do the comparisons 
     * @return true if at least one match found, false otherwise
     */
    public boolean contains(type other, Comparator<type>  comparator)
    {
        DNode<type> nodeToFind = new DNode<type>(other);
        boolean result = false;

        if (!isEmpty())
        {
            DNode<type> m = start;

            for (int c = 0; c < size && !result; c++)
            {

                int cmpr = comparator.compare(nodeToFind.getData(), m.getData());

                if (cmpr == 0)              
                    result = true;
                else               
                    m = m.getNext();
            }
        }

        return result;
    }

    /**
     * Searches for the given value in the list and removes the first instance it finds
     * @param other the data element to search for
     * @param comparator used to do the comparisons
     * @return null if nothing is found, otherwise the data element that was removed
     */
    public type findAndRemove(type other, Comparator<type>  comparator) throws Exception
    {
        type data = null;
        DNode<type> nodeToRemove = new DNode<type>(other);

        if (!isEmpty()) //check if list is empty
        {
            boolean removed = false;
            DNode<type> m = start;
            int index = 0;

            // while item is not removed move to next and compare
            while (!removed)
            {
                int result = comparator.compare(nodeToRemove.getData(), m.getData());

                if (result == 0) // if matches remove
                {
                    remove(m);
                    removed = true;
                }
                else // m != nodeToRemove, move to next node
                {
                    index++;
                    m = m.getNext();
                }

                if (index == size) // if index == size, searched all nodes therefore nothing is removed... break loop
                    removed = true;
            }
        }

        return data;
    }

    /**
     * Searches for the given value in the list and removes all of the instances it finds
     * @param other the data element to search for
     * @param comparator used to do the comparisons
     * @return null if nothing is found, otherwise the data element that was removed
     */
    public type findAndRemoveAll(type other, Comparator<type>  comparator) throws Exception
    {
        type data = null;
        DNode<type> toRemove = new DNode<type>(other);

        if (!isEmpty()) // check if list is empty
        {
            DNode<type> m = start;               
            int index = 0;

            while (index < size) // check all nodes 
            {
                int result = comparator.compare(toRemove.getData(), m.getData());

                if (result == 0) //if nodes match remove
                {
                    remove(m);
                    index = 0;
                    m = start;
                }
                else // nodes do not match, move to next node
                {
                    m = m.getNext();
                    index++;
                }                
            }
        }

        return data;
    }

    /**
     * prints the list to console/standard output from the first node to the last node
     */
    public void print()
    {
        DNode<type> curr = start;

        System.out.print("Start->");

        while(curr != null)
        {
            System.out.print("["+curr.getData()+"]->");
            curr = curr.getNext();
        }

        System.out.println("null");
    }

}
