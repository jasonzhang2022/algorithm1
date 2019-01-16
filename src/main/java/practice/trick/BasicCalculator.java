package practice.trick;

import com.google.common.collect.Iterables;
import org.junit.Test;

import java.text.CharacterIterator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

//https://leetcode.com/problems/basic-calculator/
/*
 * mplement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ),
the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
 */
/*
Trick:
  StringIterator and recursion
 */
public class BasicCalculator {
    public static int calculate(String s) {
        Iterator<Character> str=s.chars().mapToObj(c->(char)c).collect(Collectors.toList()).iterator();
        return help(str);
    }


    public static int help(Iterator<Character> str){
        int sum = 0;
        int num =0;
        int sign = 1;
        while (str.hasNext()) {

            char c= str.next();
            if (c== ' ' ) {
                continue;
            } else if(Character.isDigit(c)){
                num=num*10+ (c-'0');
            } else if ( c == '+'){
                sum += sign*num;
                sign = 1;
                num = 0;
            } else if (c== '-') {
                sum+=sign*num;
                sign = -1;
                num = 0;
            } else if (c==')'){
                sum+=sign*num;
                return sum;
            } else if (c=='(') {
                num = help(str);
            }
        }
        return sum+sign*num;
    }

    public static final class TestCase {
        @Test
        public void test() {
            assertEquals(2, calculate("1 + 1"));
        }

        @Test
        public void test1() {
            assertEquals(3, calculate("2-1 + 2"));
        }

        @Test
        public void test2() {
            assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"));
        }
    }
}
