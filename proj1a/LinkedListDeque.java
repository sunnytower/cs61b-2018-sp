public class LinkedListDeque<T> {
    /** inner class */
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
        /** only for sentinel */
        Node(Node p, Node n) {
            prev = p;
            next = n;
        }
    }
    /** sentinel and size
     *  sentinel == front, sentinel.prev == last. */
    private int size;
    private Node sentinel;

    /** method */
    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public void addFirst(T item) {
        Node toAdd = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = toAdd;
        sentinel.next = toAdd;
        size++;
    }
    public void addLast(T item) {
        Node toAdd = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = toAdd;
        sentinel.prev = toAdd;
        size++;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        Node curr = sentinel.next;
        while (curr != sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node toDelete = sentinel.next;
        T value = toDelete.item;
        sentinel.next = toDelete.next;
        toDelete.next.prev = sentinel;
        toDelete = null;
        size--;
        return value;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node toDelete = sentinel.prev;
        T value = toDelete.item;
        sentinel.prev = toDelete.prev;
        toDelete.prev.next = sentinel;
        toDelete = null;
        size--;
        return value;
    }
    public T get(int index) {
        if (size <= index) {
            return  null;
        }
        Node curr = sentinel.next;
        for (int i = 0; i < index; ++i) {
            curr = curr.next;
        }
        return  curr.item;
    }
    private T getRecursiveHelper(Node start, int index) {
        if (index == 0) {
            return start.item;
        }
        return getRecursiveHelper(start.next, index - 1);
    }
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }
}
