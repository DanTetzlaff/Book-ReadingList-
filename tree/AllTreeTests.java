package tree;
/**
 * JUnit test Suite
 *  
 *  Runs all tests for the Binary Search Tree
 * @author JKidney
 * @version 1 
 * 
 *      Created: Oct 17, 2013
 * Last Updated: Oct 17, 2013 - creation (jkidney)
 *               Oct 24, 2013 - tests created (jkidney)
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestBinarySearchTree.class, TestBinaryTreeTraversals.class })
public class AllTreeTests 
{

}
