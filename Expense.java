public class Expense {

    int id;
    String category;
    double amount;
    String date;
    String description;

    Expense(int id, String category, double amount, String date, String description) {

        this.id = id;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }
}