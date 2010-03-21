package raviaw.introductiontoalgorithms.tree;

import raviaw.introductiontoalgorithms.Heap;

public class TreeBuilder {
    public static Node<Integer> fromHeap(Heap heap) {
        return createHeapNode(heap.elements(), 0);
    }
    private static Node<Integer> createHeapNode(int[] heap, int pos) {
        if (!(pos < heap.length))
            return null;

        final Node<Integer> n = new Node<Integer>();
        n.value = heap[pos];
        n.left = createHeapNode(heap, Heap.left(pos));
        n.right = createHeapNode(heap, Heap.right(pos));

        return n;
    }
}
