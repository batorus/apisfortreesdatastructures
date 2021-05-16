package com.batorus.apisfortreesdatastructures.model.BinarySearchTree;


import com.batorus.apisfortreesdatastructures.exception.ElementAlreadyInsertedException;
import com.batorus.apisfortreesdatastructures.model.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BST<E extends Comparable<E>> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;
    private List<E> list = new ArrayList<>();

    private Logger log = LoggerFactory.getLogger(BST.class);

    /**
     * Create a default binary tree
     */
    public BST() {
    }

    public BST(List<E> elements) {
        for (E element : elements) add(element);
    }

    @Override
    /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode<E> getRoot() {
        return root;
    }

//    public boolean insertElements(List<E> elements) {
//
//        for (E element : elements) {
//            if (!insert(element)) return false;
//        }
//
//        return true;
//    }

    public boolean insertElement(E element) {
        if (!insert(element)) return false;

        return true;
    }

    /**
     * Returns a path from the root leading to the specified element
     */
    public ArrayList<TreeNode<E>> path(E e) {

        ArrayList<TreeNode<E>> list = new ArrayList<>();

        TreeNode<E> current = root; // Start from the root

        //TODO: check if elem e is in the tree
        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else
                break;
        }

        return list; // Return an array list of nodes
    }

    /**
     * Returns true if the element is in the tree
     */
    @Override
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
                return true; // Element is found
        }

        return false;
    }

//    public List<TreeNode<E>> find(E e) {
//
//        return path(e);
//    }

    //todo: test it
    public boolean recursiveSearch(TreeNode<E> node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.element) < 0) {
            recursiveSearch(node.left, e);
        }

        if (e.compareTo(node.element) > 0) {
            recursiveSearch(node.right, e);
        }

        return e.compareTo(node.element) == 0;
    }

    /**
     * Insert element e into the binary tree
     * Return true if the element is inserted successfully
     */
    @Override
    public boolean insert(E e) {

        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    throw new ElementAlreadyInsertedException("Element: " + e + " exists in the tree!"); // Duplicate node not inserted
                }
            }

            // Create the new node and attach it to the parent node
            //current e null in mom asta iar parent pointeaza catre leaf node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return true; // Element inserted successfully

    }

    //todo test it
    public boolean recursiveInsert(TreeNode<E> node, E e) {

        if (root == null) {
            root = createNewNode(e); // Create a new root
        }

        if (node == null) return false;

        if (e.compareTo(node.element) < 0) {
            if (node.left == null) {
                node.left = createNewNode(e);
            }

            return recursiveInsert(node.left, e);
        }

        if (e.compareTo(node.element) > 0) {
            if (node.right == null) {
                node.right = createNewNode(e);
            }

            return recursiveInsert(node.right, e);
        }

        if (e.compareTo(node.element) == 0) return false;

        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }


    /**
     * This inner class is static, because it does not access
     * any instance members defined in its outer class
     */
    public static class TreeNode<E> {
        public E element;
        public TreeNode<E> left;
        public TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public TreeNode<E> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<E> left) {
            this.left = left;
        }

        public TreeNode<E> getRight() {
            return right;
        }

        public void setRight(TreeNode<E> right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "elem=" + element +
                    '}';
        }
    }


    /**
     * Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree
     */
    @Override
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                break; // Element is in the tree pointed at by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {// nodul nu are left child si este chiar radacina; poate sa aiba sau nu right child
                root = current.right;
            } else {//este in dreapta sau in stanga?
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        } else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of(inorder successor)
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {//se poate merge in dreapta?
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else {
                // Special case: parentOfRightMost == current
                //sau: parentOfRightMost.left == rightMost
                //pentru ca rightMost.right is null and the pointers did not move
                parentOfRightMost.left = rightMost.left;
            }
        }

        size--;
        return true; // Element deleted successfully
    }

    @Override
    /** Obtain an iterator. Use inorder. */
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private ArrayList<E> list = new ArrayList<>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);//poti sa faci mai multe operatii la nivelul nodului aici
            inorder(root.right);
        }

        @Override
        /** More elements for traversing? */
        public boolean hasNext() {
            if (current < list.size())
                return true;

            return false;
        }

        @Override
        /** Get the current element and move to the next */
        public E next() {
            return list.get(current++);
        }

        @Override // Remove the element returned by the last next()
        public void remove() {
            if (current == 0) // next() has not been called yet
                throw new IllegalStateException();

            delete(list.get(--current));
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    @Override
    /** Remove all elements from the tree */
    public void clear() {
        root = null;
        size = 0;
    }


    //########### Traversals

    /**
     * Inorder traversal from the root
     */
    @Override
    public void inorder() {
        inorderCustom(root, 0);
    }

    /**
     * Inorder traversal from a subtree
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    protected void inorderCustom(TreeNode<E> root, int level) {
        if (root == null) return;
        inorderCustom(root.left, level + 1);
        System.out.print("Element: " + root.element + " Level:" + level + "\n");
        inorderCustom(root.right, level + 1);
    }

    @Override
    /** Postorder traversal from the root */
    public void postorder() {
        postorderCustom(root, 0);
    }

    /**
     * Postorder traversal from a subtree
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Postorder traversal from a subtree
     */
    protected void postorderCustom(TreeNode<E> root, int level) {
        if (root == null) return;
        postorderCustom(root.left, level + 1);
        postorderCustom(root.right, level + 1);
        System.out.print("Elem: " + root.element + " Level: " + level + "\n");
    }

    @Override
    /** Preorder traversal from the root */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }


    /**
     * Display the nodes in a breadth-first traversal
     */
    public void breadthFirstTraversal() {
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {

            /* poll() removes the present head.
            For more information on poll() visit
            http://www.tutorialspoint.com/java/util/linkedlist_poll.htm */

            TreeNode<E> tempNode = queue.poll();
            System.out.println(tempNode.element + " ");

            /*Enqueue left child */
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            /*Enqueue right child */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }

            System.out.println("queue start");
            queue.forEach(e -> System.out.println(e.element));
            System.out.println("queue end");
        }
    }

    protected void inorderSubtree(TreeNode<E> node) {
        if (node == null) return;
        inorderSubtree(node.left);
        this.list.add(node.element);
        inorderSubtree(node.right);
    }


    public List<E> getInorderTreeList() {
        inorderSubtree(getRoot());
        return this.list;
    }

}

