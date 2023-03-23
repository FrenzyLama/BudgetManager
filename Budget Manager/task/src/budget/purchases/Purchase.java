package budget.purchases;

import com.google.gson.annotations.Expose;
import com.google.gson.Gson;

public class Purchase {

    private String title;

    private double price;

    public Purchase(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public void printInfo() {
        System.out.printf("%s $%f\n", this.title, this.price);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

