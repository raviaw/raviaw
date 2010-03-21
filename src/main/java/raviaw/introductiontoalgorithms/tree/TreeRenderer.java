package raviaw.introductiontoalgorithms.tree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JFrame;

import raviaw.introductiontoalgorithms.Heap;

public class TreeRenderer<T> extends JFrame {
    private static final long serialVersionUID = -7521686076788731925L;
    TreeRendererNode<T> root;
    int levels;

    public static <T> TreeRenderer<T> renderTree(Node<T> root) {
        return new TreeRenderer<T>(root);
    }

    private TreeRenderer(final Node<T> root) {
        super("Tree renderer");
        update(root);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setVisible(true);
    }

    public void update(Node<T> node) {
        AtomicInteger levels = new AtomicInteger(0);
        this.root = createNode(node, 0, 0.5, 0, levels);
        this.levels = levels.get();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Now it is our time
        Dimension dimension = getSize();
        setFont(new Font("arial", 0, 15));
        drawNode(g, dimension, root, (dimension.height - 80) / levels);
    }

    private int nodeX(TreeRendererNode<T> n, Dimension d) {
        return (int) ((d.width) * n.x);
    }

    private int nodeY(TreeRendererNode<T> n, float ymultiplier) {
        return (int)(n.y * ymultiplier + 40);
    }

    public void drawNode(Graphics g, Dimension d, TreeRendererNode<T> node, float ymultiplier) {
        // 1 = 1, 3 = 2, 7 = 3, 15 = 4, 31 = 5
        line(g, node, node.left, d, ymultiplier);
        line(g, node, node.right, d, ymultiplier);
        g.drawString(node.node.value.toString(), nodeX(node, d) + 10, nodeY(node, ymultiplier));
        if (node.left != null)
            drawNode(g, d, node.left, ymultiplier);
        if (node.right != null)
            drawNode(g, d, node.right, ymultiplier);
    }

    public void line(Graphics g, TreeRendererNode<T> start, TreeRendererNode<T> end, Dimension d, float ymultiplier) {
        if (start == null || end == null)
            return;
        int startX = nodeX(start, d);
        int startY = nodeY(start, ymultiplier);
        int endX = nodeX(end, d);
        int endY = nodeY(end, ymultiplier);
        Color old = g.getColor();
        g.setColor(Color.BLUE);
        g.drawLine(startX, startY, endX, endY);
        g.setColor(old);
    }

    //
    // If xparent is 1.5
    // and xstep is 1 / 20;
    private static <T> TreeRendererNode<T> createNode(Node<T> node, double xpos, double xprogress, int y,
            AtomicInteger levels) {
        if (node == null)
            return null;

        if (levels.get() < y) {
            levels.set(y);
        }
        TreeRendererNode<T> treeNode = new TreeRendererNode<T>();
        treeNode.x = xpos + xprogress;
        treeNode.y = y;
        treeNode.node = node;
        treeNode.left = createNode(node.left, treeNode.x, Math.abs(xprogress / 2) * -1, y + 1, levels);
        treeNode.right = createNode(node.right, treeNode.x, Math.abs(xprogress / 2), y + 1, levels);

        return treeNode;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        renderTree(TreeBuilder.fromHeap(new Heap(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })));
    }

    private static class TreeRendererNode<T> {
        Node<T> node;
        TreeRendererNode<T> left;
        TreeRendererNode<T> right;
        // From 0 .. 1
        double x;
        // The vertical index
        int y;
    }
}
