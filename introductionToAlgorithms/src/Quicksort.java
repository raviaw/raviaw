public class Quicksort {
    public static void main(String[] args) {
        int[] array = ArrayUtils.shuffledArray(40, 1);
        ArrayUtils.printArrayElements("before: ", array);
        quicksort(array);
        ArrayUtils.printArrayElements("after: ", array);
    }

    public static void quicksort(int[] A) {
        quicksort(A, 0, A.length - 1);
    }

    private static void quicksort(int[] A, int left, int right) {
        int pivotIndex = (left + right) / 2;
        if (right > left) {
            pivotIndex = partition(A, left, right, pivotIndex);
            quicksort(A, left, pivotIndex - 1);
            quicksort(A, pivotIndex + 1, right);
        }
    }

    public static int partition(int[] A, int left, int right, int pivot) {
        System.out.println("partition(left: " + left + ", right: " + right + ", pivot: " + pivot + ", A[pivot]: " + A[pivot] + ")");
        int pivotValue = A[pivot];
        ArrayUtils.exchange(A, right, pivot);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (A[i] < pivotValue) {
                ArrayUtils.exchange(A, i, storeIndex);
                storeIndex++;
            }
        }
        ArrayUtils.exchange(A, right, storeIndex);
        return storeIndex;
    }
}
