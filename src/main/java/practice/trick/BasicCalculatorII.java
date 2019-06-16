package practice.trick;

import org.junit.Test;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

//https://leetcode.com/problems/basic-calculator-ii/
/*
 * Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, /
operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5

 */
public class BasicCalculatorII {
    int i = 0;

    public int calculate(String s) {
        s = s + "+";
        return calculatePlusMinusSegment(s);
    }

    public int calculatePlusMinusSegment(String s) {

        char preOp = '+';
        int num = 0;
        int sum = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            } else if (c == '+' || c == '-') {
                sum = preOp == '+' ? sum + num : sum - num;
                preOp = c;
                num = 0;
            } else if (c == '*' || c == '/') {
                num = calculateMulicationSegment(s, num);
                i--;
            } else if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            }
            i++;
        }
        return sum;
    }

    public int calculateMulicationSegment(String s, int preNum) {
        int num = 0;
        int sum = preNum;
        char preOp = s.charAt(i);
        i++;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            } else if (c == '*' || c == '/') {
                sum = preOp == '*' ? sum * num : sum / num;
                preOp = c;
                num = 0;
            } else if (c == '+' || c == '-') {
                sum = preOp == '*' ? sum * num : sum / num;
                return sum;
            } else if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            }
            i++;
        }
        return sum;
    }

    public static class TestCase {

        @Test
        public void test() {
            BasicCalculatorII calculator = new BasicCalculatorII();
            assertEquals(7, calculator.calculate("3+2*2"));
            calculator.i = 0;
            assertEquals(1, calculator.calculate("3+2*2-6"));
            calculator.i = 0;
            assertEquals(43, calculator.calculate("32+4*3*8/6-5"));
            calculator.i = 0;
            assertEquals(5, calculator.calculate("3-5+7"));
        }

        @Test
        public void test1() {
            BasicCalculatorII calculator = new BasicCalculatorII();
            assertEquals(1, calculator.calculate("3/2"));
        }

        @Test
        public void test2() {
            BasicCalculatorII calculator = new BasicCalculatorII();
            assertEquals(5, calculator.calculate("3 +5 /2"));
        }
    }


}