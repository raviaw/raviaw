import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;

import javax.swing.JFrame;

public class TreeRenderer extends JFrame {

    final int[] elements;

    public TreeRenderer(int[] elements) {
        super("Tree renderer");
        this.elements = elements;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setVisible(true);
    }

    public static final int MIN_PROPORTION = 0;
    public static final int MAX_PROPORTION = 30;

    private void drawCircled2Digit(Graphics g, final String drawingValue, final int xmid, final int ymid,
            final int proportion) {

        int xcircle = xmid - ((20 + (proportion * 2)) / 2);
        int ycircle = ymid - ((20 + (proportion * 2)) / 2);
        g.setColor(Color.WHITE);
        g.fillArc(xcircle, ycircle, 20 + (proportion * 2), 20 + (proportion * 2), 0, 360);

        g.setColor(Color.BLACK);

        // I am pretty sure there is a better way to match the font sizes
        g.setFont(new Font("courier new", 0, 14 + (int) (proportion * 1.7)));
        g.drawString(drawingValue, xmid - (8 + (int) (proportion * 1.1)), ymid + (4 + (int) (proportion * 0.6)));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Now it is our time
        Dimension d = getSize();
        int height = (int) (Math.log(elements.length + 1) / Math.log(2));
        System.out.println("Elements: " + elements.length + ", height: " + height);
        int ymultiplier = d.height / (height + 2);
        Node root = createNode(0, 0, 0.5, 0);
        drawNode(g, d, root, ymultiplier);
    }
    
    private int nodeX(Node n, Dimension d) {
        return (int) ((d.width) * n.xpos);
    }

    private int nodeY(Node n, int ymultiplier) {
        return n.y * ymultiplier;
    }

    public void drawNode(Graphics g, Dimension d, Node node, int ymultiplier) {
        // 1 = 1, 3 = 2, 7 = 3, 15 = 4, 31 = 5
        line(g, node, node.left, d,ymultiplier);
        line(g, node, node.right, d, ymultiplier);
        drawCircled2Digit(g, String.valueOf(node.value), nodeX(node, d), nodeY(node,ymultiplier), 5);
        if (node.left != null)
            drawNode(g, d, node.left, ymultiplier);
        if (node.right != null)
            drawNode(g, d, node.right, ymultiplier);
    }
    
    public void line(Graphics g, Node start, Node end, Dimension d, int ymultiplier) {
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
    Node createNode(int pos, double xparent, double xprogress, int yparent) {
        if (!(pos < elements.length))
            return null;

        Node n = new Node();
        n.arraypos = pos;
        n.y = yparent + 1;
        n.xpos = xparent + xprogress;
        System.out.println("xpos: " + n.xpos);
        n.value = elements[pos];
        n.left = createNode(Heapsort.left(pos), n.xpos, Math.abs(xprogress / 2) * -1, n.y);
        n.right = createNode(Heapsort.right(pos), n.xpos, Math.abs(xprogress / 2), n.y);

        return n;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        renderTree(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
    }

    public static void renderTree(int[] elements) {
        TreeRenderer tree = new TreeRenderer(Arrays.copyOf(elements, elements.length));
    }

    private static class Node {
        double xpos; // 0 ... 1 ... 2
        int y;
        int value;
        int arraypos;
        Node left;
        Node right;
    }
}
