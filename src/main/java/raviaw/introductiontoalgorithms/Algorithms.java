package raviaw.introductiontoalgorithms;
public class Algorithms {
    private static int recursiveBreak = 0;

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] elements = ArrayUtils.randomArray(30, 0, 60);

        mergeSort(elements);
        int number = 5;
        int pos = binarySearch(elements, number, 0, elements.length - 1);
        System.out.println("number: " + number + ", pos: " + pos + ", proof: " + (pos != -1 ? elements[pos] : -1));
    }

    public static int binarySearch(int[] A, int number, int p, int r) {
        if (recursiveBreak++ > 100) {
            System.out.println("HALT!");
            return -1;
        }
        int mid = (p + r) / 2;
        ArrayUtils.printArrayElements("binarySearch, number: " + number + ", p: " + p + ", r: " + r + ", mid: " + mid
                + ", A[mid]: " + A[mid] + ", A: ", A);
        if (A[mid] == number) {
            return mid; // Found!
        } else if (r - p <= 1) {
            System.out.println("down to two elements");
            if (A[r] == number)
                return r;
            else if (A[p] == number)
                return p;
            else
                return -1;
        } else if (A[mid] < number) { // number is to the right side
            return binarySearch(A, number, mid, r);
        } else {
            return binarySearch(A, number, p, mid);
        }
    }

    public static void mergeSort(int[] A) {
        mergeSort(A, 0, A.length - 1);
    }

    public static void mergeSort(int[] A, int p, int r) {
        ArrayUtils.printArrayElements("mergeSort, A: ", A);
        System.out.println("mergeSort, p: " + p + ", r: " + r);
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    public static void merge(int[] A, int p, int q, int r) {
        ArrayUtils.printArrayElements("before: ", A);

        int n1 = q - p + 1;
        int n2 = r - q;
        int L[] = new int[n1 + 1];
        int R[] = new int[n2 + 1];
        for (int i = 0; i < n1; i++)
            L[i] = A[p + i];
        for (int i = 0; i < n2; i++)
            R[i] = A[q + i + 1];
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        ArrayUtils.printArrayElements("L: ", L);
        ArrayUtils.printArrayElements("R: ", R);
        int i = 0;
        int j = 0;
        for (int k = p; k <= r; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
        }
        ArrayUtils.printArrayElements("merged: ", A);
    }

    public static void insertionSort(int[] A) {
        ArrayUtils.printArrayElements("start: ", A);
        for (int j = 1; j < A.length; j++) {
            int key = A[j];
            int i = j - 1;
            while (i >= 0 && A[i] > key) {
                A[i + 1] = A[i];
                i = i - 1;
                ArrayUtils.printArrayElements("i: " + i, A);
            }
            A[i + 1] = key;
            ArrayUtils.printArrayElements("key: " + key, A);
        }
    }

}
