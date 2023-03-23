package budget.purchases;

import budget.purchases.PurchaseType;

import java.util.Comparator;


public class TypeSumComparator implements Comparator<PurchaseType> {
    @Override
    public int compare(PurchaseType firstType, PurchaseType secondType) {
        return Double.compare(secondType.getSum(), firstType.getSum());
    }
}

