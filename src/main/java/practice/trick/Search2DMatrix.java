package practice.trick;

import static org.junit.Assert.*;

import org.junit.Test;

/*
Trick binary search.
 */
//https://leetcode.com/problems/search-a-2d-matrix-ii/
/*
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
 */
public class Search2DMatrix {

    public static boolean searchMN(int[][] matrix, int target) {
        int endCol = matrix[0].length -1;
        int fromRow = 0;
        if (target<matrix[0][0] || target>matrix[matrix.length-1][endCol]){
            return false;
        }

        while (endCol>0 && fromRow<matrix.length-1) {
            endCol = rowSearch(matrix, target, endCol, fromRow);

            if (matrix[fromRow][endCol] == target) {
                return true;
            }
            fromRow = colSearch(matrix, target, fromRow, endCol);
            if (matrix[fromRow][endCol]==target){
                return true;
            }
        }
        if (endCol==0){
            fromRow = colSearch(matrix, target, fromRow, endCol);
            if (matrix[fromRow][endCol]==target){
                return true;
            } else {
                return false;
            }
        } else {
            endCol = rowSearch(matrix, target, endCol, fromRow);
            if (matrix[fromRow][endCol] == target) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * find the first row from end which is less than
     */
    public static int rowSearch(int[][] matrix, int target, int endCol, int row){

        int end = endCol;
        int start = 0;
        while (end-start >=2){
            int middle = start + (end-start)/2;
            if (matrix[row][middle]==target){
                return middle;
            } else if (matrix[row][middle]>target){
                end=middle;
            } else {
                start=middle;
            }
        }

        if (matrix[row][start]<=target){
            return start;
        }
        return start-1;
    }

    /**
     * Find the col from top which is bigger than target.
     */
    public static int colSearch(int[][] matrix, int target, int fromRow, int col){
        int start = fromRow;
        int end = matrix.length;

        while (end-start>=2){
            int middle = start + (end-start)/2;
            if (matrix[middle][col]==target){
                return middle;
            } else if (matrix[middle][col]>target){
                end= middle;
            } else {
                start = middle;
            }

        }

        if (matrix[start][col]<=target){
            return end;
        }
        return start;
    }


    public static final class Test1 {
        @Test
        public void testMN(){
            int[][] matrix={
                    {1,4,7,11,15},
                    {2,5,8,12,19},
                    {3,6,9,16,22},
                    {10,13,14,17,24},
                    {18,21,23,26,30}
            };

            assertTrue(searchMN(matrix, 1));
            assertTrue(searchMN(matrix, 5));
            assertTrue(searchMN(matrix, 2));
            assertTrue(searchMN(matrix, 18));
            assertFalse(searchMN(matrix, 0));
            assertFalse(searchMN(matrix, 35));
            assertFalse(searchMN(matrix, 20));
        }
    }
}
