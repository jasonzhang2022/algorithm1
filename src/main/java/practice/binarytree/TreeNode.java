package practice.binarytree;

public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    /**
     * What if the parent doesn't exist?
     * We can track the parent through processing context using stack.
     *
     * We can also construct a global Map: node->parent so we can look up parent.
     */
    public TreeNode parent;

    public TreeNode(int v){
        this.value = v;
    }
}
