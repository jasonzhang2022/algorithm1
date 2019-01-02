package practice.binarytree;

public class TreeNodeDouble {

    double value;
    TreeNodeDouble left;
    TreeNodeDouble right;

    /**
     * What if the parent doesn't exist?
     * We can track the parent through processing context using stack.
     *
     * We can also construct a global Map: node->parent so we can look up parent.
     */
    TreeNode parent;


    public TreeNodeDouble(double v){
        this.value = v;
    }
}
