import java.util.Stack;

public class BSTIterator {
    class Node{
        int val;
        Node left;
        Node right;
    }
    Stack<Node> stack = new Stack<>();
    public BSTIterator(Node root){
        if(root!=null) return;
        Node node = root;
        while(node.left!=null){
            stack.push(node);
            node = node.left;
        }
    }
    boolean hasNext(){
        return stack.isEmpty();
    }
    int next(){
       Node node = stack.peek();
       int value = node.val;
       stack.pop();
       node = node.right;
       if(node!=null){
            stack.push(node);
            node = node.left;
       }
        return value;
    }
}
