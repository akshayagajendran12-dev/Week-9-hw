import java.util.Scanner;

interface LibraryItem {
    double calculateFine();
}

class Book implements LibraryItem {
    private int daysLate;

    Book(int daysLate) {
        this.daysLate = daysLate;
    }

    public double calculateFine() {
        return daysLate * 2.0;
    }
}

class DVD implements LibraryItem {
    private int daysLate;

    DVD(int daysLate) {
        this.daysLate = daysLate;
    }

    public double calculateFine() {
        return Math.min(daysLate * 5.0, 50.0);
    }
}

class Magazine implements LibraryItem {
    private int daysLate;

    Magazine(int daysLate) {
        this.daysLate = daysLate;
    }

    public double calculateFine() {
        return daysLate * 1.0;
    }
}

class BorrowedItem {
    String title;
    LibraryItem item;

    BorrowedItem(String title, LibraryItem item) {
        this.title = title;
        this.item = item;
    }

    double getFine() {
        return item.calculateFine();
    }
}

public class LibraryLateFineCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        BorrowedItem[] items = new BorrowedItem[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().trim().split("\\s+");

            String type = data[0];
            String title = data[1];
            int days = Integer.parseInt(data[2]);

            LibraryItem item;

            switch (type) {
                case "BOOK":
                    item = new Book(days);
                    break;
                case "DVD":
                    item = new DVD(days);
                    break;
                case "MAGAZINE":
                    item = new Magazine(days);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid item type");
            }

            items[i] = new BorrowedItem(title, item);
            total += items[i].getFine();
        }

        for (BorrowedItem item : items) {
            System.out.printf("%s: %.2f%n",
                    item.title, item.getFine());
        }

        System.out.printf("Total Fines: %.2f%n", total);
        sc.close();
    }
}
