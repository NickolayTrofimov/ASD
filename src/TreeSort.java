import java.util.ArrayList;
import java.util.Stack;

public class TreeSort<E extends Comparable<E>> {

    public long iterations = 0;

    private static class Node<E> {
        E value;
        Node<E> left;
        Node<E> right;

        Node(E value) {
            this.value = value;
        }
    }

    public void sort(E[] array) {
        iterations = 0;
        if (array.length <= 1) return;

        Node<E> root = new Node<>(array[0]);
        for (int i = 1; i < array.length; i++) {
            insert(root, array[i]);
        }

        ArrayList<E> sortedList = new ArrayList<>(array.length);
        Stack<Node<E>> stack = new Stack<>();
        Node<E> current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
                iterations++;
            }

            current = stack.pop();
            sortedList.add(current.value);
            iterations++;
            current = current.right;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = sortedList.get(i);
        }
    }

    private void insert(Node<E> root, E value) {
        Node<E> current = root;
        while (true) {
            iterations++;
            if (value.compareTo(current.value) < 0) {
                if (current.left == null) {
                    current.left = new Node<>(value);
                    return;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = new Node<>(value);
                    return;
                } else {
                    current = current.right;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void sort(ArrayList<E> list) {
        if (list == null || list.isEmpty()) return;

        E[] array = (E[]) list.toArray((E[]) java.lang.reflect.Array.newInstance(
                list.get(0).getClass(), list.size()));

        sort(array);

        for (int i = 0; i < array.length; i++) {
            list.set(i, array[i]);
        }
    }
}