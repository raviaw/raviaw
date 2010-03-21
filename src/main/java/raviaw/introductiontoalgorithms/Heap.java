package raviaw.introductiontoalgorithms;

import java.util.Arrays;

import raviaw.introductiontoalgorithms.tree.TreeBuilder;
import raviaw.introductiontoalgorithms.tree.TreeRenderer;

/**
 * The top element in the heap (1) is the largest element
 */
public class Heap {
    private int heapSize;
    private int[] A;

    public Heap() {
    }

    public Heap(int[] elements) {
        buildMaxHeap(elements);
    }

    public void heapsort() {
        for (int i = A.length - 1; i > 0; i--) {
            ArrayUtils.exchange(A, 0, i);
            heapSize--;
            System.out.println("invoking maxHeapify on heapsort (i = " + i + ")");
            maxHeapify(0);
            TreeRenderer.renderTree(TreeBuilder.fromHeap(this));
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
        }
    }

    public int heapSize() {
        return heapSize;
    }

    public int[] elements() {
        return Arrays.copyOf(A, heapSize);
    }

    // This is where the error is
    public static int left(int i) {
        return i * 2 + 1;
    }

    public static int right(int i) {
        return i * 2 + 2;
    }

    /*
     * P1 + C1 + C2 + C3 + C2 + C4 P3 P2 P4 P1 P5 P3 P2 P4 P6
     */
    public void printShortHeap() {
        ArrayUtils.printArrayElements("heap: ", A);
    }

    public void maxHeapify(int i) {
        printShortHeap();
        int l = left(i);
        int r = right(i);
        int largest;

        if (l < heapSize && A[l] > A[i]) {
            largest = l;
        } else {
            largest = i;
        }

        if (r < heapSize && A[r] > A[largest]) {
            largest = r;
        }

        System.out.println("i: " + i + ", l: " + l + ", r: " + r + ", A[i]: " + A[i] + ", A[l]: "
                + (l < heapSize ? A[l] : -1) + ", A[r]: " + (r < heapSize ? A[r] : -1) + ", largest: " + largest
                + ", A[largest]: " + A[largest]);
        if (largest != i) {
            ArrayUtils.exchange(A, largest, i);
            maxHeapify(largest);
        }
    }

    public void buildMaxHeap(int[] data) {
        A = data;
        heapSize = A.length;
        for (int i = A.length / 2 - 1; i >= 0; i--) {
            maxHeapify(i);
            TreeRenderer.renderTree(TreeBuilder.fromHeap(this));
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
        }
        printLongHeap(0, 0, "");
    }

    public void printLongHeap(int i, int level, String seq) {
        if (heapSize <= i)
            return;
        for (int j = 0; j < level; j++) {
            System.out.print(" ");
        }
        String newSeq = seq + " > " + A[i];
        System.out.println(newSeq);
        printLongHeap(left(i), level + 1, newSeq);
        printLongHeap(right(i), level + 1, newSeq);
    }
}