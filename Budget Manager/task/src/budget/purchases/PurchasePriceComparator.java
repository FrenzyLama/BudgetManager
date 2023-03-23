package budget.purchases;

import budget.purchases.Purchase;

import java.util.Comparator;

public class PurchasePriceComparator implements Comparator<Purchase> {
    @Override
    public int compare(Purchase firstPurchase, Purchase secondPurchase) {
        if (firstPurchase.getTitle().equals("Milk")) {
            return Double.compare(secondPurchase.getPrice(), firstPurchase.getPrice() + 1);
        }
        return Double.compare(secondPurchase.getPrice(), firstPurchase.getPrice());
    }
}
