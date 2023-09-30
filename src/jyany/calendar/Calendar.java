package jyany.calendar;

public class Calendar {
    private static final int[] MAX_DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public void printCalendar(int year, int month) {
        System.out.printf("   <<%4d년%3d월>>\n", year, month);
        System.out.println("Su Mo Tu We Th Fr Sa");
        System.out.println("--------------------");

        int maxDays = getMaxDaysOfMonth(month);
        for (int i = 1; i <= maxDays; i++){
            System.out.printf("%2d ", i);
            if (i % 7 == 0){
                System.out.println();
            }
        }
        System.out.println();

//        System.out.println("Su Mo Tu We Th Fr Sa");
//        System.out.println("--------------------");
//        System.out.println(" 1  2  3  4  5  6  7");
//        System.out.println(" 8  9 10 11 12 13 14");
//        System.out.println("15 16 17 18 19 20 21");
//        System.out.println("22 23 24 25 26 27 28");
//        System.out.println("29 30 31");
    }
    public int getMaxDaysOfMonth (int month){
        return MAX_DAYS[month];
    }
}
