import java.util.Scanner;

interface Connection {
    double calculateBill();
}

class Home implements Connection {
    private int units;

    Home(int units) {
        this.units = units;
    }

    public double calculateBill() {
        if (units <= 100) {
            return units * 5.0;
        }
        return 100 * 5.0 + (units - 100) * 7.0;
    }
}

class Shop implements Connection {
    private int units;

    Shop(int units) {
        this.units = units;
    }

    public double calculateBill() {
        return units * 8.0 + 100.0;
    }
}

class Factory implements Connection {
    private int units;

    Factory(int units) {
        this.units = units;
    }

    public double calculateBill() {
        return Math.max(units * 6.0, 1000.0);
    }
}

public class ElectricityConnectionBilling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        Connection[] connections = new Connection[n];
        String[] types = new String[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().trim().split("\\s+");

            types[i] = data[0];
            int units = Integer.parseInt(data[1]);

            switch (types[i]) {
                case "HOME":
                    connections[i] = new Home(units);
                    break;
                case "SHOP":
                    connections[i] = new Shop(units);
                    break;
                case "FACTORY":
                    connections[i] = new Factory(units);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Invalid connection type");
            }

            total += connections[i].calculateBill();
        }

        for (int i = 0; i < n; i++) {
            System.out.printf("%s: %.2f%n",
                    types[i], connections[i].calculateBill());
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
