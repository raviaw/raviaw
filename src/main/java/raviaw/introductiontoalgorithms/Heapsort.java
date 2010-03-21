package raviaw.introductiontoalgorithms;

import raviaw.introductiontoalgorithms.tree.TreeBuilder;
import raviaw.introductiontoalgorithms.tree.TreeRenderer;

public class Heapsort {

    public static void main(String[] args) {
        int[] array = ArrayUtils.shuffledArray(41, 1);
        ArrayUtils.printArrayElements("before: ", array);
        heapsort(array);
        ArrayUtils.printArrayElements("after: ", array);
    }

    public static void heapsort(int[] A) {
        Heap h = new Heap();
        System.out.println("building heap...");
        h.buildMaxHeap(A);
        System.out.println("heapsort...");
        h.heapsort();
    }
}
