package jyany.calendar;

public class Calendar {
    private static final int[] MAX_DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] LEAP_YEAR_MAX_DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public void printCalendar(int year, int month, String weekday) {
        System.out.printf("   <<%4d년%3d월>>\n", year, month);
        System.out.println("SU MO TU WE TH FR SA");
        System.out.println("--------------------");

        int maxDays = getMaxDaysOfMonth(year, month);
        int spaceSize = parseDay(weekday);
        for (int i = 0; i < spaceSize; i++){
            System.out.print("   ");
        }
        for (int i = 1; i <= maxDays; i++){
            System.out.printf("%2d ", i);
            if ((spaceSize + i) % 7 == 0){
                System.out.println();
            }
        }
        System.out.println();
    }
    public boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
            return true;
        }
        else {
            return false;
        }
    }
    public int getMaxDaysOfMonth (int year, int month){
        if (isLeapYear(year)){
            return LEAP_YEAR_MAX_DAYS[month];
        }
        else {
            return MAX_DAYS[month];
        }
    }

    /*
    * @param weekday 요일명
    * @return 0~6 (0 = Sunday, 6 = Saturday)
    */
    public int parseDay (String weekday) {
        switch (weekday){
            case "MO":
                return 1;
            case "TU":
                return 2;
            case "WE":
                return 3;
            case "TH":
                return 4;
            case "FR":
                return 5;
            case "SA":
                return 6;
            default:
                return 0;
        }
    }
}
