package budget;

import budget.balance.Balance;
import budget.balance.Income;
import budget.purchases.Purchase;
import budget.purchases.PurchaseType;
import budget.purchases.PurchasesData;
import budget.purchases.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private final Balance balance = new Balance();
    private final FileManager fileManager = new FileManager();
    private final Sort sort = new Sort();
    private static Map<PurchaseType, List<Purchase>> purchasesList = new HashMap<>();

    static {
        for (PurchaseType purchaseType : PurchaseType.values()) {
            purchasesList.put(purchaseType, new ArrayList<>());
        }
    }

    // Add purchase
    public void makePurchase(String title, double price, PurchaseType type) {
        Purchase purchase = new Purchase(title, price);
        balance.withDrawBalance(price);
        purchasesList.get(type).add(purchase);
    }

    // Add income
    public void makeIncome(double input) {
        Income income = new Income(input);
        balance.addIncomeToBalance(input);
    }

    // Show list of purchase
    public void showList(PurchaseType type) {
        if (purchasesList.get(type).size() < 1) {
            String errorMessage = "The purchase list is empty";
            throw new IllegalArgumentException(errorMessage);
        }
        System.out.printf("%s:\n", type.getLabel());
        ArrayList<Purchase> arrayList = (ArrayList<Purchase>) purchasesList.get(type);
        for (Purchase purchase : arrayList) {
            purchase.printInfo();
        }
        double sum = calculateSumOfPurchases(type);
        System.out.printf("Total sum: $%f\n", sum);
    }

    // Show all list of purchases
    public void showAllLists() {
        double totalSum = 0;
        System.out.print("\nAll:\n");
        for (Map.Entry<PurchaseType, List<Purchase>> entry : purchasesList.entrySet()) {
            PurchaseType type = entry.getKey();
            List<Purchase> list = entry.getValue();
            if (list.isEmpty()) {
                continue;
            }
            for (Purchase purchase : list) {
                purchase.printInfo();
            }
            totalSum += calculateSumOfPurchases(type);
        }
        if (totalSum > 0) {
            System.out.printf("Total sum: $%f\n", totalSum);
        } else {
            System.out.println("The purchase list is empty!");
        }
    }

    // Show balance
    public void showBalance() {
        System.out.print("Balance: $" + balance.getBalance());
    }

    // Save purchases data
    public void saveAllPurchases() {
        double balanceValue = balance.getBalance();
        fileManager.saveData(purchasesList, balanceValue);
        System.out.print("\nPurchases were saved!");
    }

    // Load purchases data
    public void downloadAllPurchases() {
        purchasesList.clear();
        fileManager.loadData();
        setDownloadedData();
    }

    // Sort all purchases
    public void sortAll() {
        double totalSum = 0;
        for (Map.Entry<PurchaseType, List<Purchase>> entry : purchasesList.entrySet()) {
            PurchaseType type = entry.getKey();
            List<Purchase> list = entry.getValue();
            if (!list.isEmpty()) {
                totalSum += calculateSumOfPurchases(type);
            }
        }
        if (totalSum > 0) {
            sort.sortAllPurchases(purchasesList);
        } else {
            System.out.println("\nThe purchase list is empty!");
        }
    }

    // Sort by type
    public void sortByType() {
        sort.sortByTypePurchases(purchasesList);
    }

    // Sort certain type
    public void sortCertainType(PurchaseType type) {
        List<Purchase> list = purchasesList.get(type);
        if (!list.isEmpty()) {
            sort.sortCertainType(type, list);
        } else {
            System.out.print("\nThe purchase list is empty!\n");
        }
    }
    // Set purchases downloaded data
    private void setDownloadedData() {
        PurchasesData purchasesData = fileManager.getDownloadedPurchasesList();
        purchasesList = purchasesData.purchases;
        balance.setBalance(purchasesData.balance);
    }

    private double calculateSumOfPurchases(PurchaseType type) {
        double sum = 0;
        List<Purchase> arrayList = purchasesList.get(type);
        for (Purchase purchase : arrayList) {
            sum += purchase.getPrice();
        }

        return sum;
    }
}
