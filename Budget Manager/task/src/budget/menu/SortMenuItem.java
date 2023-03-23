package budget.menu;

import java.util.HashMap;
import java.util.Map;

public enum SortMenuItem {
    ALL(1),
    BY_TYPE(2),
    CERTAIN_TYPE(3),
    BACK(4);

    private final int index;

    private static final Map<Integer, SortMenuItem> BY_INDEX = new HashMap<>();

    static {
        for (SortMenuItem sortMenuItem : values()) {
            BY_INDEX.put(sortMenuItem.index, sortMenuItem);
        }
    }

    SortMenuItem(int index) {
        this.index = index;
    }

    public static SortMenuItem valueOfIndex(int index) {
        return BY_INDEX.get(index);
    }

}
