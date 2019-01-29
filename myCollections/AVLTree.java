package myCollections;

import java.util.Comparator;

public class AVLTree<T extends Comparable<T>> {
    /*
    AVL树：
    左右子树高度绝对值最多差1的二叉搜索树
    子树也是AVL树
     */
    private Node<T> root;
    class Node<T extends Comparable<T>>{
        T key;
        int height;
        Node<T> left;
        Node<T> right;

        public Node(T key, int height, Node<T> left, Node<T> right) {
            this.key = key;
            this.height = height;
            this.left = left;
            this.right = right;
        }
        public Node(T key){
            this.key = key;
            this.height = 0;
        }
    }
    public int getHeight(Node<T> node){
        return node == null ? 0 : node.height;
    }
    public Node<T> LL(Node<T> node){
        //左子树插入节点在左导致不平衡，需要旋转，以下不再赘述
        Node<T> leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        node.height = Math.max(node.left.height,node.right.height) + 1;
        leftNode.height = Math.max(leftNode.left.height, leftNode.right.height) + 1;
        return leftNode;
    }
    public Node<T> RR(Node<T> node) {
        Node<T> rightNode;
        rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;

        node.height = Math.max( node.left.height, node.right.height) + 1;
        rightNode.height = Math.max( rightNode.left.height, rightNode.right.height) + 1;
        return rightNode;
    }
    public Node<T> LR(Node<T> node){
        //LR先对左子树RR再对本树LL
        node.left = RR(node.left);
        return LL(node);
    }
    public Node<T> RL(Node<T> node){
        node.right = LL(node.right);
        return RR(node);
    }
    public Node<T> insert(Node<T> root,T key){
        /*
        插入：先判断插入点，再判断是否需要翻转
         */
        if(root == null){
            return new Node<T>(key);
        }
        else{
            if(key.compareTo(root.key)<0)
            //T是实现了comparable接口的，所以这里可以比较，但不能用大小于号
            {
                root.left = insert(root.left,key);
                if(root.left.height - root.right.height == 2){
                    if (key.compareTo(root.left.key) < 0)
                        root = LL(root);
                    else
                        root = LR(root);
                }
            }else if(key.compareTo(root.key)>0){
                root.right = insert(root.right,key);
                if(root.left.height - root.right.height == -2){
                    if (key.compareTo(root.right.key) < 0)
                        root = RL(root);
                    else
                        root = RR(root);
                }
            }else{
                return root;//相同的节点不添加
            }
        }
        return root;
    }
    public Node<T> delete(Node<T> root,T key){
        /*
        插入：先判断插入点，再判断是否需要翻转
         */
        if(root == null || key == null){
            return root;
        }
        else{
            if(key.compareTo(root.key)<0)
            //T是实现了comparable接口的，所以这里可以比较，但不能用大小于号
            {
                root.left = delete(root.left,key);
                if(root.left.height - root.right.height == -2){
                    Node<T> rightNode = root.right;
                    if (rightNode.right.height>root.left.height)
                        root = RL(root);
                    else
                        root = RR(root);
                }
            }else if(key.compareTo(root.key)>0){
                root.right = delete(root.right,key);
                if(root.left.height - root.right.height == 2){
                    Node<T> leftNode = root.left;
                    if (leftNode.left.height>leftNode.right.height)
                        root = LL(root);
                    else
                        root = LR(root);
                }
            }else {
                //找到了要删除的节点
                if (root.left == null) root = root.right;
                else if (root.right == null) root = root.left;
                else {
                    if (root.left.height < root.right.height) {
                        Node<T> tempNode = root.right;
                        while (tempNode.left != null) {
                            tempNode = tempNode.left;
                        }//为保证二叉树的平衡性、搜索性
                        //这里选择了高度较高的子树，选取中序遍历与root相邻的节点作为root
                        root.key = tempNode.key;
                        delete(tempNode, key);
                    } else {
                        Node<T> tempNode = root.left;
                        while (tempNode.right != null) {
                            tempNode = tempNode.right;
                        }//为保证二叉树的平衡性、搜索性
                        //这里选择了高度较高的子树，选取中序遍历与root相邻的节点作为root
                        root.key = tempNode.key;
                        delete(tempNode, key);
                    }
                }
            }
        }
        return root;
    }
}
