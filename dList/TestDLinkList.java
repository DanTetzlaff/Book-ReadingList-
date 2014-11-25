package dList;
/**
 * JUnit test class for the doubly linked list
 * @author Jordan Kidney
 * @version 1.0
 * 
 * Last Modified: Sept 18, 2014 - Created (Jordan Kidney)
 */
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import org.junit.Before;
import comparisonObjects.IntegerCompare;

public class TestDLinkList 
{
    private DLinkList<Integer> list;

    /**
     * Helper method used to populate the link list with data 
     * adding to the start of the list
     */
    private void addToFront(int[] thingsToAdd) 
    {
        for (int i : thingsToAdd) {
            list.addToFront(new Integer(i));
        }
    }

    /**
     * Helper method used to populate the link list with data 
     * adding to the end of the list
     */
    private void addToEnd(int[] thingsToAdd) 
    {
        for (int i : thingsToAdd) {
            list.addToEnd(new Integer(i));
        }
    }

    /**
     * Helper method used to populate the link list with data 
     * adding to the list using the sorted method
     */
    private void addSorted(int[] thingsToAdd) 
    {
        IntegerCompare cmp = new IntegerCompare();
        for (int i : thingsToAdd) {
            list.addSorted(new Integer(i), cmp);
        }
    }

    /**
     * Helper method used to confirm that all links traversing through the next links
     * is correct with the given data
     */
    private void confirmElementOrderForwards(int[] expectedElements)
    {
        Hashtable<DNode<Integer> , DNode<Integer> > seenNodes = new Hashtable<DNode<Integer>, DNode<Integer>>();
        DLinkListIterator<Integer> iter = list.getStartIterator();

        int countMatch = 0;
        int count = 0;
        for (int e : expectedElements) 
        {
            //if at the end of the expected number of elements
            //make sure iterator is at the end of the list
            if(count == expectedElements.length)
            {
                assertTrue("Reached end of expected values but the list contains more nodes", iter.atEndofList());	
            }
            else if(count != expectedElements.length-1)
            {
                //make sure we have not hit the end of the list before the expected number of elements
                assertFalse("Reached end of list before expected number of elements",iter.atEndofList());
            }

            //make sure there is data in the node
            assertNotNull("A node in the list contains no data", iter.getdata());	

            //check for a cycle in seen before nodes
            DNode<Integer> curr = iter.getNode();
            if(seenNodes.containsKey(curr))
            {
                fail("A cycle has been detected in your list");
            }
            else
                seenNodes.put(curr,curr);

            //check that data is correct
            assertEquals("Data elements in the list do not match expected order",
                new Integer(e), iter.getdata());
            countMatch++;

            iter.moveToNext();
            count++;


        }

        assertEquals("Expected number of data elements does not match how many are in list",
            expectedElements.length, countMatch);
    }

    /**
     * Helper method used to confirm that all links traversing through the prev links
     * is correct with the given data
     */
    private void confirmElementOrderBackwards(int[] expectedElements) 
    {
        Hashtable<DNode<Integer> , DNode<Integer> > seenNodes = new Hashtable<DNode<Integer>, DNode<Integer>>();
        DLinkListIterator<Integer> iter = list.getEndIterator();
        int countMatch = 0;
        int count=0;
        for (int e : expectedElements) 
        {
            //if at the end of the expected number of elements
            //make sure iterator is at the end of the list
            if(count == expectedElements.length)
            {
                assertTrue("Reached end of expected values but the list contains more nodes", iter.atEndofList());	
            }
            else if(count != expectedElements.length-1)
            {
                //make sure we have not hit the end of the list before the expected number of elements
                assertFalse("Reached start of list before expected number of elements",iter.atStartofList());
            }

            //make sure there is data in the node
            assertNotNull("A node in the list contains no data", iter.getdata());	

            //check for a cycle in seen before nodes
            DNode<Integer> curr = iter.getNode();
            if(seenNodes.containsKey(curr))
            {
                fail("A cycle has been detected in your list");
            }
            else
                seenNodes.put(curr,curr);

            assertEquals("Data elements in the list do not match expected order",
                new Integer(e), iter.getdata());
            countMatch++;

            iter.moveToPrev();
            count++;
        }

        assertEquals("Expected number of data elements does not match how many are in list",
            expectedElements.length, countMatch);
    }

