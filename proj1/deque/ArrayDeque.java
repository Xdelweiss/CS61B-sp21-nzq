package deque;

import static java.lang.Math.min;

// Circular ArrayDeque! Hooray!
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        nextFirst = 0;
        nextLast = 0;
        size = 0;
        items = (T[]) new Object[10];
    }

    /** Add 1 for circular array to avoid out of bound index. */
    private int add1Circular(int n) {
        return (n + 1) % items.length;
    }

    /** Minus 1 for circular array to avoid out of bound index. */
    private int minus1Circular(int n) {
        if (n == 0) {
            return items.length - 1;
        } else {
            return n - 1;
        }
    }
    private void memorySave() {
        double R = (double) size / items.length;
        if (R < 0.25 && items.length > 10) {
            resize(items.length / 2);
        }
    }
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int itemStart = add1Circular(nextFirst);
        int itemEnd = minus1Circular(nextLast);
        /*
         [e, f, g, a, b, c, d]  (size = 7, length = 7)
                ^nextFirst 2
                   ^itemStart 3
         [a, b, c, d, e, f, g, N, ..., N, N]  (size = 7, length = 7*2 = 14)
                               ^nextLast 7
                                          ^nextFist 13
        */
        if (itemEnd > itemStart) {
            System.arraycopy(items, itemStart, a, 0, itemEnd - itemStart + 1);
        } else {
            System.arraycopy(items, itemStart, a, 0, size - itemStart);
            System.arraycopy(items, 0, a, size - itemStart, itemStart);
        }
        nextFirst = a.length - 1;
        nextLast = size;
        items = a;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = minus1Circular(nextFirst);
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = add1Circular(nextLast);
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int p = add1Circular(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[p] + " ");
            p = add1Circular(p);
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = add1Circular(nextFirst);
        T temp = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        memorySave();
        return temp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = minus1Circular(nextLast);
        T temp = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        memorySave();
        return temp;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int itemStart = add1Circular(nextFirst);
        return items[(itemStart + index) % items.length];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 15; i++) {
            ad.addFirst(i);
        }
        for (int i = 20; i < 50; i++) {
            ad.addLast(i);
        }

        ad.printDeque();
        System.out.println();
        System.out.println(ad.get(0));
        System.out.println(ad.get(30));

        while (!ad.isEmpty()) {
            ad.removeLast();
            ad.printDeque();
        }

        for (int i = 0; i < 15; i++) {
            ad.addFirst(i);
        }
        for (int i = 20; i < 50; i++) {
            ad.addLast(i);
        }

        ad.printDeque();
        System.out.println();
        System.out.println(ad.get(0));
        System.out.println(ad.get(30));

        while (!ad.isEmpty()) {
            ad.removeFirst();
            ad.printDeque();
        }
    }
}
