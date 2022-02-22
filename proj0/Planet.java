public class Planet{
    public double xxPos; /** its current x position. */
    public double yyPos; /** its current y position. */
    public double xxVel; /** its current velocity in the x direction. */
    public double yyVel; /** its current velocity in the y direction. */
    public double mass; /** its mass. */
    public String imgFileName; /** name of the file that corresponds to the image that depicts the planet. */

    /** constructor to initialize an instance of the planet class. */
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** constructor to take in a planet and initialize an identical planet object. */
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /** calculate the r between two planets. */
    public double calcDistance(Planet other) {
        double xxDiff = other.xxPos - this.xxPos;
        double yyDiff = other.yyPos - this.yyPos;
        double r = java.lang.Math.sqrt((xxDiff * xxDiff) + (yyDiff * yyDiff));
        return r;
    }

    /** calculate the force extered on the planet other by the given planet. */
    public double calcForceExertedBy(Planet other) {
        double gravity = 6.67e-11;
        double distance = this.calcDistance(other);
        double force = (gravity * this.mass * other.mass) / (distance * distance);
        return force;
    }

    public double calcForceExertedByX(Planet other) {
        double dx = other.xxPos - this.xxPos;
        double distance = this.calcDistance(other);
        double Fx = this.calcForceExertedBy(other) * dx / distance;
        return Fx;
    }

    public double calcForceExertedByY(Planet other) {
        double dy = other.yyPos - this.yyPos;
        double distance = this.calcDistance(other);
        double Fy = this.calcForceExertedBy(other) * dy / distance;
        return Fy;
    }

    public double calcNetForceExertedByX (Planet[] other) {
        double netFx = 0.00;
        for (int i=0; i<other.length; i++) {
            if (this.equals(other[i])) {
                continue;
            } else {
                double Fx = this.calcForceExertedByX(other[i]);
                netFx += Fx;
            }
        }
        return netFx;
    }

    public double calcNetForceExertedByY (Planet[] other) {
        double netFy = 0.00;
        for (int i=0; i<other.length; i++) {
            if (this.equals(other[i])) {
                continue;
            } else {
                double Fy = this.calcForceExertedByY(other[i]);
                netFy += Fy;
            }
        }
        return netFy;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}