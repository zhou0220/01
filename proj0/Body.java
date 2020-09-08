public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String name){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = name;
    }

    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Body b) {
        double r = calcDistance(b);
        return G * mass * b.mass / (r * r);
    }

    public double calcForceExertedByX(Body b) {
        double F = calcForceExertedBy(b);
        double dx = b.xxPos - this.xxPos;
        double r = calcDistance(b);
        return F * dx / r;
    }

    public double calcForceExertedByY(Body b) {
        double F = calcForceExertedBy(b);
        double dy = b.yyPos - this.yyPos;
        double r = calcDistance(b);
        return F * dy / r;
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double xNetForce = 0;
        for (Body body : bodies) {
            if (body.equals(this)) {
                continue;
            }
            xNetForce += calcForceExertedByX(body);
        }
        return xNetForce;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double yNetForce = 0;
        for (Body body : bodies) {
            if (body.equals(this)) {
                continue;
            }
            yNetForce += calcForceExertedByY(body);
        }
        return yNetForce;
    }

    public void update(double dt, double x_force, double y_force){
        double ax = x_force / mass;
        double ay = y_force / mass;
        xxVel = xxVel + ax * dt;
        yyVel = yyVel + ay * dt;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
