
package githubpackage;


class Node {
    int value;
    Node left, right;

    public Node(int value) {
        this.value = value;
        left = null;
        right = null;
    }
}


class BinarySearchTree {

    Node root;


    /*
       recursive insert method
        
       */
    /*
    inserts a node into the tree
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
     * a method to find the node in the tree
     * with a specific value
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
     * a method to find the node in the tree
     * with a smallest key
     * @param root The root of the subtree to be searched
     * @return the minimum value in the subtree
     */
    public int getMin(Node root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        
        while (root.left != null) { // finding the node with the minimum value
            root = root.left;
        }
        
        return root.value;
    }



    /*
    a method to find the node in the tree
    with a largest key
    */
    public int getMax(Node root) {
        //implement in here
        return Integer.MIN_VALUE;
    }



    /*
    this method will not compile until getMax
    is implemented
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


public class TreeDemo {
    public static void main(String[] args) {
        BinarySearchTree t1 = new BinarySearchTree();
        t1.insert(24);
        t1.insert(80);
        t1.insert(18);
        t1.insert(9);
        t1.insert(90);
        t1.insert(22);

        System.out.print("in-order :   ");
        t1.inOrderTraversal(t1.root);
        System.out.println();
        
        System.out.print("pre-order :   ");
        t1.preOrderTraversal(t1.root);
        System.out.println();
        
        System.out.print("post-order :   ");
        t1.postOrderTraversal(t1.root);
        System.out.println();
    }
}
