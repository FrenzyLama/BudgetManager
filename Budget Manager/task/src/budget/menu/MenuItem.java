package budget.menu;

import java.util.HashMap;
import java.util.Map;

public enum MenuItem {
    ADD_INCOME(1),
    ADD_PURCHASE(2),
    LIST_OF_PURCHASES(3),
    BALANCE(4),
    SAVE(5),
    LOAD(6),
    ANALYZE(7),
    EXIT(0);

    private final int index;

    private static final Map<Integer, MenuItem> BY_INDEX = new HashMap<>();

    static {
        for (MenuItem menuItem : values()) {
            BY_INDEX.put(menuItem.index, menuItem);
        }
    }

    MenuItem(int index) {
        this.index = index;
    }

    public static MenuItem valueOfIndex(int index) {
        return BY_INDEX.get(index);
    }
}

