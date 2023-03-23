package budget;


import budget.menu.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();

        do {
            try {
                menu.selectAction();


            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!menu.isSessionEnded());

    }
}
