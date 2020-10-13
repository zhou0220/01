package bearmaps;

import java.util.List;
import java.util.Collections;

public class KDTree implements PointSet {
    private static final boolean HORIZONTAL = true;
    private Node root;

    public KDTree(List<Point> points) {
        Collections.shuffle(points);
        for (Point point : points) {
            root = insert(point, root, HORIZONTAL);
        }
    }

    //Insert process in KDTree
    private Node insert(Point point, Node node, boolean direction) {
        if (node == null) {
            return new Node(point, direction);
        }

        if (point.equals(node.getPoint())) {
            return node;
        }

        int cmp = comparePoints(point, node.getPoint(), direction);
        if (cmp < 0) {
            node.left = insert(point, node.getLeft(), !direction);
        } else {
            node.right = insert(point, node.getRight(), direction);
        }

        return node;
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        return nearest(root, target, root.getPoint());
    }

    private Point nearest(Node node, Point target, Point best) {
        if (node == null) {
            return best;
        }

        // Compare the current best point with current node's point.
        double bestDist = Point.distance(best, target);
        double currDist = Point.distance(node.getPoint(), target);
        if (Double.compare(bestDist, currDist) < 0) {
            best = node.getPoint();
        }

        Node goodSide;
        Node badSide;
        int cmp = comparePoints(target, node.getPoint(), node.getDirection());
        if (cmp < 0) {
            goodSide = node.getLeft();
            badSide = node.getRight();
        } else {
            goodSide = node.getRight();
            badSide = node.getLeft();
        }

        best = nearest(goodSide, target, best);
        if (isWorthLooking(node, target, best)) {
            best = nearest(badSide, target, best);
        }

        return best;
    }

    // if badSide exist some better result for looking
    private boolean isWorthLooking(Node node, Point target, Point best) {
        double distToBest = Point.distance(target, best);
        double distToBad;
        if (node.getDirection() == HORIZONTAL) {
            distToBad = Point.distance(new Point(node.point.getX(), target.getY()), target);
        } else {
            distToBad = Point.distance(new Point(target.getX(), node.point.getY()), target);
        }

        return Double.compare(distToBad, distToBest) < 0;
    }

    // Compare two points based on the direction  of current node.
    private int comparePoints(Point a, Point b, boolean direction) {
        if (direction == HORIZONTAL) {
            return Double.compare(a.getX(), b.getX());
        } else {
            return Double.compare(a.getY(), b.getY());
        }
    }

    private class Node {
        private Point point;
        private boolean direction;
        private Node left;
        private Node right;

        Node(Point point, boolean direction) {
            this.point = point;
            this.direction = direction;
            left = null;
            right = null;
        }

        public Point getPoint() {
            return point;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public boolean getDirection() {
            return direction;
        }
    }
}