    /**
     * Called before the start of each test case below is run ( this is done by JUnit)
     */
    @Before
    public void setUp()
    {
        list = new DLinkList<Integer>();
    }

    /**
     * Checks to make sure the list is empty when it is first created
     */
    @Test
    public void list_Empty_at_start() 
    {
        try
        {
            DLinkListIterator<Integer> iter = list.getStartIterator();
            DLinkListIterator<Integer> iter2 = list.getEndIterator();

            assertEquals(null, iter.getdata() );
            assertEquals(null, iter2.getdata() );
            assertEquals(0, list.getSize());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught: " + ex.getMessage());
        }
    }

    /*====================================================================
    ADD TO FRONT TESTS
    ==================================================================*/
    /**
     * Tests adding a single element to the front of an empty list
     */
    @Test
    public void add_To_Front_Single_Element() 
    {
        try
        {
            int[] elements = { 5 };
            addToFront(elements);

            confirmElementOrderForwards(elements);
            confirmElementOrderBackwards(elements);

            assertEquals(1, list.getSize() );  //confirm size of list is one
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /**
     * Tests adding a multiple elements to the front of an empty list
     * and that the next/prev links are all correct
     * also verifies that the size is correct
     */
    @Test
    public void add_To_Front_Multiple_Element_check() 
    {
        try
        {
            int[] elements = { 5, 4, 7 };
            addToFront(elements);

            int[] expectedElementsInOrder = { 7, 4, 5 };
            confirmElementOrderForwards(expectedElementsInOrder);

            int[] expectedElementsInOrder2 = { 5, 4, 7 };
            confirmElementOrderBackwards(expectedElementsInOrder2);

            assertEquals(3, list.getSize() ); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }
    /*====================================================================
    ADD TO END TESTS
    ==================================================================*/
    /**
     * Tests adding a single element to the end of an empty list
     */
    @Test
    public void add_To_End_Single_Element() 
    {
        try
        {
            int[] elements = { 5 };
            addToEnd(elements);

            confirmElementOrderForwards(elements);
            confirmElementOrderBackwards(elements);

            assertEquals(1, list.getSize() );  
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /**
     * Tests adding a multiple elements to the end of an empty list
     *and that the next/prev links are all correct
     *also verifies that the start reference is correct
     */
    @Test
    public void add_To_End_Multiple_Element_check() 
    {
        try
        {
            int[] elements = { 5, 4, 7 };
            addToEnd(elements);

            int[] expectedElementsInOrder = { 5, 4, 7 };
            confirmElementOrderForwards(expectedElementsInOrder);

            int[] expectedElementsInOrder2 = { 7, 4, 5 };
            confirmElementOrderBackwards(expectedElementsInOrder2);

            assertEquals(3, list.getSize() );
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /*====================================================================
    ADD Sorted TESTS
    ==================================================================*/
    /**
     * Tests adding a single element to the list calling add sorted
     */
    @Test
    public void add_Sorted_Single_Element() 
    {
        try
        {

            int[] elements = { 5 };
            addSorted(elements);
            confirmElementOrderForwards(elements);
            confirmElementOrderBackwards(elements);
            assertEquals(1, list.getSize() );
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /**
     * Tests adding multiple elements using addSorted on an empty list
     * and that the next/prev links are all correct
     * also verifies that the start reference is correct
     */ 
    @Test
    public void add_Sorted_Multiple_Element() 
    {
        try
        {
            int[] elements = { 5, 4, 7, -1, 6, 7, 4, -1 };
            addSorted(elements);

            int[] expectedElementsInOrder = { -1, -1, 4, 4, 5, 6, 7, 7 };
            confirmElementOrderForwards(expectedElementsInOrder);

            int[] expectedElementsInOrder2 = { 7, 7, 6, 5, 4, 4, -1, -1 };
            confirmElementOrderBackwards(expectedElementsInOrder2);

            assertEquals(8, list.getSize() );
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /**
     * Tests adding elements sorted after having normal addToStart
     * called ( sorted into a non sorted list )
     */
    @Test
    public void add_Sorted_After_Noraml_Adds_check() 
    {
        try
        {
            int[] elements  = { 5, 10, 20, 1 };
            int[] elements2 = { 1, 20, 10, 5 };
            addToFront(elements2);
            confirmElementOrderForwards(elements);
            confirmElementOrderBackwards(elements2);
            assertEquals(4, list.getSize() );  //confirm size of list 

            int[] elements3 = { 1, 19, 22, 7, 3 };

            addSorted(elements3);
            assertEquals(9, list.getSize() );  //confirm size of list 

            int[] expectedElementsInOrder = { 1,3,5,7,10,19,20,1,22};
            confirmElementOrderForwards(expectedElementsInOrder);

            int[] expectedElementsInOrder2 = { 22,1,20,19,10,7,5,3,1 };
            confirmElementOrderBackwards(expectedElementsInOrder2);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /*====================================================================
    get TESTS
    ==================================================================*/
    /**
     * Tests trying to do a get(index) on an empty list. Should throw an exception 
     */
    @Test
    public void get_On_An_Empty_List() 
    {
        try
        {
            list.get(1);
            fail("Exception not thrown");
        }
        catch(Exception ex)
        {
            assertTrue(true);
        }
    }
    //--------------------------------------------------------------------
    //Tests trying to do a get(index) that is a fail on a lower and uper bound
    @Test
    public void get_On_Out_Of_Bound_Indexs() 
    {
        try
        {
            int[] elements  = { 5, 10, 20, 1 };
            addToFront(elements);

            try
            {
                list.get(-1); // lower bound
                fail("Exception not thrown for lower bound");
            }
            catch(Exception er1){ }

            try
            {
                list.get(100); // upper bound
                fail("Exception not thrown for upper bound");
            }
            catch(Exception er2){ }

            try
            {
                list.get(4); // right on the edge of the upper bound
                fail("Exception not thrown for hight bound on edge");
            }
            catch(Exception er3){ }

            assertTrue(true); // if it gets here it means that the past 
            // two tests for bounds past
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Unknown Exception caught");
        }
    }

    /**
     * Tests trying to do a get(index) on all elements within bounds
     * in the list
     */
    @Test
    public void get_On_Start_Middle_End_Of_the_List() 
    {
        try
        {
            int[] elements = { 5, 4, 7, 11 , 20 };
            addToEnd(elements);

            int[] indexToCheck = { 0, 2, 1, 4, 3, 2, 4  };

            for(int index : indexToCheck)
            {
                assertEquals( new Integer(elements[index]) , list.get(index) );  
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /*====================================================================
    remove TESTS
    ==================================================================*/
    /**
     *  Tests trying to do a removeAt(index) on an empty list. 
     *  Should throw an exception 
     */
    @Test
    public void removeAt_On_An_Empty_List() 
    {
        try
        {

            list.removeAt(1);
            fail("Exception not thrown");
        }
        catch(Exception ex)
        {
            assertTrue(true);
        }
    }

    /**
     * Tests trying to do a removeAt(index) that is a fail on a lower/upper bound
     */
    @Test
    public void removeAt_On_Out_Of_Bounds() 
    {
        try
        {
            int[] elements  = { 5, 10, 20, 1 };
            addToFront(elements);

            try
            {
                list.removeAt(-1); // lower bound
                fail("Exception not thrown for lower bound");
            }
            catch(Exception er1){ }

            try
            {
                list.removeAt(100); // upper bound
                fail("Exception not thrown for upper bound");
            }
            catch(Exception er2){ }

            try
            {
                list.removeAt(4); // right on the edge of the upper bound
                fail("Exception not thrown for hight bound on edge");
            }
            catch(Exception er3){ }

            assertTrue(true); // if it gets here it means that the past 
            //two tests for bounds past
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Unknown Exception caught");
        }

    }

    /**
     * Helper methods for testing the remove function
     */
    private int[] toArray(ArrayList<Integer> numbers)
    {
        int[] list = new int[numbers.size()];
        for(int index=0; index < list.length; index++)
            list[index] = numbers.get(index);
        return list;
    }

    private int[] toRevArray(ArrayList<Integer> numbers)
    {
        int[] list = new int[numbers.size()];
        int count = 0;
        for(int index=list.length-1; index >= 0; index--)
        {
            list[count] = numbers.get(index);
            count++;
        }

        return list;
    }

    /**
     * Tests RemoveAt on an non empty list 
     * First, middle and end points
     */
    @Test
    public void removeAT_NotEmpty_check() 
    {
        try
        {
            int[] elements = { 5, 4, 7, 11 , 20 };
            int[] indexToRem = {1,2,2,0,0};
            ArrayList<Integer> numbers = new ArrayList<Integer>();

            for(int value : elements)
                numbers.add(value);

            addToEnd(elements);

            for(int index : indexToRem)
            {
                int remValue = numbers.remove(index);
                assertEquals( new Integer(remValue), list.removeAt(index));
                confirmElementOrderForwards( toArray(numbers) );
                confirmElementOrderBackwards( toRevArray(numbers) );
                assertEquals( numbers.size() , list.getSize() );
            }

            // list should be empty so confirm that all refs are set properly
            DLinkListIterator<Integer> iter = list.getStartIterator();
            DLinkListIterator<Integer> iter2 = list.getEndIterator();

            assertEquals(null, iter.getdata() );
            assertEquals(null, iter2.getdata() );
            assertEquals(0, list.getSize());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /**
     * Tests RemoveAt on an non empty list , and checks that elements can still be added 
     * properly after the remove
     * First, middle and end points
     */
    @Test
    public void removeAT_NotEmpty_check_Add_After() 
    {
        try
        {
            //Do all the removes
            int[] elements = { 5, 4, 7, 11 , 20 };
            int[] indexToRem = {1,2,2,0,0};
            ArrayList<Integer> numbers = new ArrayList<Integer>();

            for(int value : elements)
                numbers.add(value);

            addToEnd(elements);

            for(int index : indexToRem)
            {
                int remValue = numbers.remove(index);
                assertEquals( new Integer(remValue), list.removeAt(index));
                confirmElementOrderForwards( toArray(numbers) );
                confirmElementOrderBackwards( toRevArray(numbers) );
                assertEquals( numbers.size() , list.getSize() );
            }

            // list should be empty so confirm that all refs are set properly
            DLinkListIterator<Integer> iter = list.getStartIterator();
            DLinkListIterator<Integer> iter2 = list.getEndIterator();

            assertEquals(null, iter.getdata() );
            assertEquals(null, iter2.getdata() );
            assertEquals(0, list.getSize());

            //Do the adds and verify that everything is correct

            list.addToFront(5);
            list.addToFront(7);
            list.addToEnd(99);
            list.addToEnd(78);

            int[] order1 = { 7,5,99,78 }; 
            confirmElementOrderForwards(order1);

            int[] order2 = { 78,99,5,7 };
            confirmElementOrderBackwards(order2);

            assertEquals(4, list.getSize() );  //confirm size of list is one

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /*====================================================================
    contains TESTS
    ==================================================================*/
    /**
     * Tests trying to do a contains on an empty list. Should return false 
     */
    @Test
    public void contains_On_An_Empty_List() 
    {
        try
        {
            IntegerCompare cmp = new IntegerCompare();
            assertFalse("Found an element where it should not have", list.contains(2, cmp));	
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /**
     * Tests trying to do a contains where it should not be in the list. Should return false 
     */
    @Test
    public void contains_Not_In_List() 
    {
        try
        {
            IntegerCompare cmp = new IntegerCompare();

            int[] elements = { 5,6,8,12,89 };
            addToFront(elements);
            assertFalse("Found an element where it should not have", list.contains(2, cmp));	
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /**
     * Tests trying to do a contains where it should be in the list. Should return false 
     */
    @Test
    public void contains_In_List() 
    {
        try
        {
            IntegerCompare cmp = new IntegerCompare();

            int[] elements = { 5,6,8,2,12,89 };
            addToFront(elements);
            assertTrue("Did not find an element when it should have been.", list.contains(2, cmp));	
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /*====================================================================
    contains Find and remove test
    ==================================================================*/
    /**
     *  Tests trying to do a findAndRemove where the element is not in the list 
     *  
     */
    @Test
    public void findAndRemove_NotInList() 
    {
        try
        {
            IntegerCompare cmp = new IntegerCompare();
            int[] elements = { 5, 4, 7 };
            addToFront(elements);

            list.findAndRemove(2, cmp);

            int[] expectedElementsInOrder = { 7, 4, 5 };
            confirmElementOrderForwards(expectedElementsInOrder);

            int[] expectedElementsInOrder2 = { 5, 4, 7 };
            confirmElementOrderBackwards(expectedElementsInOrder2);

            assertEquals(3, list.getSize() ); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /**
     *  Tests trying to do a findAndRemove where the element is at the
     *  start of the list
     */
    @Test
    public void findAndRemove_InList_Start() 
    {
        try
        {
            IntegerCompare cmp = new IntegerCompare();
            int[] elements = { 2, 5, 4, 7, 2 };
            addToFront(elements);

            list.findAndRemove(2, cmp);

            int[] expectedElementsInOrder = { 7, 4, 5, 2 };
            confirmElementOrderForwards(expectedElementsInOrder);

            int[] expectedElementsInOrder2 = { 2, 5, 4, 7 };
            confirmElementOrderBackwards(expectedElementsInOrder2);

            assertEquals(4, list.getSize() ); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /**
     *  Tests trying to do a findAndRemove where the element in the middle of the list
     */
    @Test
    public void findAndRemove_InList_Middle() 
    {
        try
        {
            IntegerCompare cmp = new IntegerCompare();
            int[] elements = { 5, 2, 4, 2, 7, 3 };
            addToFront(elements);

            list.findAndRemove(2, cmp);

            int[] expectedElementsInOrder = { 3, 7, 4, 2, 5 };
            confirmElementOrderForwards(expectedElementsInOrder);

            int[] expectedElementsInOrder2 = { 5, 2, 4, 7, 3 };
            confirmElementOrderBackwards(expectedElementsInOrder2);

            assertEquals(5, list.getSize() ); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /**
     *  Tests trying to do a findAndRemove where the element is at the end of the list
     */
    @Test
    public void findAndRemove_InList_End() 
    {
        try
        {
            IntegerCompare cmp = new IntegerCompare();
            int[] elements = { 2, 5, 4, 7, 3 };
            addToFront(elements);

            list.findAndRemove(2, cmp);

            int[] expectedElementsInOrder = { 3, 7, 4, 5 };
            confirmElementOrderForwards(expectedElementsInOrder);

            int[] expectedElementsInOrder2 = { 5, 4, 7, 3 };
            confirmElementOrderBackwards(expectedElementsInOrder2);

            assertEquals(4, list.getSize() ); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /**
     *  Tests trying to do a findAndRemoveAll 
     */
    @Test
    public void findAndRemoveALL_InList() 
    {
        try
        {
            IntegerCompare cmp = new IntegerCompare();
            int[] elements = { 2, 5, 2, 7, 2 };
            addToFront(elements);

            list.findAndRemoveAll(2, cmp);

            int[] expectedElementsInOrder = { 7, 5 };
            confirmElementOrderForwards(expectedElementsInOrder);

            int[] expectedElementsInOrder2 = { 5, 7  };
            confirmElementOrderBackwards(expectedElementsInOrder2);

            assertEquals(2, list.getSize() ); 

            //Do the adds and verify that everything is correct
            list.addToFront(3);
            list.addToFront(6);
            list.addToEnd(99);
            list.addToEnd(78);

            int[] order1 = { 6,3,7,5,99,78 }; 
            confirmElementOrderForwards(order1);

            int[] order2 = { 78,99,5,7,3,6 };
            confirmElementOrderBackwards(order2);

            assertEquals(6, list.getSize() );  //confirm size of list is one
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /**
     *  Tests trying to do a findAndRemoveAll  where every element will be removed
     */
    @Test
    public void findAndRemoveALL_InList_removes_all() 
    {
        try
        {
            IntegerCompare cmp = new IntegerCompare();
            int[] elements = { 2,2,2,2,2,2 };
            addToFront(elements);

            list.findAndRemoveAll(2, cmp);

            DLinkListIterator<Integer> start = list.getStartIterator();
            DLinkListIterator<Integer> end = list.getEndIterator();

            assertNull("Start is not null", start.getNode());
            assertNull("End is not null", end.getNode());

            assertEquals(0, list.getSize() ); 

            //Do the adds and verify that everything is correct
            list.addToFront(3);
            list.addToFront(6);
            list.addToEnd(99);
            list.addToEnd(78);

            int[] order1 = { 6,3,99,78 }; 
            confirmElementOrderForwards(order1);

            int[] order2 = { 78,99,3,6 };
            confirmElementOrderBackwards(order2);

            assertEquals(4, list.getSize() );  //confirm size of list is one
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            fail("Exception caught");
        }
    }

    /**
     * Tests adding elements sorted after having normal addToStart
     * called ( sorted into a non sorted list )
     */
    @Test
    public void add_Sorted_one_node_list_new_node_comes_after() {
        IntegerCompare cmp = new IntegerCompare();
        int[] elements = { 2 };
        addToFront(elements);

        list.addSorted(new Integer(3), cmp);

        int[] order1 = { 2,3 };
        confirmElementOrderForwards(order1);
    }
}
