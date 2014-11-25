package tree;
/**
 * Basic Generic Binary Search Tree class.
 * 
 *  A Binary Search Tree is a node-based binary tree data structure which has the following properties:
 *         (1) The left subtree of a node contains only nodes with data that is less than the current node.
 *         (2) The right subtree of a node contains only nodes with data that is greater than the current node's.
 *         (3) The left and right subtree each must also be a binary search trees.
 *         (4) There must be no duplicate nodes.
 *         
 * @see http://en.wikipedia.org/wiki/Binary_search_tree
 * 
 * @author JKidney
 * @author Daniel Tetzlaff
 * @version 1 
 * 
 *      Created: Oct 17, 2013
 * Last Updated: Oct 17, 2013 - creation (jkidney)
 *               Oct 23, 2014 - updated to new version
 *               Nov 14, 2014 - Completed implementation (Daniel Tetzlaff)
 */
import java.util.Comparator;

import tree.treeIterators.*;

/*
 *  keytype = the type of the key that is used to store and retrieve data in the binary search tree
 * datatype = the type of the data being stored in the binary search tree
 */
public class BinarySearchTree<keytype, datatype>  
{
    private BinaryNode<keytype, datatype> root; // will always keep a reference to the root node of the tree
    private Comparator<keytype> comparator; // used to compare keys when adding and searching
    private int size = 0;

    /**
     * Sets up the basics of the binary search tree
     * @param comparator used to compare keys when adding and search the tree
     */
    public BinarySearchTree(Comparator<keytype> comparator)
    {
        root = null;
        this.comparator = comparator;
    }

    /**
     * gives back the size of the list
     * @return the number of nodes in the tree
     */
    public int getSize() 
    { 
        return size; 
    }

    //CONSTANTS used when selecting a specific Traversal iterator 
    public static final int   PRE_TRAV  = 0; // PreOrder traversal
    public static final int    IN_TRAV  = 1; // InOrder traversal
    public static final int  POST_TRAV  = 2; // PostOrder traversal
    public static final int LEVEL_TRAV  = 3; // LevelOrder traversal

    //constants used for the print
    private static final int PRINT_NODE = 0; // print out the entire node
    private static final int PRINT_KEY = 1; // print out just the key
    private static final int PRINT_DATA = 2; // print out just the data

    /**
     * Creates an iterator that can be used to do a specific traversal of a tree
     * based upon the given type. Defaults to Level order if incorrect value is given
     * @param type must be one of the constants provided by the class (PRE_TRAV,IN_TRAV,POST_TRAV,LEVEL_TRAV)
     * @return the desired traversal iterator
     */
    public BinaryTreeIterator<keytype, datatype> getTraversalIterator(int type)
    {
        //DO NOT CHANGE THIS METHOD
        BinaryTreeIterator<keytype, datatype> iter = null;

        switch(type)
        {
            case  PRE_TRAV: iter = new PreOrderTreeIterator<keytype, datatype>(root); break;
            case   IN_TRAV: iter = new InOrderTreeIterator<keytype, datatype>(root); break;
            case POST_TRAV: iter = new PostOrderTreeIterator<keytype, datatype>(root); break;
            default:        iter = new LevelOrderTreeIterator<keytype, datatype>(root); break;  
        }

        return iter;
    }

    /**
     * Adds the new data element to the binary search tree
     *  
     * Insertion begins as a search would begin (see the find method); if the key is not 
     * equal to that of the root's key, we search the left or right subtrees as before. 
     * Eventually, we will reach an external node and add the data as its right or left child, 
     * depending on the node's key. In other words, we examine the root and recursively 
     * (or iteratively) insert the new node to the left subtree if its key is less than 
     * that of the root's key, or the right subtree if its key is greater than the root's key.Bianar
     * if the key matches any keys in the tree we do not add it.
     * 
     * @see http://en.wikipedia.org/wiki/Binary_search_tree#Insertion
     * @param key the key to use for comparisons on where to store the data in the tree
     * @param data the new data to try to store in the tree
     */
    public void add(keytype key, datatype data)
    {
        BinaryNode<keytype, datatype> nodeToAdd = new BinaryNode<keytype, datatype>(key, data);

        if(size == 0)
        {
            root = nodeToAdd;
            size++;
        }
        else
        {
            BinaryNode<keytype, datatype> m = root;
            int Osize = size;
            boolean end = false;
            
            while (size == Osize && !end) //loop until size changes - node added - or end is triggered 
            {                             //if node already exists and nothing is added
                int result = comparator.compare(key, m.getKey());

                if (result < 0)
                {
                    if (!m.hasLeftChild())
                    {
                        m.setLeftChild(nodeToAdd);
                        size++;
                        end = true;
                    }
                    else
                    {
                        m = m.getLeftChild();
                    }
                }
                else if (result > 0)
                {
                    if (!m.hasRightChild())
                    {
                        m.setRightChild(nodeToAdd);
                        size++;
                        end = true;
                    }
                    else
                    {
                        m = m.getRightChild();
                    }
                }
                else
                {
                    end = true;
                }
            }
        }
    }

