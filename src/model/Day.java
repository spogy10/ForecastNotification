package model;

import java.util.HashMap;
import java.util.Map;

public enum Day {
    Sunday(1),
    Monday(2),
    Tuesday(3),
    Wednesday(4),
    Thursday(5),
    Friday(6),
    Saturday(7);


    private final int index;
    private static final Map<Integer, Day> intToDayMap = new HashMap<>();

    static {
        for (Day day : Day.values()) {
            intToDayMap.put(day.index, day);
        }
    }

    Day(int index){
        this.index = index;
    }

    public static Day getDayFromIndex(int index){
        return intToDayMap.get(index);
    }

}
