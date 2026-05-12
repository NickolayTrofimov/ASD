import java.util.ArrayList;

public class HeapSort<E extends Comparable<E>> {

    public long iterations = 0;

    public void sort(E[] array) {
        iterations = 0;
        int n = array.length;
        if (n <= 1) return;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
    }

    private void heapify(E[] array, int heapSize, int index) {
        int largest = index;
        int left  = 2 * index + 1;
        int right = 2 * index + 2;

        iterations++;
        if (left < heapSize && array[left].compareTo(array[largest]) > 0) {
            largest = left;
        }

        iterations++;
        if (right < heapSize && array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }

        if (largest != index) {
            swap(array, index, largest);
            heapify(array, heapSize, largest);
        }
    }

    private void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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