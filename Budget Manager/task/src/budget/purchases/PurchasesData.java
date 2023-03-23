package budget.purchases;

import budget.purchases.Purchase;
import budget.purchases.PurchaseType;

import java.util.List;
import java.util.Map;

public class PurchasesData {
    Map<PurchaseType, List<Purchase>> purchases;
    double balance;

    public PurchasesData(Map<PurchaseType, List<Purchase>> purchases, double balance) {
        this.purchases = purchases;
        this.balance = balance;
    }
}

