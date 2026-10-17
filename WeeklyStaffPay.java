import java.util.Scanner;

abstract class Staff {
    protected String name;

    Staff(String name) {
        this.name = name;
    }

    abstract double calculatePay();
}

class FullTime extends Staff {
    private double weeklySalary;

    FullTime(String name, double weeklySalary) {
        super(name);
        this.weeklySalary = weeklySalary;
    }

    double calculatePay() {
        return weeklySalary;
    }
}

class Hourly extends Staff {
    private double hours;
    private double rate;

    Hourly(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    double calculatePay() {
        if (hours <= 40) {
            return hours * rate;
        }
        return 40 * rate + (hours - 40) * rate * 1.5;
    }
}

class Intern extends Staff {
    private double stipend;

    Intern(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    double calculatePay() {
        return stipend;
    }
}

public class WeeklyStaffPay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        Staff[] staff = new Staff[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().trim().split("\\s+");

            switch (data[0]) {
                case "FULLTIME":
                    staff[i] = new FullTime(
                            data[1], Double.parseDouble(data[2]));
                    break;

                case "HOURLY":
                    staff[i] = new Hourly(
                            data[1],
                            Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]));
                    break;

                case "INTERN":
                    staff[i] = new Intern(
                            data[1], Double.parseDouble(data[2]));
                    break;

                default:
                    throw new IllegalArgumentException("Invalid staff type");
            }

            total += staff[i].calculatePay();
        }

        for (Staff s : staff) {
            System.out.printf("%s: %.2f%n",
                    s.name, s.calculatePay());
        }

        System.out.printf("Total Payroll: %.2f%n", total);
        sc.close();
    }
}
