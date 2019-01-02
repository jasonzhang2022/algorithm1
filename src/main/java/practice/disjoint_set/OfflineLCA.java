package practice.disjoint_set;

import org.junit.Test;
import practice.binarytree.LCA;
import practice.binarytree.TreeNode;

import java.util.*;

import static org.junit.Assert.assertEquals;

/*

How does this differ from other LCA.
This algorithm traverse the tree once and answer many LCA at once.

Other LCA only answer one LCA question.


Idea:
A node is marked black when all its descendant are marked as black.

The node and its left branch is in one disjoint set. One branch is black. I term this
as branch disjoint set.


The branch disjoint set basically mirrors the the tree. The disjoint set tree points node to parent.

1. it uses path compression, when a find(v) is called, v.parent is set to LCA.
2. if a node is just mark as black, all its ancestor is not white. But its partner node ancestor is LCA

 */
public class OfflineLCA {


    Map<Integer, Set<Integer>> lcas = new HashMap<>();

    Set<Integer> blacks = new HashSet<>();

    Map<Integer, Integer> disjoinSets = new HashMap<>();

    public Map<String, Integer> query(TreeNode root, int[][] queries) {
        for (int[] pair : queries) {
            lcas.merge(pair[0], new HashSet<>(Arrays.asList(pair[1])), (key, oldList) -> {
                oldList.add(pair[1]);
                return oldList;
            });
            lcas.merge(pair[1], new HashSet<>(Arrays.asList(pair[0])), (key, oldList) -> {
                oldList.add(pair[0]);
                return oldList;
            });
        }
        lca(root);
        return results;
    }

    Map<String, Integer> results = new HashMap<>();

    void lca(TreeNode node){
        //node's  parent it itself.
        disjoinSets.put(node.value, node.value);
        if (node.left!=null ){
            lca(node.left);
            union(node.value, node.left.value);
        }
        if (node.right!=null){
            lca(node.right);
            union(node.value, node.right.value);
        }
        blacks.add(node.value);

        Set<Integer> keys= lcas.getOrDefault(node.value, Collections.emptySet());
        for ( int key: keys) {
            if (blacks.contains(key)){
                results.put(String.format("%d_%d", Math.min(node.value, key), Math.max(node.value, key)), find(key));
            }
        }
    }

    int find(int v){
        int p=v;
        while (disjoinSets.get(p)!=p){
            p = find(disjoinSets.get(p));
        }
        disjoinSets.put(v, p);
        return p;
    }

    void union(int pvalue, int child){
        int pparent =find(pvalue);
        int cparent = find(child);
        //point child parent to parent node;
        disjoinSets.put(cparent, pparent);
    }

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
        public void testLCAOffline() {
            TreeNode root = buildTree();
            OfflineLCA  lca = new OfflineLCA ();

            int[][] input= {{10, 27}, {10, 20}, {10, 55}, {40, 65}};
            Map<String, Integer> output = lca.query(root, input);

            assertEquals(output.get("10_27").intValue(), 20);
            assertEquals(output.get("10_20").intValue(), 20);
            assertEquals(output.get("10_55").intValue(), 30);
            assertEquals(output.get("40_65").intValue(), 50);
        }
    }

}
