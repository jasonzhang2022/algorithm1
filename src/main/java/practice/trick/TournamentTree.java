package practice.trick;
/*
第一题
完全二叉树 parent是孩子中的最小值，请找出整棵树第二小的值
example:
*     2
*    / \
*   2   3
*  / \  | \
* 4   2 5  3

* In this given tree the answer is 3.
解法 一开始装傻提出O(n),两分钟后给出了O(logn)的解法
解法是follow up的 多叉树
*/
//http://www.geeksforgeeks.org/tournament-tree-and-binary-heap/
/*
 * It is like a merge process
 *
 * We start from leaf node, each time we move smaller one to the parent.
 * When we reach the parent, we have the minimum.
 *
 * The assumption: there is no two leaf nodes with the same values.
 *
 * For a particular internal node, it has two children. The internal node's value is from one of the child.
 *
 *
 * Suppose the two lists in sorted order is
 * L1,L2,L3... (left subtree)
 * R1,R2...  (right subtree)
 *
 * Suppose L1 is minimum L1<R1, Apparently, the  second minimum will be R1 or L2. We already knows R1 which is the minimum of
 * right subtree.
 * It comes how  we can find L2?  When L1 move up to root from leaf, every comparison is like a comparison between L1 and the
 * minimum of a subtree. Here the substree is like a collection of elements( a portion of all data).
 * The second best should the minimum of all these subtree minimums. This is like selection sort. Suppose
 * we have N list, we find the minimum is L1, We want to know the second one to select. We just select the minimum of from heading element
 * of N-1 remaining list. So we just walk down the tree from root to track back where the root node comes from.
 *
 *
 *
 *
 *
 *
 *
 *
 *

 *
 *
 *
 */
public class TournamentTree {
}
