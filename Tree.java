import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Tree {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
      int getVal(){
          return val;
      }
  }

    public void comp(){
        List<TreeNode> tlist = new ArrayList<>();

        Collections.sort(tlist,Comparator.comparing(TreeNode::getVal));
    }
    public TreeNode deleteBSTNode(TreeNode root,int key){
        if(root == null) return null;
        if(key<root.val) root.left = deleteBSTNode(root.left,key);
        else if(key>root.val) root.right = deleteBSTNode(root.right,key);
        else{
            if(root.left == null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteBSTNode(root.right, root.val);
        }
        return root;
    }
    public TreeNode findMin(TreeNode node){
        while(node.left!=null){
            node = node.left;
        }
        return node;
    }
}
