package personal_finance_tracker;

import java.util.ArrayList;
import java.util.List;

class User {
    private String name;
    private String email;
    private String password;
    private List<Transaction> transactions;
    private double budget;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.transactions = new ArrayList<>();
        this.budget = 0.0;
    }

    // Methods for user operations
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void displayTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded.");
        } else {
            System.out.println("Transactions for " + name + ":");
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

    public void displayBudgetStatus() {
        double totalExpenses = transactions.stream().mapToDouble(Transaction::getAmount).sum();
        System.out.println("Total Expenses: $" + totalExpenses);
        System.out.println("Budget Limit: $" + budget);
        if (totalExpenses > budget) {
            System.out.println("Warning: You have exceeded your budget!");
        } else {
            System.out.println("You are within your budget.");
        }
    }

    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}