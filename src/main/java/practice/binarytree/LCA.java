package practice.binarytree;


import org.junit.Test;

import java.util.function.BiConsumer;

import static org.junit.Assert.assertEquals;

/**
 * Why LCA?
 * <p>
 * LCA is a good example how you can traverse the tree.
 */
public class LCA {

    /**
     * If interviewer mentions BST, we need to use BST property.
     */
    public int lcaForBST(TreeNode node, int smallValue, int bigValue) {

        if (node.value >= smallValue && node.value <= bigValue) {
            return node.value;
        }

        if (node.value > bigValue) {
            //both value are at left tree.
            return lcaForBST(node.left, smallValue, bigValue);
        }
        return lcaForBST(node.right, smallValue, bigValue);
    }

    // scan the whole tree: print out the first node which containing the both nodes
    int foundLCA = -1;

    public int lcaForBT(TreeNode node, int smallValue, int bigValue) {

        if (node == null) {
            return 0;
        }
        int foundValueAtLeft = lcaForBT(node.left, smallValue, bigValue);
        int foundValueAtRight = lcaForBT(node.right, smallValue, bigValue);
        if (foundValueAtLeft == 2 || foundValueAtRight == 2) {
            return 2;
        }
        if (foundValueAtLeft == 1 && foundValueAtRight == 1) {
            foundLCA = node.value;
            return 2;
        }
        if ((node.value == smallValue || node.value == bigValue) && (foundValueAtLeft == 1 || foundValueAtRight == 1)) {
            foundLCA = node.value;
            return 2;
        }
        if ((node.value == smallValue || node.value == bigValue) && (foundValueAtLeft == 0 && foundValueAtRight == 0)) {
            foundLCA = node.value;
            return 1;
        }
        return Math.max(foundValueAtLeft, foundValueAtRight);

    }


    /**
     * Suppose we have parent pointer.
     * For first node, trace parent to root. Add every ancestor node to map.
     * For second node, trace parent to root. Check whether the ancestor node in map or not. The first one found in map is the LCA.
     *
     * memory O(logN)
     *
     * Suppose we want to save memory
     *  trace first node, calculate its depth.
     *  trace second node, calculate its depth.
     *
     *  For node with big depth, walk to the same level with small depth.
     *  The both node walk together. If found idential node, it is LCA
     *
     *  One extra trace, but only use O(1) memory
     *
     */


    public static class TestCase {

        public TreeNode buildTree() {
            TreeNode root = new TreeNode(30);
            root.left = new TreeNode(20);
            root.left.left = new TreeNode(10);
            root.left.right = new TreeNode(25);
            root.left.right.left = new TreeNode(22);
            root.left.right.right = new TreeNode(27);

            root.right = new TreeNode(50);
            root.right.left = new TreeNode(40);
            root.right.right = new TreeNode(60);

            root.right.right.left = new TreeNode(55);
            root.right.right.right = new TreeNode(65);

            return root;
        }

        @Test
        public void testLCABST() {

            LCA lca = new LCA();
            TreeNode root = buildTree();
            assertEquals(lca.lcaForBST(root, 10, 27), 20);
            assertEquals(lca.lcaForBST(root, 10, 20), 20);
            assertEquals(lca.lcaForBST(root, 10, 55), 30);
            assertEquals(lca.lcaForBST(root, 40, 65), 50);
        }

        @Test
        public void testLCABT() {
            LCA lca = new LCA();
            TreeNode root = buildTree();
            lca.foundLCA = -1;
            lca.lcaForBT(root, 10, 27);
            assertEquals(lca.foundLCA, 20);

            lca.foundLCA = -1;
            lca.lcaForBT(root, 10, 20);
            assertEquals(lca.foundLCA, 20);

            lca.foundLCA = -1;
            lca.lcaForBT(root, 10, 55);
            assertEquals(lca.foundLCA, 30);

            lca.foundLCA = -1;
            lca.lcaForBT(root, 40, 65);
            assertEquals(lca.foundLCA, 50);
        }


    }

}
