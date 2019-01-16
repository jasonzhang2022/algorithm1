package practice.trick;

//https://leetcode.com/problems/find-peak-element/
/*
 * A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */
/*
BS approach.

Binary search one element.
if the element >= both neighour element, found.
If the element is less than right side, there is definitely one peak element at right side.
If the element is less than left side, there is definitely one peak element at left side.

No typical Binary search problem. But works.

 */
public class FindPeakElement {
}
