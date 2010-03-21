
public class Heapsort {

    public static void main(String[] args) {
        int[] array = ArrayUtils.shuffledArray(41, 1);
        ArrayUtils.printArrayElements("before: ", array);
        Heap.heapsort(array);
        ArrayUtils.printArrayElements("after: ", array);
    }

    // This is where the error is
    public static int left(int i) {
        return i * 2 + 1;
    }

    public static int right(int i) {
        return i * 2 + 2;
    }

    /**
     * The top element in the heap (1) is the largest element
     */
    private static class Heap {
        public static void heapsort(int[] A) {
            Heap h = new Heap();
            System.out.println("building heap...");
            h.buildMaxHeap(A);
            TreeRenderer.renderTree(h.A);
            System.out.println("heapsort...");
            h.heapsort();
        }

        private int heapSize;
        private int[] A;

        private void heapsort() {
            for (int i = A.length - 1; i > 0; i--) {
                ArrayUtils.exchange(A, 0, i);
                heapSize--;
                System.out.println("invoking maxHeapify on heapsort (i = " + i + ")");
                maxHeapify(0);
            }
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
