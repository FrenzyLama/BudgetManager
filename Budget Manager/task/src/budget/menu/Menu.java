package budget.menu;

import budget.purchases.PurchaseType;
import budget.User;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final User user = new User();
    private boolean sessionEnded = false;

    public void selectAction() {
        System.out.printf("\n%s\n", MENU_BLANK);// Print menu blank list
        int numberOfMenuItem = Integer.parseInt(scanner.nextLine());
        switch (MenuItem.valueOfIndex(numberOfMenuItem)) {//Select the menu item
            case ADD_INCOME -> {//Add income
                System.out.println("\nEnter income:");
                double inputIncome = Double.parseDouble(scanner.nextLine());
                user.makeIncome(inputIncome);
                System.out.println("Income was added!");
            }

            case ADD_PURCHASE -> {
                boolean back = false;
                do {
                    back = addPurchase();// Add purchase
                } while (!back);
            }

            case LIST_OF_PURCHASES -> {
                boolean back = false;
                do {
                    back = showListOfPurchases(); //Show list of purchases
                } while (!back);
            }

            case BALANCE -> {//Show balance
                System.out.println();
                user.showBalance();
                System.out.println();
            }

            case SAVE -> {
                user.saveAllPurchases();
            }

            case LOAD -> {
                user.downloadAllPurchases();
                System.out.print("\nPurchases were loaded!\n");
            }

            case ANALYZE -> {
                boolean back = false;
                do {
                    System.out.printf("\n%s\n", SORT_BLANK);// Print menu blank list
                    back = chooseSort();
                } while (!back);
            }

            case EXIT -> {//End execute program
                this.sessionEnded = true;
                System.out.println();
                System.out.println("Bye!");
                System.exit(0);
            }
        }
    }

    public boolean isSessionEnded() {
        return this.sessionEnded;
    }

    private boolean addPurchase() {
        // Print blank of purchases
        System.out.printf("\n%s\n", TYPE_OF_PURCHASE_BLANK);
        int categoryOfPurchase = Integer.parseInt(scanner.nextLine());
        if (categoryOfPurchase == 5) {
            return true; // Back
        }
        PurchaseType type = PurchaseType.valueOfIndex(categoryOfPurchase);
        // Enter name and price of purchase
        // scanner.nextLine();
        System.out.println("\nEnter purchase name:");
        String inputName = scanner.nextLine();
        System.out.println("Enter its price:");
        double inputPrice = Double.parseDouble(scanner.nextLine());
        user.makePurchase(inputName, inputPrice, type);
        System.out.println("Purchase was added!");
        return false;
    }

    private boolean showListOfPurchases() {
        System.out.printf("\n%s\n", LISTS_OF_PURCHASE);
        int categoryOfPurchase = Integer.parseInt(scanner.nextLine());
        if (categoryOfPurchase == 6) {
            return true; // Back
        }
        if (categoryOfPurchase == 5) {
            user.showAllLists();
        } else {
            PurchaseType type = PurchaseType.valueOfIndex(categoryOfPurchase);
            System.out.println();
            user.showList(type);
        }
        return false;
    }

    private boolean chooseSort() {
        boolean back = false;
        int numberOfItem = Integer.parseInt(scanner.nextLine());
        switch (SortMenuItem.valueOfIndex(numberOfItem)) {
            case ALL -> {
                user.sortAll();
            }
            case BY_TYPE -> {
                user.sortByType();
            }
            case CERTAIN_TYPE -> {
                System.out.printf("\n%s\n", TYPE_FOR_SORT);// Print types for sort
                int numberOfType = Integer.parseInt(scanner.nextLine());
                PurchaseType type = PurchaseType.valueOfIndex(numberOfType);
                user.sortCertainType(type);
            }
            case BACK -> {
                back = true;
            }
        }
        return back;
    }

    private final String MENU_BLANK = """
            Choose your action:
            1) Add income
            2) Add purchase
            3) Show list of purchases
            4) Balance
            5) Save
            6) Load
            7) Analyze (Sort)
            0) Exit""";

    private final String TYPE_OF_PURCHASE_BLANK = """
            Choose the type of purchase
            1) Food
            2) Clothes
            3) Entertainment
            4) Other
            5) Back""";

    private final String LISTS_OF_PURCHASE = """
            Choose the type of purchases
            1) Food
            2) Clothes
            3) Entertainment
            4) Other
            5) All
            6) Back""";

    private final String SORT_BLANK = """
            How do you want to sort?
            1) Sort all purchases
            2) Sort by type
            3) Sort certain type
            4) Back""";

    private final String TYPE_FOR_SORT = "Choose the type of purchase\n" +
            "1) Food\n" +
            "2) Clothes\n" +
            "3) Entertainment\n" +
            "4) Other";
}
