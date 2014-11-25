package tree.treeIterators;

import java.util.*;

import tree.BinaryNode;
/**
 * Parent class for all recursive traversal solution iterators
 * 
 * @author jkidney
 * @version 1 
 * 
 *      Created: Oct 23, 2014
 * Last Updated: Oct 23, 2014 - creation (jkidney)
 */
public abstract class RecursiveTreeIterator<keytype, datatype> extends BinaryTreeIterator<keytype, datatype> 
{
	private ArrayList< BinaryNode<keytype,datatype> > traversalNodes;

	public RecursiveTreeIterator(BinaryNode<keytype, datatype> current) 
	{
		super(null);
		traversalNodes = new ArrayList< BinaryNode<keytype,datatype> >();
		runFullTraversal(current);
	}

	public boolean hasNext() { return !traversalNodes.isEmpty() || current != null; }

	public datatype next() throws NoSuchElementException
	{
		datatype data = null;
		if(!hasNext()) throw new NoSuchElementException();


		if(traversalNodes.isEmpty())
		{
			data = current.getData();
			current = null;
		}
		else
		{
			data = current.getData();
			current = traversalNodes.remove(0);
		}

		return data;
	}

	/**
	 * Used by the children classes to add a node during the visit action of each recursive traversal
	 * @param node the node to add
	 */
	protected void addNode(BinaryNode<keytype, datatype> node) 
	{   
		if(current == null) 
			current = node;
		else
			traversalNodes.add(node); 
	}

	/**
	 * Should be implemented by the children classes to run the full recursive traversals.
	 * @param startNode
	 */
	protected abstract void runFullTraversal(BinaryNode<keytype, datatype> node);

}
