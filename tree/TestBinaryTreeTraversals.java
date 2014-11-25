package tree;
/**
 * JUnit test Class for all Binary Tree Traversals
 *  
 * @author JKidney
 * @version 1 
 * 
 *      Created: Oct 24, 2013
 * Last Updated: Oct 24, 2013 - creation (jkidney)
 *               Oct 24, 2013 - tests created (jkidney)
 *               Nov  7, 2013 - fixed bug for tests where traversals did not return any data (jkidney)
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tree.treeIterators.BinaryTreeIterator;
import comparisonObjects.IntegerCompare;

public class TestBinaryTreeTraversals {

	private BinarySearchTree<Integer,Integer> tree;

	/**
	 * Helper method used to populate the tree with data 
	 */
	public void addAllData(int[] thingsToAdd) 
	{
		for (int i : thingsToAdd) {
			tree.add(i,i);
		}
	}

	/**
	 * Helper method used to confirm that all data in the tree
	 * is in the proper binary search tree format based upon a level-order
	 * traversal of the tree.
	 */
	public void confirmMatch(int[] expectedElements, int traversal_type)
	{
		BinaryTreeIterator<Integer,Integer> iter = tree.getTraversalIterator(traversal_type);
		int index=0;

		//confirm that we have matching tree size an expected number of elements
		String errMsg = "Tree size does not match expected number of nodes:";
		errMsg += " Expected " + expectedElements.length + " got " + tree.getSize(); 
		assertEquals(errMsg, expectedElements.length,tree.getSize());

		//confirm that each node in the traversal matches the expected node from a Level
		//order traversal at the given iteration. Checks to make sure that the expected key matches
		while(iter.hasNext())
		{
			int key = iter.next();
			int expectedKey = expectedElements[index];
			assertEquals("Node at wrong location in tree: ",expectedKey,key);

			//iter.next();
			index++;
		}
		
		
		assertEquals("# of nodes returned by traversal does not match expected",
				      expectedElements.length, index);

	}

	public void conFirmEmptyTraversal(BinaryTreeIterator<Integer,Integer> iter)
	{
		assertEquals("Iterator not blank for empty tree",null, iter.getCurrentNode() );
		assertFalse(iter.hasNext());
	}
	
	
	public void testTraversal(int traversal_type,int[] elements,int[] expectedTrav)
	{
		try
		{
			addAllData(elements);	
			confirmMatch(expectedTrav, traversal_type);
		}
		catch(Exception ex)
		{
			fail("Exception caught");
		}
		
	}
	
	/**
	 * Called before the start of each test case below is run ( this is done by JUnit)
	 */
	@Before
	public void setUp()
	{
		tree = new BinarySearchTree<Integer,Integer>(new IntegerCompare());
	}

	
	/**
	 * Test all iterators on an empty tree
	 */
	@Test
	public void tree_Empty_at_start() 
	{
		try
		{
			
			BinaryTreeIterator<Integer,Integer> LEVELiter = tree.getTraversalIterator(BinarySearchTree.LEVEL_TRAV);
			BinaryTreeIterator<Integer,Integer> PREiter = tree.getTraversalIterator(BinarySearchTree.PRE_TRAV);
			BinaryTreeIterator<Integer,Integer> POSTiter = tree.getTraversalIterator(BinarySearchTree.POST_TRAV);
			BinaryTreeIterator<Integer,Integer> INiter = tree.getTraversalIterator(BinarySearchTree.IN_TRAV);

			
			conFirmEmptyTraversal(LEVELiter);
			conFirmEmptyTraversal(PREiter);
			conFirmEmptyTraversal(POSTiter);
			conFirmEmptyTraversal(INiter);
		}
		catch(Exception ex)
		{
			fail("Exception caught: " + ex.getMessage());
		}
	}

	
	/**
	 * Test Level Order Traversal
	 */
	@Test
	public void test_level_order_traversal()
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102 };
			int[] expectedTrav = { 5, 2, 100, 1, 3, 7, 102 };
			testTraversal(BinarySearchTree.LEVEL_TRAV,elements,expectedTrav);
			
		}
		catch(Exception ex)
		{
			fail("Exception caught: " + ex.getMessage());
		}
	}
	
	/**
	 * Test Pre Order Traversal
	 */
	@Test
	public void test_pre_order_traversal()
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102 };
			int[] expectedTrav = { 5,2,1,3,100,7,102 };
			testTraversal(BinarySearchTree.PRE_TRAV,elements,expectedTrav);
			
		}
		catch(Exception ex)
		{
			fail("Exception caught: " + ex.getMessage());
		}
	}
	
	/**
	 * Test In Order Traversal
	 */
	@Test
	public void test_in_order_traversal()
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102 };
			int[] expectedTrav = { 1,2,3,5,7,100,102 };
			testTraversal(BinarySearchTree.IN_TRAV,elements,expectedTrav);
			
		}
		catch(Exception ex)
		{
			fail("Exception caught: " + ex.getMessage());
		}
	}

	/**
	 * Test Post Order Traversal
	 */
	@Test
	public void test_post_order_traversal()
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102 };
			int[] expectedTrav = { 1,3,2,7,102,100,5 };
			testTraversal(BinarySearchTree.POST_TRAV,elements,expectedTrav);
			
		}
		catch(Exception ex)
		{
			fail("Exception caught: " + ex.getMessage());
		}
	}
}
