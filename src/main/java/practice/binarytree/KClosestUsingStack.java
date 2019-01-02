package practice.binarytree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class KClosestUsingStack {

    Deque<TreeNodeDouble> closestKValues(TreeNodeDouble node, double value, int k) {
        Stack<TreeNodeDouble> path = new Stack<>();
        findNode(node, value, path);

        Deque<TreeNodeDouble> kitems = new LinkedList<TreeNodeDouble>();
        if (!added) {
            ((LinkedList<TreeNodeDouble>) kitems).add(path.peek());
        }

        Stack<TreeNodeDouble> predeccessorPath = path;
        Stack<TreeNodeDouble> successorPath = (Stack<TreeNodeDouble>) path.clone();

        findPredecessor(predeccessorPath);
        findSuccessor(successorPath);


        while (kitems.size() < k) {

            TreeNodeDouble predecessor = predeccessorPath.isEmpty() ? null : predeccessorPath.peek();
            TreeNodeDouble successor = successorPath.isEmpty() ? null : successorPath.peek();
            double leftDisstance = (predecessor == null ? Double.MAX_VALUE : value - predecessor.value);
            double rightDIstance = (successor == null ? Double.MAX_VALUE : successor.value - value);

            if (leftDisstance < rightDIstance) {
                kitems.offerFirst(predecessor);
                findPredecessor(predeccessorPath);
            } else {
                kitems.offerLast(successor);
                findSuccessor(successorPath);
            }
        }
        return kitems;
    }


    boolean added = false;
    void findNode(TreeNodeDouble node, double value, Stack<TreeNodeDouble> path) {
        if (node.value == value) {
            path.push(node);
            return;
        }
        if (node.value > value) {
            if (node.left != null) {
                path.push(node);
                findNode(node.left, value, path);
                return;
            } else {
                TreeNodeDouble left = new TreeNodeDouble(value);
                path.push(node);
                path.push(left);
                node.left = left;
                added = true;
                return;
            }
        } else {
            if (node.right != null) {
                path.push(node);
                findNode(node.right, value, path);
                return;
            } else {
                TreeNodeDouble right = new TreeNodeDouble(value);
                path.push(node);
                path.push(right);
                node.right = right;
                added = true;
                return;
            }
        }
    }

    /**
     * Different from previosu findNode.
     * This method separates its ancestor into
     */
    void findNode(TreeNodeDouble node, double value, Stack<TreeNodeDouble> predecessors, Stack<TreeNodeDouble> successors) {
        if (node.value == value) {
            return;
        }
        if (node.value > value) {
            if (node.left != null) {
                successors.push(node);
                findNode(node.left, value, predecessors, successors);
                return;
            } else {
                TreeNodeDouble left = new TreeNodeDouble(value);
                successors.push(node);
                findNode(node.left, value, predecessors, successors);
                node.left = left;
                added = true;
                return;
            }
        } else {
            if (node.right != null) {
                predecessors.push(node);
                findNode(node.left, value, predecessors, successors);
                return;
            } else {
                TreeNodeDouble right = new TreeNodeDouble(value);
                predecessors.push(node);
                node.right = right;
                added = true;
                return;
            }
        }
    }

    void findPredecessor(Stack<TreeNodeDouble> path) {
        TreeNodeDouble node = path.peek();
        if (node.left != null) {
            TreeNodeDouble goright = node.left;
            while (goright != null) {
                path.push(goright);
                goright = goright.right;
            }
            return;
        }


        TreeNodeDouble child = path.pop();
        while (!path.isEmpty()) {
            TreeNodeDouble parent = path.peek();
            if (parent.right == child) {
                break;
            } else {
                child = parent;
                path.pop();
            }
        }
    }

    void findSuccessor( Stack<TreeNodeDouble> path) {
        TreeNodeDouble node = path.peek();

        if (node.right != null) {
            TreeNodeDouble goleft = node.right;
            while (goleft != null) {
                path.push(goleft);
                goleft = goleft.left;
            }
            return;
        }

        TreeNodeDouble child = path.pop();
        while (!path.isEmpty()) {
            TreeNodeDouble parent = path.peek();
            if (parent.left == child) {
                break;
            } else {
                child = parent;
                path.pop();
            }
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
            KClosestUsingStack traverser = new KClosestUsingStack();
            assertEquals(traverser.closestKValues(constructTreeNodeDouble(), 10.3, 3).stream().map(i -> String.valueOf(Double.valueOf(i.value).intValue()))
                    .collect(Collectors.joining(",")), "10,20,25");
        }

        @Test
        public void testRight() {
            KClosestUsingStack traverser = new KClosestUsingStack();

            assertEquals(traverser.closestKValues(constructTreeNodeDouble(), 60.3, 3).stream().map(i -> String.valueOf(Double.valueOf(i.value).intValue()))
                    .collect(Collectors.joining(",")), "40,50,60");

        }

        @Test
        public void testCenter() {
            KClosestUsingStack traverser = new KClosestUsingStack();

            assertEquals(traverser.closestKValues(constructTreeNodeDouble(), 32.3, 3).stream().map(i -> String.valueOf(Double.valueOf(i.value).intValue()))
                    .collect(Collectors.joining(",")), "25,30,40");
        }

        @Test
        public void testOneMore() {
            KClosestUsingStack traverser = new KClosestUsingStack();

            assertEquals(traverser.closestKValues(constructTreeNodeDouble(), 59.4, 3).stream().map(i -> String.valueOf(Double.valueOf(i.value).intValue()))
                    .collect(Collectors.joining(",")), "40,50,60");
        }
    }
}
