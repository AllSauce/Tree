/*
 * This file contains 2 classes.
 * One is the T class, which is
 * the building block of the binary tree (nodes).
 * The other is the BinaryTree class itself.
 *
 * @author Krenar Manxhuka
 * @version 2021-02-18
 */

/*
 * This class ís the binary tree.
 * It builds a data structure that has a root (the starting node) then a left node (smaller) and
 * a right node (bigger). By using these it becomes tree like, with having its roots
 * from the head of the structure.
 */
public class Tree<T extends Comparable<T>> {
    private int size;
    private Node<T> root;


    /**
     * Creates a Node element.
     */
    private static class Node<T> {
        public T data;
        public Node<T> left;
        public Node<T> right;

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;

        }
    }

    /**
     * Creates an empty tree
     */
    public Tree(){
        size = 0;
        root = null;
    }

    /**
     * Test for presence of a value.
     *
     * @param elem the element to be processed
     * @return true if element is in list, false otherwise
     */
    public boolean search(T elem){
        Node<T> currentNode = root;

        while(currentNode!=null){
                if (elem.compareTo(currentNode.data) < 0) {
                    currentNode = currentNode.left;
                } else if (elem.compareTo(currentNode.data) > 0) {
                    currentNode = currentNode.right;
                } else if (elem.compareTo(currentNode.data) == 0) {
                    return true;
                }
            }
        return false;
    }

    /**
     * Add value to tree, duplicates not allowed.
     *
     * @param elem element to be processed
     * @return  return true if element is added, false otherwise
     */
    public boolean insert(T elem){
        Node<T> newNode = new Node<>(elem);
        Node<T> currentNode = root;
        if(currentNode==null){
            root = newNode;
            size++;
            return true;
        }
        while(true){
                if (elem.compareTo(currentNode.data) < 0) {
                    if(currentNode.left==null){
                        currentNode.left=newNode;
                        size++;
                        return true;
                    }
                    currentNode = currentNode.left;
                } else if (elem.compareTo(currentNode.data) > 0) {
                    if(currentNode.right == null){
                        currentNode.right = newNode;
                        size++;
                        return true;
                    }
                    currentNode = currentNode.right;

                } else if (elem.compareTo(currentNode.data) == 0) {
                    return false;
                }
        }
    }

    /**
     * Test for the number of elements in the tree.
     *
     * @return returns the numbers of elements in the tree
     */
    public int size(){
        return size;
    }

    /**
     * The height of the tree (root to furthest leaf).
     * @return returns the height of the tree
     */
    public int height(){
        if(root==null){
            return 0;
        }
        return height(root);
    }

    /**
     * Helper function
     * Recursively looks through the tree to get its height.
     *
     * @param node the node to start from
     * @return returns the height of the tree
     */
    private int height(Node<T> node){
        if(ifLeaf(node)){
            return 0;
        }
        int leftHeight = 0;
        if(node.left!=null){
            leftHeight = height(node.left);
        }
        int rightHeight = 0;
        if(node.right!=null){
            rightHeight = height(node.right);
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }


    /**
     * The number of leaves in the tree (end nodes, that have no child nodes).
     * @return returns the number of leaves in the tree
     */
    public int leaves(){
        return leaves(root);
    }

    /**
     * Helper function.
     * Returns the numbers of leaves recursively.
     *
     * @param node the node to be processed recursively
     * @return the number of leaves
     */
    private int leaves(Node<T> node){
        if(node==null){
            return 0;
        }else if(ifLeaf(node)){
            return 1;
        }
        return leaves(node.right) + leaves(node.left);
    }

    /**
     * A string describing the tree as an array (encapsuled by brackets).
     *
     * @return a list containing the elements in the tree.
     */
    public String toString(){
        StringBuilder elements = new StringBuilder("[");
        elements.append(toString(root));
        if(elements.length()>=2) {
            elements.delete(elements.length() - 2, elements.length());
        }
        elements.append("]");
        return elements.toString();
    }

    /**
     * Helper function.
     * Returns the elements in the tree
     *
     * @return returns a string with elements from the tree
     */
    private String toString(Node<T> node){
        StringBuilder elements = new StringBuilder();
        if(node==null){
            return "";
        }

        elements.append(toString(node.left));
        String s = String.valueOf(node.data);

        // Action
        elements.append(s);
        elements.append(", ");

        elements.append(toString(node.right));
        return elements.toString();
    }


    /**
     * Helper function.
     * Checks if a node is a leaf.
     *
     * @param node the node thats processed.
     * @return returns true if the node is a leaf and if not, false.
     */
    private boolean ifLeaf(Node<T> node){
        return node.left == null && node.right == null;
    }
}
