package tree;
/**
 * Basic Generic Binary tree Node class used to hold data in any binary tree
 * @author JKidney
 * @version 2 
 * 
 *      Created: Oct 17, 2013
 * Last Updated: Oct 17, 2013 - creation (jkidney)
 *               Oct 22, 2013 - added childrenCount() and isLeaf() methods (jkidney)
 *               Oct 23, 2013 - added parent node ref and update methods (jkidney)
 */
public class BinaryNode<keytype, datatype> 
{
	private datatype data;
	private keytype key;
	private BinaryNode<keytype, datatype> leftChild;
	private BinaryNode<keytype, datatype> rightChild;
	
	private BinaryNode<keytype, datatype> parent;
	
	public BinaryNode() { leftChild=rightChild=null; }

	/**
	 * Constructor used to insert data at the time of creation
	 * @param key the key that was used to store the data in the tree
	 * @param data the data to be stored in the node
	 */
	public BinaryNode(keytype key, datatype data)
	{
		super();
		this.key = key;
		this.data = data;
		leftChild = rightChild = parent = null;
	}

	public datatype getData() { return data; }
	public void setData(datatype data) { this.data = data; }

	public keytype getKey() { return key; }
	
	public void setKey(keytype key) { this.key = key; }

	public BinaryNode<keytype, datatype> getLeftChild() { return leftChild; }
	
	/**
	 * Sets the currents nodes left child. it will also update the parent relationship with the child node
	 * @param node the node to set as the left child
	 */
	public void setLeftChild(BinaryNode<keytype, datatype> node) 
	{ 
		this.leftChild = node; 
	
		if(leftChild != null) leftChild.setParent(this);
	}
	/**
	 * Determines if the current node has a right child
	 * @return true if it does, false otherwise
	 */
	public boolean hasRightChild() 
	{
		return ( rightChild != null );
	}
	
	public BinaryNode<keytype, datatype> getRightChild() { return rightChild; } //The revenge of poop :D
	
	/**
	 * Sets the currents nodes right child. it will also update the parent relationship with the child node
	 * @param node the node to set as the right child
	 */
	public void setRightChild(BinaryNode<keytype, datatype> node) 
	{ 
		this.rightChild = node; 
		
		if(rightChild != null) rightChild.setParent(this);
	}
	
	
	public BinaryNode<keytype, datatype> getParent() { return parent; } 
	public void setParent(BinaryNode<keytype, datatype> node) { this.parent = node; }
	
	/**
	 * Determines if the current node has a left child
	 * @return true if it does, false otherwise
	 */
	public boolean hasLeftChild() 
	{
		return ( leftChild != null );
	}
	
	/**
	 * Determines the number of children this node has
	 * @return the total number of children this node has
	 */
	public int childrenCount()
	{
		int count = 0;
		
		if(hasLeftChild()) count++;
		if(hasRightChild()) count++;
		
		return count;
	}
	/**
	 * Determines if the node is a leaf node or not
	 * @return true of it is a leaf false otherwise
	 */
	public boolean isLeaf()
	{
		return (childrenCount() == 0);
	}

	
	/**
	 * Replaces the current node with all the data from the target node
	 * this method is useful when doing removes and you need to move 
	 * a child up to replace another node in the tree
	 * this method will preserve the parent of the current node
	 * @param node the node to copy data from
	 */
	public void replaceWith( BinaryNode<keytype, datatype>  node)
	{
		key = node.key;
		data = node.data;
		setLeftChild(node.leftChild);
		setRightChild(node.rightChild);
	}
	
	/**
	 * unlinks the current node from its parent
	 * useful when a leaf node is being removed
	 */
	public void unLinkFromParent()
	{
	   if(parent != null)
	   {
		   parent.unlinkChild(this);
		   parent = null;
	   }
	   
	}
	
	/**
	 * Unlinks the specified child. This method only gets called
	 * by the unLinkFromParent() method when a child is being removed
	 */
	private void unlinkChild( BinaryNode<keytype, datatype> child )
	{
		if(child != null)
		{
		   if(child == leftChild)  leftChild = null;
		   else rightChild = null;
		}
	}
	
	/**
	 * String representation of the node object
	 */
	public String toString() {
		return "[key=" + key + ", data=" + data + "]";
	}
}

