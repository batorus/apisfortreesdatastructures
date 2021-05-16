package com.batorus.apisfortreesdatastructures.model;



import java.util.Collection;

public interface Tree<E> extends Collection<E> {
    /**
     * Return true if the element is in the tree
     */
    public boolean search(E e);

    /**
     * Insert element e into the binary tree
     * Return true if the element is inserted successfully
     */
    public boolean insert(E e);

    /**
     * Delete the specified element from the tree
     * Return true if the element is deleted successfully
     */
    public boolean delete(E e);

    /**
     * Get the number of elements in the tree
     */
    public int getSize();

    /**
     * Inorder traversal from the root
     */
    public default void inorder() {
    }

    /**
     * Postorder traversal from the root
     */
    public default void postorder() {
    }

    /**
     * Preorder traversal from the root
     */
    public default void preorder() {
    }

    @Override
    /** Return true if the tree is empty */
    public default boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public default boolean contains(Object e) {
        return search((E) e);
    }

    @Override
    public default boolean add(E e) {
            return insert(e);

    }

    @Override
    public default boolean remove(Object e) {
        return delete((E) e);
    }

    @Override
    public default int size() {
        return getSize();
    }

    @Override
    public default boolean containsAll(Collection<?> c) {

        if (c == null) return false;

        if (c.isEmpty()) return false;

        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {

        if (c == null) return false;

        Object[] a = c.toArray();
        int numNew = a.length;

        if (numNew == 0)
            return false;

        boolean flag = true;

        for (Object o : a) {
            E e = (E) o;
            flag &= add(e);
        }
        return flag;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
        if (c == null) return false;

        Object[] a = c.toArray();
        int numNew = a.length;

        if (numNew == 0)
            return false;

        boolean flag = true;

        for (Object o : a) {
            flag &= remove(o);
        }
        return flag;
    }

    @Override
    public default boolean retainAll(Collection<?> c) {
        // to be implemented
        return false;
    }

    @Override
    public default Object[] toArray() {
        // // to be implemented
        return null;
    }

    //test this
    @Override
    public default <T> T[] toArray(T[] a) {
        if (a.length < this.size())
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), this.size());
        int i = 0;
        Object[] result = a;

        java.util.Iterator<E> itr = iterator();

        while(itr.hasNext()){
            result[i++] = itr.next();
        }

        if (a.length > this.size())
            a[this.size()] = null;

        return a;
    }
}