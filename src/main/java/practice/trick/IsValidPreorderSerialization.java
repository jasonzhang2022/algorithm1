package practice.trick;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.Iterator;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/*
Trick: Iterator to remember what string stops.
 */

/*
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter
 * a non-null node, we record the node's value. If it is a null node, we record using a
 * sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#",
where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal
 serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#'
 representing null pointer.

You may assume that the input format is always valid, for example it could never
contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false
 */
public class IsValidPreorderSerialization {
    public static boolean isValid(String s) {
        Iterator<String> tokens = Splitter.on(',').split(s).iterator();
        return isValid(tokens) && !tokens.hasNext();
    }

    static boolean  isValid(Iterator<String> tokens){
        if (!tokens.hasNext()) {
            return false;
        }

        String next = tokens.next();
        if (next.equals("#")){
            //leaf node.
            return true;
        }


        //next is a root
        if (isValid(tokens) && isValid(tokens)){
            return true;
        }

        return false;

    }


    public static class TestC {

        @Test
        public void test(){
            assertTrue(isValid("9,#,#"));
            assertFalse(isValid("9,#"));
            assertFalse(isValid("9,#,#,3"));
            assertFalse(isValid("9,#,#,#"));
            assertTrue(isValid("9,4,#,#,3,#,#"));
            assertTrue(isValid("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        }

    }

}
