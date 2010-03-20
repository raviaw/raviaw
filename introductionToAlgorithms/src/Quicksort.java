
public class Quicksort {
    public int partition(int[] A, int left, int right, int pivot) {
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
