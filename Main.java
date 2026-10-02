import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>();
        try {
    BufferedReader reader = new BufferedReader(new FileReader("expenses.txt"));

    String line;

    while ((line = reader.readLine()) != null) {

        String[] data = line.split(",");

      int id = Integer.parseInt(data[0]);
String category = data[1];
double amount = Double.parseDouble(data[2]);
String date = data[3];
String description = data[4];

Expense expense = new Expense(id, category, amount, date, description);

        expenses.add(expense);
    }

    reader.close();

} catch (IOException e) {
    System.out.println("Error loading expenses.");
}

        while (true) {

            System.out.println("\n===== Expense Tracker =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Search Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Total Expenses");
            System.out.println("6. Category-wise Summary");
            System.out.println("7. Monthly Expense Report");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:

    System.out.print("Enter expense ID: ");
    int id = sc.nextInt();

    sc.nextLine();

    System.out.print("Enter category: ");
    String category = sc.nextLine();

    System.out.print("Enter amount: ");
    double amount = sc.nextDouble();

    sc.nextLine();

    System.out.print("Enter date: ");
    String date = sc.nextLine();

    System.out.print("Enter description: ");
    String description = sc.nextLine();

    Expense expense = new Expense(id, category, amount, date, description);

    expenses.add(expense);

    try {
        FileWriter writer = new FileWriter("expenses.txt", true);

        writer.write(id + "," + category + "," + amount + "," + date + "," + description + "\n");

        writer.close();

        System.out.println("Expense added successfully!");

    } catch (IOException e) {
        System.out.println("Error saving expense.");
    }

    break;
                

                case 2:

    if (expenses.isEmpty()) {
        System.out.println("No expenses found.");
    } else {

        System.out.println("\nExpenses:");

        for (Expense e : expenses) {
         System.out.println("ID: " + e.id + ", Category: " + e.category + ", Amount: " + e.amount + ", Date: " + e.date + ", Description: " + e.description);
        }
    }

    break;
    case 3:

    System.out.print("Enter expense ID to search: ");
    int searchId = sc.nextInt();

    boolean foundExpense = false;

    for (Expense e : expenses) {

        if (e.id == searchId) {

            System.out.println(
                "ID: " + e.id +
                ", Category: " + e.category +
                ", Amount: " + e.amount +
                ", Date: " + e.date +
                ", Description: " + e.description
            );

            foundExpense = true;
            break;
        }
    }

    if (!foundExpense) {
        System.out.println("Expense not found!");
    }

    break;
    case 4:

    System.out.print("Enter the expense ID to delete: ");
    int deleteId = sc.nextInt();

    boolean found = false;

    for (int i = 0; i < expenses.size(); i++) {

        if (expenses.get(i).id == deleteId) {
            expenses.remove(i);
            found = true;

            System.out.println("Expense deleted successfully!");
            break;
        }
    }

    if (found) {
        try {
            FileWriter writer = new FileWriter("expenses.txt");

            for (Expense e : expenses) {
                writer.write(e.id + "," + e.category + "," + e.amount + "," + e.date + "," + e.description + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error updating file!");
        }
    } else {
        System.out.println("Expense not found!");
    }

    break;


                case 5:

    double total = 0;

    for (Expense e : expenses) {
        total += e.amount;
    }

    System.out.println("Total expenses: " + total);

    break;
    case 6:

    HashMap<String, Double> categoryTotals = new HashMap<>();
    double overallTotal = 0;
    for (Expense e : expenses) {
        overallTotal += e.amount;

        if (categoryTotals.containsKey(e.category)) {
            categoryTotals.put(
                e.category,
                categoryTotals.get(e.category) + e.amount
            );
        } else {
            categoryTotals.put(e.category, e.amount);
        }
    }

    System.out.println("\n===== Category-wise Summary =====");
    for (String cat : categoryTotals.keySet()) {
    System.out.println(
      cat + ": Rs. " + categoryTotals.get(cat));
}
System.out.println("\nOverall Total: Rs. " + overallTotal);

    break;
    case 7:

    Scanner monthScanner = new Scanner(System.in);

    System.out.print("Enter month (MM): ");
    String selectedMonth = monthScanner.nextLine();
    if (!selectedMonth.matches("0[1-9]|1[0-2]")) {
    System.out.println("Invalid month! Please enter 01 to 12.");
    break;
}
    System.out.print("Enter year (YYYY): ");
    String selectedYear = monthScanner.nextLine();
    if (!selectedYear.matches("\\d{4}")) {
    System.out.println("Invalid year! Please enter a 4-digit year.");
    break;
}
    double monthlyTotal = 0;
    boolean monthFound = false;

    System.out.println("\n===== Monthly Expense Report =====");

    for (Expense e : expenses) {

        String[] dateParts = e.date.split("-");

        if (dateParts.length == 3 &&
            dateParts[1].equals(selectedMonth) &&
            dateParts[2].equals(selectedYear)) {

            System.out.println(
                e.category + " | Rs. " + e.amount +
                " | " + e.date + " | " + e.description
            );

            monthlyTotal += e.amount;
            monthFound = true;

        }
    }

    System.out.println("-----------------------------");
  if (!monthFound) {
    System.out.println("No expenses found for this month.");
}



    System.out.println("Monthly Total: Rs. " + monthlyTotal);

    break;

              


                case 8:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}