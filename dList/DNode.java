package dList;
/**
* Basic Generic Node class used to hold data in a Link List
* @author Jordan Kidney
* @version 1.0
* 
* Last Modified: Sept 18, 2014 - Created (Jordan Kidney)
*/

public class DNode<type> 
{
	private type data;
	private DNode<type> next;
	private DNode<type> prev;
	
	public DNode() { next=prev=null; }

	/**
	 * Constructor used to insert data at the time of creation
	 * @param data
	 */
	public DNode(type data)
	{
		super();
		this.data = data;
	}

	public type getData() { return data; }
	public void setData(type data) { this.data = data; }

	public DNode<type> getNext() { return next; }
	public void setNext(DNode<type> next) { this.next = next; }

	public DNode<type> getPrev() { return prev; } //poop :D
	public void setPrev(DNode<type> prev) { this.prev = prev; }
}