    /**
     * Determines if their is a node in the tree with the given key or not
     * @param key the key to search for
     * @return true if a node does exists , false otherwise
     */
    public boolean containskey(keytype key)
    {
        boolean result = false;

        if (root != null)
        {
            BinaryNode<keytype, datatype> m = root;

            while (result == false && m != null)
            {
                int compared = comparator.compare(key, m.getKey());

                if (compared == 0)
                {
                    result = true;
                }
                else if (compared < 0)
                {
                    m = m.getLeftChild();
                }
                else if (compared > 0)
                {
                    m = m.getRightChild();
                }
            }
        }     

        return result;
    }

    /**
     * Searches the tree for the given key, returns the fata from the first node that it finds
     * with a matching key or null if not found.
     *  
     * We begin by examining the root node. If the tree is null, the data we are searching 
     * for does not exist in the tree. Otherwise, if the data equals that of the root, the 
     * search is successful. If the data is less than the root, search the left subtree. 
     * Similarly, if it is greater than the root, search the right subtree. This process is 
     * repeated until the data is found or the remaining subtree is null. If the searched data 
     * is not found before a null subtree is reached, then the item must not be present in the tree.
     * 
     * @see http://en.wikipedia.org/wiki/Binary_search_tree#Searching
     * @param key the key to use for comparisons on where to search for the data in the tree
     * @return the found data object or null if it was not found in the tree
     */
    public datatype find(keytype key)
    {
        datatype result = null;

        if (root != null)
        {            
            BinaryNode<keytype, datatype> item = getItem(key);

            if (item != null)
            {
                result = item.getData();
            }
        }     

        return result;
    }

    /**
     * grabs a node from the tree based on the key
     * @param key is the search term used to find the node
     * @return the node found that matches the key, null if not found
     */
    private BinaryNode<keytype, datatype> getItem(keytype key)
    {
        BinaryNode<keytype, datatype> result = null;

        BinaryNode<keytype, datatype> m = root;

        while (result == null && m != null)
        {
            int compared = comparator.compare(key, m.getKey());

            if (compared == 0)
            {
                result = m;
            }
            else if (compared < 0)
            {
                m = m.getLeftChild();
            }
            else if (compared > 0)
            {
                m = m.getRightChild();
            }
        }

        return result;
    }

