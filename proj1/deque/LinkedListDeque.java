package deque;

public class LinkedListDeque<T> {
    private static class ItemNode<T> {
        private ItemNode prev;
        private T item;
        private ItemNode next;
        }

    private ItemNode sentinel = new ItemNode();
    private int size;

    public LinkedListDeque() {
        sentinel.prev = sentinel;
        sentinel.item = null;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T t) {
        this();
        this.addLast(t);
    }

    /** Add a new node N (carries item t) between A and B.
     * From: A <--> B   To: A <--> N <--> B */
    private void addHelper(ItemNode a, ItemNode b, ItemNode n, T t) {
        a.next = n;
        n.prev = a;
        b.prev = n;
        n.next = b;
        n.item = t;
    }

    /** Remove node B from A <--> B <--> C and return the T of B. */
    private T removeHelper(ItemNode a, ItemNode b, ItemNode c) {
        a.next = c;
        c.prev = a;
        return (T) b.item;
    }


    public void addFirst(T t) {
       addHelper(sentinel, sentinel.next, new ItemNode(), t);
        size += 1;
    }

    public void addLast(T t) {
       addHelper(sentinel.prev, sentinel, new ItemNode(), t);
        size += 1;
    }

    public T getLast() {
        return (T) sentinel.prev.item;
    }

    public T removeLast() {
        if (!isEmpty()) {
            size -= 1;
        }
        return removeHelper(sentinel.prev.prev, sentinel.prev, sentinel);
    }

    public T removeFirst() {
        if (!isEmpty()) {
            size -= 1;
        }
        return removeHelper(sentinel, sentinel.next, sentinel.next.next);
    }

    public void printDeque() {
        ItemNode p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
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

    public T get(int index) {
        if (isEmpty() || index >= size) {
            return null;
        }
        ItemNode p = sentinel.next;
        for (int i = 0; i < index ; i++) {
            p = p.next;
        }
        return (T) p.item;
    }

    private T getRecursiveHelper(int index, ItemNode p) {
        if (index == 0) {
            return (T) p.item;
        } else {
            return getRecursiveHelper(index - 1, p.next);
        }
    }
    public T getRecursive(int index) {
        if (isEmpty() || index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);

    }

}
