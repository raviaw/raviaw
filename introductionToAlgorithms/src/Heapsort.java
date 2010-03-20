import java.util.Arrays;

public class Heapsort {

    public static void main(String[] args) {
        // Heap h = new Heap();
        // int[] array = new int[] { 1, 2, 3, 4, 7, 8, 9, 10, 14, 16 };
        // int[] array = new int[] { 16, 14, 10, 8, 7, 9, 3, 2, 4, 1 };
        int[] array = ArrayUtils.randomArray(30, 0, 50);
        // h.buildMaxHeap(array);
        ArrayUtils.printArrayElements("before: ", array);
        Heap.heapsort(array);
        ArrayUtils.printArrayElements("after: ", array);
    }

    /**
     * The top element in the heap (1) is the largest element
     */
    private static class Heap {
        public static void heapsort(int[] A) {
            Heap h = new Heap();
            System.out.println("building heap...");
            h.buildMaxHeap(A);
            // System.out.println("heapsort...");
            // h.heapsort();
        }

        private int heapSize;
        private int[] A;

        private void heapsort() {
            for (int i = A.length - 1; i > 0; i--) {
                exchange(0, i);
                heapSize--;
                System.out.println("invoking maxHeapify on heapsort (i = " + i + ")");
                maxHeapify(0);
            }
        }

        private int left(int i) {
            if (i == 0)
                return 1;
            else
                return i * 2;
        }

        private int right(int i) {
            if (i == 0)
                return 2;
            else
                return i * 2 + 1;
        }

        private int parent(int i) {
            return i / 2;
        }

        private void exchange(int i, int j) {
            System.out.println("exchange(i = " + i + ", j = " + j + ", A[i] = " + A[i] + ", A[j] = " + A[j] + ")");
            int a = A[i];
            A[i] = A[j];
            A[j] = a;
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
                exchange(i, largest);
                maxHeapify(largest);
            }
        }

        public void buildMaxHeap(int[] data) {
            A = data;
            heapSize = A.length;
            // for (int i = A.length; i >= 0; i--) {
            // for (int i = A.length / 2; i >= 0; i--) {
            for (int i = A.length / 2; i >= 0; i--) {
                maxHeapify(i);
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
}
