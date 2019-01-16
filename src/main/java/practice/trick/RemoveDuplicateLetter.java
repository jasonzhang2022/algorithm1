package practice.trick;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
  Stack usage and disjoint set usage.
 disjoint set usage: maintain next, prev point.
 */

//https://leetcode.com/problems/remove-duplicate-letters/
/*
 * Given a string which contains only lowercase letters, remove duplicate letters so that every
  * letter appear once and only once. You must make sure your result is the smallest
   * in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"


 */
public class RemoveDuplicateLetter {

    public static String removeStack(String s){

        int[] count = new int[26];
        Stack<Character> stack = new Stack();
        boolean[] instack = new boolean[26];

        for (char c: s.toCharArray()){
            count[c-'a']++;
        }
        for (char c: s.toCharArray()){
            while (!stack.isEmpty()) {
                char topc = stack.peek();
                if (count[topc-'a']>0 && topc>=c){
                    stack.pop();
                    instack[topc-'a']= false;
                }   else {
                    break;
                }
            }
            if (!instack[c-'a']) {
                stack.push(c);
                count[c-'a']--;
                instack[c-'a']=true;
            }

        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static String removeDisjoint(String s){
        char[] chars = s.toCharArray();

        // last index one character is at.
        int[] lastIndex = new int[26];
        Arrays.fill(lastIndex, -1);

        /**
         * Each set represents one char.
         * When one character[i] is deleted, set[i] is unioned with set[i+1]
         */
        int[] set = new int[s.length()];


        for (int i=0; i<s.length(); i++){
            set[i]=i;
        }

        for (int i=0; i<chars.length; i++){
            char c = chars[i];
            int cIndex = c-'a';
            if (lastIndex[cIndex] ==-1){
                lastIndex[cIndex]=i;
            } else {
                int prevcIndex = lastIndex[cIndex];

                //nextCharIndex can't be out of boundary. The maximal value is i.
                int nextCharIndex = findNextCharIndex(prevcIndex+1, set);

                char nextChar = chars[nextCharIndex];
                if (nextChar<c){
                    //we remove c at lastCharIndex;
                    lastIndex[cIndex]=i;
                    set[prevcIndex]= nextCharIndex;
                } else {
                    //we remove current c.
                    set[i]=i+1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        int setid =0;
        while (setid<s.length()){
            int freeCharid = findNextCharIndex(setid, set);
            if (freeCharid<s.length()) {
                sb.append(s.charAt(freeCharid));
            }
            setid=freeCharid+1;
        }
        return sb.toString();
    }

    static int findNextCharIndex(int i, int[] set){
        if (i>=set.length){
            return i;
        }
        if (set[i]!=i){
            set[i]=findNextCharIndex(set[i], set);
        }
        return set[i];
    }




    public static final class Test {
        @org.junit.Test
        public void test1(){
            assertEquals("bda", removeDisjoint("bdba"));
            assertEquals("abc", removeDisjoint("baabc"));
            assertEquals("acdb", removeDisjoint("cbacdcbc"));
        }
        @org.junit.Test
        public void test2(){
            assertEquals("bda", removeStack("bdba"));
            assertEquals("abc", removeStack("baabc"));
            assertEquals("acdb", removeStack("cbacdcbc"));
        }
    }
}
