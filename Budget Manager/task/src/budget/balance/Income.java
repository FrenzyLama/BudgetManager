package budget.balance;

public class Income {
    final private double value;

    public void printInfo() {
        System.out.println("Income + " + value);
    }

    public Income(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}
