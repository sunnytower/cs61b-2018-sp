public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int length;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque() {
        items = (T []) new Object[8];
        /** for convenience, the actual length == length -1, nextfirst == nextlast. */
        length = 8;
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }
    public void addFirst(T item) {
        if (size == length - 1) {
            grow();
            items[nextFirst] = item;
            nextFirst = minusOne(nextFirst);
        }
        else {
            items[nextLast] = item;
            nextFirst = minusOne(nextFirst);
        }
    }
    public void addLast(T item) {
        if (size == length - 1) {
            grow();
            items[nextLast] = item;
            nextLast = plusOne(nextLast, length);
        }
        else {
            items[nextLast] = item;
            nextLast = plusOne(nextLast, length);
        }
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        int front = plusOne(nextFirst, length);
        while (front != nextLast) {
            System.out.print(items[front] + " ");
            front = plusOne(front, length);
        }
        System.out.println();
    }
    public T removeFirst() {
        nextFirst = plusOne(nextFirst, length);
        T ret = items[nextFirst];
        size--;
        if(timeToResize()){
            shrink();
        }
        return ret;
    }
    public T removeLast() {
        nextLast = minusOne(nextLast);
        T ret = items[nextLast];
        size--;
        if(timeToResize()){
            shrink();
        }
        return ret;
    }
    public T get(int index) {
        if (index >= length) {
            return null;
        }
        return items[index];
    }
    private boolean timeToResize() {
        return (length / size) >= 4;
    }

    private void grow() {
        int newLength = length * 2;
        T[] newItems = (T []) new Object[newLength];
        int originFront = plusOne(nextFirst, length);
        int newFront = length;
        while (originFront != nextLast) {
            newItems[newFront] = items[originFront];
            items[originFront] = null;
            originFront = plusOne(originFront, length);
            newFront = plusOne(newFront, newLength);
        }
        nextFirst = length - 1;
        nextLast = newFront;
        length = newLength;
        items = newItems;
    }
    private void shrink() {
        int newLength = length / 2;
        T[] newItems = (T []) new Object[newLength];
        int originFront = plusOne(nextFirst, length);
        int newFront = newLength / 2;
        while (originFront != nextLast) {
            newItems[newFront] = items[originFront];
            items[originFront] = null;
            originFront = plusOne(originFront, length);
            newFront = plusOne(newFront, newLength);
        }
        nextFirst = newLength / 2 - 1;
        nextLast = newFront;
        length = newLength;
        items = newItems;
    }
    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }
    private int plusOne(int index, int length) {
        return index + 1 % length;
    }
}
