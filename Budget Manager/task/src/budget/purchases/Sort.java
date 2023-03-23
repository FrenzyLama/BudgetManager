package budget.purchases;

import budget.purchases.Purchase;
import budget.purchases.PurchasePriceComparator;
import budget.purchases.PurchaseType;
import budget.purchases.TypeSumComparator;

import java.util.*;

public class Sort {
    PurchasePriceComparator priceComparator = new PurchasePriceComparator();
    TypeSumComparator sumComparator = new TypeSumComparator();

    public void sortAllPurchases(Map<PurchaseType, List<Purchase>> listOfAll) {
        ArrayList<Purchase> listPurchases = new ArrayList<Purchase>();

        double sum = 0;

        for (Map.Entry<PurchaseType, List<Purchase>> entry : listOfAll.entrySet()) {
            List<Purchase> list = entry.getValue();
            listPurchases.addAll(list);
        }

        System.out.println("\nAll:");
        Collections.sort(listPurchases, priceComparator);
        for (Purchase p : listPurchases) {
            sum += p.getPrice();
            System.out.printf("%s $%.2f\n", p.getTitle(), p.getPrice());
        }
        System.out.printf("Total: $%.2f\n", sum);
    }

    public void sortByTypePurchases(Map<PurchaseType, List<Purchase>> listOfAll) {
        System.out.println("\nTypes:");
        ArrayList<PurchaseType> listTypes = new ArrayList<>();
        double totalSum = 0;
        for (Map.Entry<PurchaseType, List<Purchase>> entry : listOfAll.entrySet()) {
            List<Purchase> list = entry.getValue();
            PurchaseType type = entry.getKey();
            double sum = 0;
            for (Purchase p : list) {
                sum += p.getPrice();
            }
            type.setSum(sum);
            listTypes.add(type);
            totalSum += sum;
        }
        if (totalSum == 0) {
            for (PurchaseType type : listTypes) {
                double sum = type.getSum();
                System.out.printf("%s - $%.2f\n", type.getLabel(), sum);
            }
            System.out.printf("Total sum - $%.2f\n", totalSum);
        } else {
            listTypes.sort(sumComparator);
            for (PurchaseType type : listTypes) {
                double sum = type.getSum();
                System.out.printf("%s - $%.2f\n", type.getLabel(), sum);
            }
            System.out.printf("Total sum - $%.2f\n", totalSum);
        }
    }

    public void sortCertainType(PurchaseType type, List<Purchase> list) {
        System.out.printf("\n%s:\n", type.getLabel());
        TreeSet<Purchase> setPurchases = new TreeSet<Purchase>(priceComparator);
        double sum = 0;
        setPurchases.addAll(list);

        for (Purchase p : setPurchases) {
            sum += p.getPrice();
            System.out.printf("%s $%.2f\n", p.getTitle(), p.getPrice());
        }
        System.out.printf("Total: $%.2f\n", sum);
    }

}
