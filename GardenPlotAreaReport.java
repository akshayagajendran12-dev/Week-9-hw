import java.util.Scanner;

interface Plot {
    double calculateArea();
    String getShape();
}

class Circle implements Plot {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public String getShape() {
        return "CIRCLE";
    }
}

class Rectangle implements Plot {
    private double length, width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double calculateArea() {
        return length * width;
    }

    public String getShape() {
        return "RECTANGLE";
    }
}

class Triangle implements Plot {
    private double base, height;

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double calculateArea() {
        return 0.5 * base * height;
    }

    public String getShape() {
        return "TRIANGLE";
    }
}

class GardenPlot {
    String owner;
    Plot plot;

    GardenPlot(String owner, Plot plot) {
        this.owner = owner;
        this.plot = plot;
    }

    void printReport() {
        System.out.printf("%s (%s): %.2f%n",
                owner, plot.getShape(), plot.calculateArea());
    }
}

public class GardenPlotAreaReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        GardenPlot[] plots = new GardenPlot[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().trim().split("\\s+");

            String shape = data[0];
            String owner = data[1];
            Plot plot;

            switch (shape) {
                case "CIRCLE":
                    plot = new Circle(Double.parseDouble(data[2]));
                    break;
                case "RECTANGLE":
                    plot = new Rectangle(
                            Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]));
                    break;
                case "TRIANGLE":
                    plot = new Triangle(
                            Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid shape");
            }

            plots[i] = new GardenPlot(owner, plot);
            total += plot.calculateArea();
        }

        for (GardenPlot p : plots) {
            p.printReport();
        }

        System.out.printf("Total Area: %.2f%n", total);
        sc.close();
    }
}
