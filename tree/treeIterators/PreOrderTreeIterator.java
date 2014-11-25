package tree.treeIterators;

/**
 * Used as a wrapper around a binary node object for easy moving through a binary tree.
 * 
 * This iterator follows an PreOrder traversal of the Binary Tree
 * 
 *  Recursive algorithm:
 *      inorder(node)
 *           if node == null then return
 *           visit(node)
 *           inorder(node.left)
 *           inorder(node.right)
 * 
 *Iterator algorithm:
 *        iterativePreorder(node)
 *          parentStack = empty stack
 *          while not parentStack.isEmpty() or node != null
 *             if node != null then
 *                 visit(node)
 *                 if node.right != null then
 *                     parentStack.push(node.right)
 *                 node = node.left
 *             else
 *                 node = parentStack.pop()
 * 
 * @see http://en.wikipedia.org/wiki/Tree_traversal#Depth-first_2
 * @author jkidney
 * @author Daniel Tetzlaff
 * @version 1 
 * 
 *      Created: Oct 17, 2013
 * Last Updated: Oct 17, 2013 - creation (jkidney)
 *               Oct 23, 2014 - Updated to to use java built in iterator class
 *               Nov 17, 2014 - Functionality Completed (Daniel Tetzlaff)
 */
import tree.BinaryNode;

public class PreOrderTreeIterator<keytype,datatype> extends RecursiveTreeIterator<keytype, datatype>
{
    /**
     * Constructor 
     * @param current the node to start the traversal from
     */
    public PreOrderTreeIterator(BinaryNode<keytype, datatype> current) 
    {
        super(current);
    }

    /*
     * Must run a full Pre order recursive traversal. The visit action should call
     * the addNode method from the parent class
     * @see tree.treeIterators.RecursiveTreeIterator#runFullTraversal(tree.BinaryNode)
     */
    protected void runFullTraversal(BinaryNode<keytype, datatype> node) 
    {
        preOrder(node);
    }

    private void preOrder(BinaryNode<keytype, datatype> node)
    {
        if (node != null)
        {
            addNode(node);
            
            preOrder( node.getLeftChild() );
            preOrder( node.getRightChild() );
        }
    }

}
