package tree.treeIterators;
/**
 * Used as a wrapper around a binary node object for easy moving through a binary tree.
 * This is the parent class for all iterators. Each iterator should follow a tree
 * traversal algorithm to move through the tree. The iterator will go through a full 
 * one way tree traversal until the last node has been visited
 * 
 * @author jkidney
 * @version 1 
 * 
 *      Created: Oct 17, 2013
 * Last Updated: Oct 17, 2013 - creation (jkidney)
 *               Oct 23, 2014 - Updated to to use java built in iterator class
 */

import java.util.*;

import tree.BinaryNode;

public abstract class BinaryTreeIterator<keytype,datatype> implements Iterator<datatype>
{
	protected BinaryNode<keytype,datatype> current;
	
	/**
	 * Constructor
	 * @param current the starting node for the iterator to work with
	 */
	public BinaryTreeIterator(BinaryNode<keytype,datatype> current)
	{
		this.current = current;
	}

	/**
	 * returns the data at the current node
	 * @return¨the data
	 */
	public datatype getCurrentData()
	{
		datatype data = null;
		if(current != null) data = current.getData();
		return data;
	}
	
	/**
	 * returns the key at the current node
	 * @return¨the key
	 */
	public keytype getCurrentKey()
	{
		keytype key = null;
		if(current != null) key = current.getKey();
		return key;
	}
	
	/**
	 *  Returns the reference in the current node in the traversal
	 *  @return the current node
	 */
	public BinaryNode<keytype,datatype> getCurrentNode() 
	{
	  return current;
	}
	
}
