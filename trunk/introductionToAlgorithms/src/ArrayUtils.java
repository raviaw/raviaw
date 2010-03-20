import java.util.Random;

public class ArrayUtils {
    final static Random r = new Random();

    public static void exchange(int[] A, int i, int j) {
        System.out.println("exchange(i = " + i + ", j = " + j + ", A[i] = " + (i < A.length ? A[i] : -1) + ", A[j] = "
                + (j < A.length ? A[j] : -1) + ")");
        int a = A[i];
        A[i] = A[j];
        A[j] = a;
    }

    public static int[] shuffledArray(final int elements, final int lowerBound) {
        int[] A = new int[elements];

        // Build
        for (int i = 0; i < elements; i++) {
            A[i] = i + lowerBound;
        }

        // Shuffle
        for (int i = elements - 1; i > 0; i--) {
            int n = r.nextInt(i);
            exchange(A, i, n);
        }

        return A;
    }

    public static int[] randomArray(int elements, int lowerBound, int higherBound) {
        int[] A = new int[elements];
        for (int i = 0; i < elements; i++)
            A[i] = r.nextInt(higherBound - lowerBound) + lowerBound;

        return A;
    }

    public static void printArrayElements(String prefix, int[] elements) {
        printArrayElements(prefix, elements, 0, elements.length);
    }

    public static void printArrayElements(String prefix, int[] elements, int start, int end) {
        System.out.print(prefix);
        for (int i = start; i < end; i++) {
            int val = elements[i];
            if (val < Integer.MAX_VALUE)
                System.out.print(elements[i] + " ");
            else
                System.out.print("oo ");
        }
        System.out.println();
    }
}
