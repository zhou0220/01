package bearmaps;

import java.util.List;

public class NaivePointSet  implements PointSet {

    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point p = new Point(x, y);
        Point n = points.get(0);
        double shortestDistance = Point.distance(p, n);
        for (int i = 1; i < points.size(); i++) {
            double distance = Point.distance(points.get(i), p);
            if (distance < shortestDistance) {
                shortestDistance = distance;
                n = points.get(i);
            }
        }
        return n;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX() + " and " + ret.getY()); // evaluates to 3.3 and 4.4;
    }
}
