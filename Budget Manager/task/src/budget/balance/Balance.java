package budget.balance;

public class Balance {
    private double balance = 0;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addIncomeToBalance(double income) {
        this.balance += income;
    }

    public void withDrawBalance(double withDraw) {
        this.balance -= withDraw;
    }
}