    /**
     * Removes a specific node from the tree that has a matching key value
     * 
     *  There are five possible cases to consider:
     *      (1) the tree is empty ( root == null )
     *      (2) only one node in the tree ( root = null )
     *      (3) Deleting a leaf (node with no children): 
     *                Deleting a leaf is easy, as we can simply remove it from the tree.
     *      (4) Deleting a node with one child: 
     *                Remove the node and replace it with its child.
     *      (5) Deleting a node with two children: 
     *                Call the node to be deleted N. Do not delete N. Instead, choose its 
     *                in-order successor (R) . Replace the value of N with the value of R, then delete R.
     *
     *       As with all binary trees, a node's in-order successor is the left-most child of its right subtree.
     * 
     * @see http://en.wikipedia.org/wiki/Binary_search_tree#Deletion
     * 
     * @param key the key to use for the search
     * @return the data at the node or null if no such node found
     */
    public datatype remove(keytype key)
    {
        datatype result = null;

        if (root != null)
        {
            if (root.isLeaf())
            {
                result = root.getData();
                root = null;
                size--;
            }
            else
            {
                BinaryNode<keytype, datatype> toRemove = getItem(key);

                if (toRemove != null)
                {
                    if (toRemove.isLeaf())
                    {
                        result = toRemove.getData();
                        toRemove.unLinkFromParent();  
                        toRemove = null; 
                        size--;
                    }
                    else if (toRemove.childrenCount() == 1)
                    {
                        result = toRemove.getData();

                        if(toRemove.hasLeftChild())
                        {
                            toRemove.replaceWith(toRemove.getLeftChild());
                        }
                        else
                        {
                            toRemove.replaceWith(toRemove.getRightChild());
                        }

                        toRemove = null;
                        size--;
                    }
                    else
                    {
                        BinaryNode<keytype, datatype> temp = getSuccessor(toRemove); //grabs the successor child

                        if (temp.isLeaf())
                        {
                            temp.setRightChild(toRemove.getRightChild()); //setting new values for children
                            temp.setLeftChild(toRemove.getLeftChild());
                           
                            result = toRemove.getData(); //grab contents
                            
                            toRemove.replaceWith(temp); //removal
                            temp.unLinkFromParent();
                            toRemove = temp = null;
                            size--;
                        }
                        else
                        {
                            BinaryNode<keytype, datatype> childTemp = temp.getRightChild(); // get temp var of child
                            
                            temp.setRightChild(toRemove.getRightChild()); //set new children nodes
                            temp.setLeftChild(toRemove.getLeftChild());
                            
                            result = toRemove.getData(); //grab contents
                            
                            toRemove.replaceWith(temp); //replace
                            temp.unLinkFromParent();    
                            add(childTemp.getKey(), childTemp.getData());
                            
                            toRemove = temp = childTemp = null; //null all tmep objetcs to complet remove
                            
                            size = size - 2; //size minus 2 because we remove node + child node, then place both back in
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * takes a node and finds the successor child
     * @param node to begin with
     * @param smallest of the largest children from the beginning node
     */
    private BinaryNode<keytype, datatype> getSuccessor(BinaryNode<keytype, datatype> node)
    {
        BinaryNode<keytype, datatype> temp = node;

        temp = temp.getRightChild();

        while (temp.hasLeftChild() != false)
        {
            temp = temp.getLeftChild();
        }

        return temp;
    }

    /**
     * Properly removes all nodes from the tree. You must remove each node in the tree individual 
     * to properly unlink everything You are allowed to use a traversal in this method. Try to 
     * remove the leaf nodes first and end with the root node
     */
    public void clear()
    {
        BinaryTreeIterator<keytype, datatype> iter = getTraversalIterator(POST_TRAV);

        while (iter.hasNext())
        {
            BinaryNode<keytype, datatype> temp = iter.getCurrentNode();

            if ( comparator.compare(temp.getKey(), root.getKey()) != 0 )
            {
                temp.unLinkFromParent();  
                temp = null; 
            }
            else
            {
                root = null;               
            }
            
            size--;
            iter.next();
        }
    }

    /**
     * prints the tree nodes to the console/standard output using the specified traversal
     * @param type must be one of the constants provided by the class (PRE_TRAV,IN_TRAV,POST_TRAV,LEVEL_TRAV)
     */
    public void printAllNodes(int type)
    {
        print(type, PRINT_NODE);
    }

    /**
     * prints the just the key from each node to the console/standard output using the specified traversal
     * @param type must be one of the constants provided by the class (PRE_TRAV,IN_TRAV,POST_TRAV,LEVEL_TRAV)
     */
    public void printJustKeys(int type)
    {
        print(type, PRINT_KEY);
    }

    /**
     * prints the just the data values from each node to the console/standard output using the specified traversal
     * @param type must be one of the constants provided by the class (PRE_TRAV,IN_TRAV,POST_TRAV,LEVEL_TRAV)
     */
    public void printJustData(int type)
    {
        print(type, PRINT_DATA);
    }

    /**
     * Helper method used to print out the tree with the given traversal
     * @param type the type of traversal
     * @param valueType the type of data output
     */
    private void print(int type, int valueType)
    {
        BinaryTreeIterator<keytype, datatype> iter = getTraversalIterator(type);
        System.out.print("TREE[ ");
        while(iter.hasNext())
        {
            BinaryNode<keytype, datatype> node = iter.getCurrentNode();
            String dataOutPut = "";
            switch(valueType)
            {
                case PRINT_NODE:  dataOutPut += node; break;
                case PRINT_DATA:  dataOutPut = ""+node.getData(); break;
                default:  dataOutPut = ""+node.getKey(); break;
            }

            System.out.print("("+dataOutPut+")");

            iter.next();

            if(iter.hasNext())
                System.out.print(" , ");
        }
        System.out.println(" ]");

    }

}
