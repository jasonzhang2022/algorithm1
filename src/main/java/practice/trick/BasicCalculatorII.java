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

    Deque<Integer> nums = new LinkedList<>();
    Deque<Character> ops  = new LinkedList<>();
    public int calculate(String s) {
        Iterator<Character> str =s.chars().mapToObj(c->(char)c).collect(Collectors.toList()).iterator();
        help(str);
        return nums.pollFirst();
    }

    public void help(Iterator<Character> str) {
        int num =0;
        while (str.hasNext()) {

            char c= str.next();
            if (c== ' ' ) {
                continue;
            } else if(Character.isDigit(c)){
                num=num*10+ (c-'0');
            } else if ( c == '+' || c=='-'){
                nums.offerLast(num);
                num = 0;
               calulateNumer();
               ops.offerLast(c);
            } else if (c== '*' || c == '/') {
                nums.offerLast(num);
                num=0;
                ops.offerLast(c);
            }
        }
        nums.offerLast(num);
        calulateNumer();
    }

    public void calulateNumer(){
        if (ops.isEmpty()){
            return;
        }
        int prefix = 0;
        char prefixOp='+';
        if (ops.peekFirst() == '+' || ops.peekFirst()=='-'){
            prefix = nums.pollFirst();
            prefixOp = ops.pollFirst();
        }

        int result = nums.pollFirst();
        while (!ops.isEmpty()){
            char c = ops.pollFirst();
            if (c=='*'){
                result*=nums.pollFirst();
            } else {
                result/=nums.pollFirst();
            }
        }
        if (prefixOp == '+'){
            result = prefix + result;
        } else {
            result = prefix - result;
        }
        nums.offerLast(result);
    }

    public static class TestCase{

        @Test
        public void test(){
            BasicCalculatorII calculator=new BasicCalculatorII();
            assertEquals(7, calculator.calculate("3+2*2"));
            assertEquals(1, calculator.calculate("3+2*2-6"));
            assertEquals(43, calculator.calculate("32+4*3*8/6-5"));
            assertEquals(5, calculator.calculate("3-5+7"));
        }
        @Test
        public void test1(){
            BasicCalculatorII calculator=new BasicCalculatorII();
            assertEquals(1, calculator.calculate("3/2"));
        }
        @Test
        public void test2(){
            BasicCalculatorII calculator=new BasicCalculatorII();
            assertEquals(5, calculator.calculate("3 +5 /2"));
        }
    }


}