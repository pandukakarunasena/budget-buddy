package Util;

import constants.Month;

import java.time.LocalDate;

public class Util {
    public static Month getCurrentMonthEnum() {
        int currentMonth = LocalDate.now().getMonthValue();
        Month[] months = Month.values();
        Month currentMonthEnum = months[currentMonth - 1];
        return  currentMonthEnum;
    }
}
