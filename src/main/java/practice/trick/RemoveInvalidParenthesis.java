package practice.trick;
/*
 *https://leetcode.com/problems/remove-invalid-parentheses/

	 Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]

 * There two solutions: Check each one to view the pro and con;
 *
 */

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/*

 The first question we should ask. What is a brute force solution?
 At any position, we can remove it or keep it. At the end, we check whether it is valid or not.
 If it is not valid, discard it.
 If it is valid, if the number of letter doesn't equal to the number of maximal valid.  It doesn't
 meet the constraint.


 But we can do better.

 It should be clear. We don't know whether it is valid or not until the last moment of string (suppose we don't know
 string lenghth). For example, give a string "((((". When we reach the end, we know it is invalid. But before it,
 we don't know.
 If we process from backend, we immediately know it is invalid.
 1 we have complete information about the string
 1. we incorporate string length implicitly.

 We scan the input once.
 At each position,
 We records two number: number of left parenthesis, and the  number of right parenthensis including the one at current position.

 Then we process from backend.

 There are 3 cases
 At this position, there are equal number of (, and ). We can't remove anything. remove current character will make final invalid.
 At this position, if the number of ( > the number of ),
    remove if current is (,
    do nothing if current is (. we can make the string valid again by removing more ( befor current one.
    otherwise does nothing

 At this position, if the number of ) > the number of (,
   remove if current is ).
   do nothing if current is ). we can make the string valid again by removing more ) befor current one.
   Otherwise does nothting.

 When we remove, we need to leave followinng process know how many we removed.

 Give this string ())(
 The number we record [1,0], [1,1][1,2],[2,2].
 We can't keep [2,2] here since [2,2] is perfect valid.
 So if it is right imbalance, we should start with [1,0] again.


 */
public class RemoveInvalidParenthesis {

    static List<String> bruteForce(String s) {

        Set<String> result = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        helpBuilder(s, 0, sb, result);
        return result.stream().filter(str -> str.length() == maxLength).sorted().collect(Collectors.toList());
    }

    static int maxLength = 0;

    static void helpBuilder(String s, int index, StringBuilder sb, Set<String> buffer) {
        if (index == s.length()) {
            if (isValid(sb.toString())) {
                buffer.add(sb.toString());
                maxLength = Math.max(sb.length(), maxLength);
            }
            return;
        }

        int length = sb.length();
        //two options for index, remove or keep
        helpBuilder(s, index + 1, sb, buffer);
        sb.append(s.charAt(index));
        helpBuilder(s, index + 1, sb, buffer);
        sb.setLength(length);
    }

    static boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push('(');
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static final class TestCase {
        @org.junit.Test
        public void test() {

            String result= bruteForce("()())()").stream().collect(Collectors.joining(","));
            assertEquals(result, "(())(),()()()");
            maxLength = 0;

            result= bruteForce("(a)())()").stream().collect(Collectors.joining(","));
            assertEquals(result, "(a())(),(a)()()");
            maxLength = 0;
            result= bruteForce(")(").stream().collect(Collectors.joining(","));
            assertEquals(result, "");
            maxLength = 0;
            result= bruteForce("()").stream().collect(Collectors.joining(","));
            assertEquals(result, "()");
        }
    }

}
