package practice.binarytree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class KClosestUsingStackPredecessorSuccessor {

    Deque<TreeNodeDouble> closestKValues(TreeNodeDouble node, double value, int k) {
        Stack<TreeNodeDouble> predeccessors = new Stack<>();
        Stack<TreeNodeDouble> successors= new Stack<>();

        findNode(node, value, predeccessors, successors);

        Deque<TreeNodeDouble> kitems = new LinkedList<TreeNodeDouble>();
        if (found!=null) {
            ((LinkedList<TreeNodeDouble>) kitems).add(found);
        }


        while (kitems.size() < k) {

            TreeNodeDouble predecessor = predeccessors.isEmpty() ? null :predeccessors.peek();
            TreeNodeDouble successor = successors.isEmpty() ? null : successors.peek();
            double leftDisstance = (predecessor == null ? Double.MAX_VALUE : value - predecessor.value);
            double rightDIstance = (successor == null ? Double.MAX_VALUE : successor.value - value);

            if (leftDisstance < rightDIstance) {
                kitems.offerFirst(predecessor);
                findPredecessor(predeccessors);
            } else {
                kitems.offerLast(successor);
                findSuccessor(successors);
            }
        }
        return kitems;
    }

    TreeNodeDouble found = null;

    /**
     * Different from previosu findNode.
     * This method separates its ancestor into
     */
    void findNode(TreeNodeDouble node, double value, Stack<TreeNodeDouble> predecessors, Stack<TreeNodeDouble> successors) {
        if (node.value == value) {
            found = node;
            return;
        }
        if (node.value > value) {
            successors.push(node);
            if (node.left != null) {
                findNode(node.left, value, predecessors, successors);
            }
        } else {
            predecessors.push(node);
            if (node.right != null) {
                findNode(node.right, value, predecessors, successors);
            }
        }
    }

    void findPredecessor(Stack<TreeNodeDouble> predecessors) {
        TreeNodeDouble node = predecessors.pop();
        if (node.left != null) {
            TreeNodeDouble goright = node.left;
            while (goright != null) {
                predecessors.push(goright);
                goright = goright.right;
            }
            return;
        }
    }

    void findSuccessor( Stack<TreeNodeDouble> successors) {
        TreeNodeDouble node = successors.pop();

        if (node.right != null) {
            TreeNodeDouble goleft = node.right;
            while (goleft != null) {
                successors.push(goleft);
                goleft = goleft.left;
            }
            return;
        }
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
            KClosestUsingStackPredecessorSuccessor traverser = new KClosestUsingStackPredecessorSuccessor();
            assertEquals(traverser.closestKValues(constructTreeNodeDouble(), 10.3, 3).stream().map(i -> String.valueOf(Double.valueOf(i.value).intValue()))
                    .collect(Collectors.joining(",")), "10,20,25");
        }

        @Test
        public void testRight() {
            KClosestUsingStackPredecessorSuccessor traverser = new KClosestUsingStackPredecessorSuccessor();

            assertEquals(traverser.closestKValues(constructTreeNodeDouble(), 60.3, 3).stream().map(i -> String.valueOf(Double.valueOf(i.value).intValue()))
                    .collect(Collectors.joining(",")), "40,50,60");

        }

        @Test
        public void testCenter() {
            KClosestUsingStackPredecessorSuccessor traverser = new KClosestUsingStackPredecessorSuccessor();

            assertEquals(traverser.closestKValues(constructTreeNodeDouble(), 32.3, 3).stream().map(i -> String.valueOf(Double.valueOf(i.value).intValue()))
                    .collect(Collectors.joining(",")), "25,30,40");
        }

        @Test
        public void testOneMore() {
            KClosestUsingStackPredecessorSuccessor traverser = new KClosestUsingStackPredecessorSuccessor();

            assertEquals(traverser.closestKValues(constructTreeNodeDouble(), 59.4, 3).stream().map(i -> String.valueOf(Double.valueOf(i.value).intValue()))
                    .collect(Collectors.joining(",")), "40,50,60");
        }
    }
}
