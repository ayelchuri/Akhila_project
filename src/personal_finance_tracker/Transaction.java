package personal_finance_tracker;
import java.util.*;
import java.text.SimpleDateFormat;

// Class for transactions
class Transaction {
    private double amount;
    private String category;
    private Date date;

    public Transaction(double amount, String category, Date date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Getters and Setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction [Amount: $" + amount + ", Category: " + category + ", Date: " + date + "]";
    }
}