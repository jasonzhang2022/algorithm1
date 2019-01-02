package practice.binarytree;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class KClosest {
    Map<TreeNodeDouble, TreeNodeDouble> parents = new IdentityHashMap<>();

    //assume that value doesn't exist in tree.
    Deque<TreeNodeDouble> closestKValues(TreeNodeDouble node, double value, int k) {
        TreeNodeDouble foundNode = findNode(node, value);

        TreeNodeDouble startNode = null;
        if (foundNode.value < value) {
            TreeNodeDouble successor = findSuccessor(foundNode);
            if (successor != null && Math.abs(successor.value - value) < Math.abs(foundNode.value - value)) {
                startNode = successor;
            }
        }  else if (foundNode.value > value) {
            TreeNodeDouble predecessor = findPredecessor(foundNode);
            if (predecessor != null && Math.abs(predecessor.value - value) < Math.abs(foundNode.value - value)) {
                startNode = predecessor;
            }
        }
        if (startNode == null) {
            startNode = foundNode;
        }

        Deque<TreeNodeDouble> kitems = new LinkedList<TreeNodeDouble>();
        kitems.add(startNode);
        while (kitems.size() < k) {
            TreeNodeDouble predecessor = findPredecessor(kitems.peekFirst());
            double leftDistance = (predecessor == null ? Double.MAX_VALUE : Math.abs(predecessor.value - value));

            TreeNodeDouble successor = findSuccessor(kitems.peekLast());
            double rightDistance = (successor == null ? Double.MAX_VALUE : Math.abs(successor.value - value));
            if (leftDistance < rightDistance) {
                kitems.offerFirst(predecessor);
            } else {
                kitems.offerLast(successor);
            }
        }
        return kitems;
    }


    /**
     * Find the node with value = value
     * or a parent node which a child node should be positioned.
     */
    TreeNodeDouble findNode(TreeNodeDouble node, double value) {

        if (node.value == value) {
            return node;
        }
        if (node.value > value) {
            //should go to left.
            if (node.left != null) {
                parents.putIfAbsent(node.left, node);
                return findNode(node.left, value);
            } else {
                return node;
            }
        } else {
            if (node.right != null) {
                parents.putIfAbsent(node.right, node);
                return findNode(node.right, value);
            } else {
                return node;
            }
        }
    }


    /**
     * Successor: left most node at right subtree.
     * or first parent at right branch.
     */
    TreeNodeDouble findSuccessor(TreeNodeDouble node) {
        // does this node has right substree.
        // if it does, find the left node in substree.
        if (node.right != null) {
            return findLeft(node.right);
        }

        TreeNodeDouble child = node;
        while (parents.get(child) != null) {
            TreeNodeDouble parent = parents.get(child);
            if (parent.left == child) {
                return parent;
            }
            child = parent;
        }

        return null;
    }


    /**
     * right most node at left subtree.
     * Or first parent node at left branch
     */
    TreeNodeDouble findPredecessor(TreeNodeDouble node) {
        if (node.left != null) {
            return findRight(node.left);
        }

        TreeNodeDouble child = node;
        while (parents.get(child) != null) {
            TreeNodeDouble parent = parents.get(child);
            if (parent.right == child) {
                return parent;
            }
            child = parent;
        }

        return null;
    }

    private TreeNodeDouble findLeft(TreeNodeDouble node) {
        while (node.left != null) {
            parents.putIfAbsent(node.left, node);
            node = node.left;
        }
        return node;
    }

    private TreeNodeDouble findRight(TreeNodeDouble node) {
        while (node.right != null) {
            parents.putIfAbsent(node.right, node);
            node = node.right;
        }
        return node;
    }


    public static class TestCase {

        public TreeNodeDouble constructTreeNodeDouble() {
            TreeNodeDouble root = new TreeNodeDouble(30);
            root.left = new TreeNodeDouble(20);
            root.left.left = new TreeNodeDouble(10);
            root.left.right = new TreeNodeDouble(25);

            root.right = new TreeNodeDouble(50);
            root.right.left = new TreeNodeDouble(40);
            root.right.right = new TreeNodeDouble(60);

            return root;
        }


        @Test
        public void testLeft() {
            KClosest traverser = new KClosest();
            assertEquals(traverser.closestKValues(constructTreeNodeDouble(), 10.3, 3).stream().map(i -> String.valueOf(Double.valueOf(i.value).intValue()))
                    .collect(Collectors.joining(",")), "10,20,25");
        }

        @Test
        public void testRight() {
            KClosest traverser = new KClosest();

            assertEquals(traverser.closestKValues(constructTreeNodeDouble(), 60.3, 3).stream().map(i -> String.valueOf(Double.valueOf(i.value).intValue()))
                    .collect(Collectors.joining(",")), "40,50,60");

        }

        @Test
        public void testCenter() {
            KClosest traverser = new KClosest();

            assertEquals(traverser.closestKValues(constructTreeNodeDouble(), 32.3, 3).stream().map(i -> String.valueOf(Double.valueOf(i.value).intValue()))
                    .collect(Collectors.joining(",")), "25,30,40");
        }

        @Test
        public void testOneMore() {
            KClosest traverser = new KClosest();

            assertEquals(traverser.closestKValues(constructTreeNodeDouble(), 59.4, 3).stream().map(i -> String.valueOf(Double.valueOf(i.value).intValue()))
                    .collect(Collectors.joining(",")), "40,50,60");
        }
    }
}
