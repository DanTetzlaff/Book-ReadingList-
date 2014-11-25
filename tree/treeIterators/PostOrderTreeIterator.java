package tree.treeIterators;

/**
 * Used as a wrapper around a binary node object for easy moving through a binary tree.
 * 
 * This iterator follows an PostOrder traversal of the Binary Tree
 * 
 *  Recursive algorithm:
 *      inorder(node)
 *           if node == null then return
 *           inorder(node.left)
 *           inorder(node.right)
 *           visit(node)
 * 
 *Iterator algorithm:
 *         iterativePostorder(node)
 *               if node == null then return
 *               nodeStack = empty stack
 *               nodeStack.push(node)
 *               prevNode = null
 *               while not nodeStack.isEmpty()
 *                      currNode = nodeStack.peek()
 *                      if prevNode == null or prevNode.left == currNode or prevNode.right == currNode then
 *                               if currNode.left != null then
 *                                    nodeStack.push(currNode.left)
 *                               else if currNode.right != null then
 *                                    nodeStack.push(currNode.right)
 *                      else if currNode.left == prevNode then
 *                              if currNode.right != null then
 *                                  nodeStack.push(currNode.right)
 *                      else
 *                        visit(currNode)
 *                        nodeStack.pop()
 *                      prevNode = currNode
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

public class PostOrderTreeIterator<keytype,datatype> extends RecursiveTreeIterator<keytype, datatype>
{
    /**
     * Constructor 
     * @param current the node to start the traversal from
     */
    public PostOrderTreeIterator(BinaryNode<keytype, datatype> current) 
    {
        super(current);
    }

    /*
     * Must run a full Post order recursive traversal. The visit action should call
     * the addNode method from the parent class
     * @see tree.treeIterators.RecursiveTreeIterator#runFullTraversal(tree.BinaryNode)
     */
    protected void runFullTraversal(BinaryNode<keytype, datatype> node) 
    {
        postOrder(node);
    }

    private void postOrder(BinaryNode<keytype, datatype> node)
    {
        if (node != null)
        {
            postOrder( node.getLeftChild() );
            postOrder( node.getRightChild() );
            
            addNode(node);
        }
    }

}
