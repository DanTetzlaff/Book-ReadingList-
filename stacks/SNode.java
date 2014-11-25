package stacks;

/**
* Basic Generic Node class used to hold data in a Link List
* @author Daniel Tetzlaff
* @version 1.0
* 
* Last Modified: November  4, 2014 - Created (Daniel Tetzlaff)
*/

public class SNode<type> 
{
	private type data;
	private SNode<type> next;
	
	public SNode() { next=null; }

	/**
	 * Constructor used to insert data at the time of creation
	 * @param data
	 */
	public SNode(type data)
	{
		super();
		this.data = data;
	}

	public type getData() { return data; }
	public void setData(type data) { this.data = data; }

	public SNode<type> getNext() { return next; }
	public void setNext(SNode<type> next) { this.next = next; }
}
