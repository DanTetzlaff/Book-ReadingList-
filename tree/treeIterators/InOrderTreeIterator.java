package tree.treeIterators;

/**
 * Used as a wrapper around a binary node object for easy moving through a binary tree.
 * 
 * This iterator follows an InOrder traversal of the Binary Tree
 * 
 *  Recursive algorithm:
 *      inorder(node)
 *           if node == null then return
 *           inorder(node.left)
 *           visit(node)
 *           inorder(node.right)
 * 
 *Iterator algorithm:
 *   iterativeInorder(node)
 *        parentStack = empty stack
 *        while not parentStack.isEmpty() or node != null
 *          if node != null then
 *             parentStack.push(node)
 *             node = node.left
 *          else
 *            node = parentStack.pop()
 *            visit(node)
 *            node = node.right
 * 
 * @see http://en.wikipedia.org/wiki/Tree_traversal#Depth-first_2
 * @author jkidney
 * @version 1 
 * 
 *      Created: Oct 17, 2013
 * Last Updated: Oct 17, 2013 - creation (jkidney)
 *               Oct 23, 2014 - Updated to to use java built in iterator class
 */
import tree.BinaryNode;

public class InOrderTreeIterator<keytype,datatype> extends RecursiveTreeIterator<keytype, datatype>
{
    /**
     * Constructor 
     * @param current the node to start the traversal from
     */
    public InOrderTreeIterator(BinaryNode<keytype, datatype> current) 
    {
        super(current);
    }

    /*
     * Must run a full in order recursive traversal. The visit action should call
     * the addNode method from the parent class
     * @see tree.treeIterators.RecursiveTreeIterator#runFullTraversal(tree.BinaryNode)
     */
    protected void runFullTraversal(BinaryNode<keytype, datatype> node) 
    {
        inOrder(node);
    }

    private void inOrder(BinaryNode<keytype, datatype> node)
    {
        if (node != null)
        {
            inOrder( node.getLeftChild() );
            
            addNode(node);
            
            inOrder( node.getRightChild() );
        }
    }

}