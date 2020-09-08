public class NBody {
    public static double readRadius(String name) {
        In in = new In(name);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }

    public static Body[] readBodies(String name) {
        In in = new In(name);
        int N = in.readInt();
        double R = in.readDouble();

        Body[] bodies = new Body[N];
        for (int i = 0; i < N; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return bodies;
    }

    public static void main(String[] args) {
        return;
    }

}
