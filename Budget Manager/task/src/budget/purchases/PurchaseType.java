package budget.purchases;

import java.util.HashMap;
import java.util.Map;

public enum PurchaseType {
    FOOD(1, "Food", 0),
    CLOTHES(2, "Clothes", 0),
    ENTERTAIMENT(3, "Entertainment", 0),
    OTHER(4, "Other", 0),
    ALL( 5, "All", 0);
    private static final Map<Integer, PurchaseType> BY_INDEX = new HashMap<>();



    static {
        for (PurchaseType purchaseType : values()) {
            BY_INDEX.put(purchaseType.index, purchaseType);
        }
    }

    PurchaseType(int index, String label, double sum) {
        this.index = index;
        this.label = label;
        this.sum = sum;
    }

    public static PurchaseType valueOfIndex(int index) {
        return BY_INDEX.get(index);
    }

    public String getLabel() {
        return label;
    }

    private final int index;
    private final String label;
    private double sum;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
