
/**
 * A BST node class
 */
class Node {
    int value;
    Node left, right;

    /**
     * Makes a new Node object with the given value
     * 
     * @param value The value to give the Node object
     */
    public Node(int value) {
        this.value = value;
        left = null;
        right = null;
    }
}


/**
 * A BST data structure class
 */
class BinarySearchTree {

    Node root;
    
    
    
    /**
     * Inserts a node into the tree
     * 
     * @param value The value to insert into the tree
     */
    public void insert(int value) {
        //tree is empty
        if (root == null) {
            root = new Node(value);
            return;
        } else {
            Node current = root;
            Node parent = null;

            while (true) {
                parent = current;

                if (value < current.value) {
                    current = current.left;
                    if (current == null) {
                        parent.left = new Node(value);
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = new Node(value);
                        return;
                    }
                }

            } //closing while

        } //closing main if-else 
    }
    
    
    
    /**
     * pre-order traversal
     * Prints the value of every node pre-order
     * 
     * @param root The root node of the current subtree
     */
    public void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        
        System.out.print(root.value + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }



    /**
     * in-order traversal
     * Prints the value of every node in-order
     * 
     * @param root The root node of the current subtree
     */
    public void inOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        
        preOrderTraversal(root.left);
        System.out.print(root.value + " ");
        preOrderTraversal(root.right);
    }



    /**
     * post-order traversal
     * Prints the value of every node post-order
     * 
     * @param root The root node of the current subtree
     */
    public void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
        System.out.print(root.value + " ");
    }



    /**
     * Finds the node in the tree with the value specified by key
     * 
     * @param root The root of the subtree to be searched
     * @param key The value to search for
     * @return true if the value is in the subtree, otherwise false
     */
    public boolean find(Node root, int key) {
        if (root == null) { // the value wasn't found
            return false;
        }
        
        return root.value == key ? true : find(root.value > key ? root.left : root.right, key);
    }



    /**
     * Finds the node in the tree with the smallest key
     * 
     * @param root The root of the subtree to be searched
     * @return The minimum value in the subtree
     */
    public int getMin(Node root) {
        if (root == null) {
            return Integer.MAX_VALUE; // empty tree indication value
        }
        
        while (root.left != null) { // finding the node with the smallest value
            root = root.left;
        }
        
        return root.value;
    }



    /**
     * Finds the node in the tree with the largest key
     * 
     * @param root The root of the subtree to be searched
     * @return The maximum value in the subtree
     */
    public int getMax(Node root) {
        if (root == null) {
            return Integer.MIN_VALUE; // empty tree indication value
        }
        
        while (root.right != null) { // finding the node with the largest value
            root = root.right;
        }
        
        return root.value;
    }
    
    
    
    /**
     * Deletes the node with the value specified by the key argument if it exists in the tree
     * 
     * @param root The root of the subtree to be deleted from
     * @param key The value to be deleted
     * @return The root of the subtree after deletion
     */
    public Node delete(Node root, int key) {

        if (root == null) {
            return root;
        } else if (key < root.value) {
            root.left = delete(root.left, key);
        } else if (key > root.value) {
            root.right = delete(root.right, key);
        } else {
            //node has been found
            if (root.left == null && root.right == null) {
                //case #1: leaf node
                root = null;
            } else if (root.right == null) {
                //case #2 : only left child
                root = root.left;
            } else if (root.left == null) {
                //case #2 : only right child
                root = root.right;
            } else {
                //case #3 : 2 children
                root.value = getMax(root.left);
                root.left = delete(root.left, root.value);
            }
        }
        return root;
    }
}


/**
 * driver class
 */
public class TreeDemo {
    
    /**
     * The main method of the program
     * 
     * @param args Unused
     */
    public static void main(String[] args) {
        int[] valuesInTree = {24, 80, 18, 9, 90, 22};
        int[] valuesNotInTree = {91, 8, 50};
        
        BinarySearchTree t1 = new BinarySearchTree();
        
        for (int n : valuesInTree) {
            t1.insert(n);
        }
        
        System.out.println("\nsmallest value in the tree: " + t1.getMin(t1.root));
        System.out.println("largest value in the tree: " + t1.getMax(t1.root) + "\n");
        
        System.out.print("in-order :   ");
        t1.inOrderTraversal(t1.root);
        System.out.println();
        
        System.out.print("pre-order :   ");
        t1.preOrderTraversal(t1.root);
        System.out.println();
        
        System.out.print("post-order :   ");
        t1.postOrderTraversal(t1.root);
        System.out.println("\n");
        
        for (int n : valuesInTree) {
            System.out.println(n + " is in the tree: " + t1.find(t1.root, n));
        }
        for (int n : valuesNotInTree) {
            System.out.println(n + " is in the tree: " + t1.find(t1.root, n));
        }
    }
}
