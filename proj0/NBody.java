public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        int numPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numPlanets = in.readInt();
        Planet[] planets = new Planet[numPlanets];
        double radius = in.readDouble();
        for (int i=0; i<planets.length; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String image = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, image);

        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();
        for (double time=0.00; time<T; time+=dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i=0; i< planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int j=0; j< planets.length; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg");
            StdDraw.show();
            for (Planet planet: planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}