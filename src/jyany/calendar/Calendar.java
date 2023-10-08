package jyany.calendar;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Calendar {
    private static final int[] MAX_DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] LEAP_YEAR_MAX_DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final Map<LocalDate, PlanItem> planMap;

    public Calendar(){
        planMap = new HashMap<>();
    }
    public void registerPlan (String strDate, String plan) {
        LocalDate date = PlanItem.getDateFromString(strDate);

        if (planMap.containsKey(date)){
            PlanItem planItem = planMap.get(date);
            planItem.addPlan(plan);
        } else {
            PlanItem planItem = new PlanItem(strDate, plan);
            planMap.put(date, planItem);
        }
    }
    public PlanItem searchPlan (String strDate) {
        LocalDate date = PlanItem.getDateFromString(strDate);
        return planMap.getOrDefault(date, null);
    }
    public void printCalendar(int year, int month) {
        System.out.printf("   <<%4d년%3d월>>\n", year, month);
        System.out.println("SU MO TU WE TH FR SA");
        System.out.println("--------------------");

        int maxDays = getMaxDaysOfMonth(year, month);
        int spaceSize = findFirstDay(year, month);
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
        } else {
            return false;
        }
    }
    public int getMaxDaysOfMonth (int year, int month){
        if (isLeapYear(year)){
            return LEAP_YEAR_MAX_DAYS[month];
        } else {
            return MAX_DAYS[month];
        }
    }

    // Sun: 0 ~ Sat: 6
    // 주어진 년, 월의 1일이 무슨 요일인지 구하는 함수
    public int findFirstDay (int year, int month) {

        int[] referenceDays = {5, 3, 2, 0}; //금, 수, 화, 일
        int[] leapYearSameDayWithDoomsDay = {0, 4, 29, 21, 4, 9, 6, 11, 8, 5, 10, 7, 12};
        int[] sameDayWithDoomsDay = {0, 3, 28, 21, 4, 9, 6, 11, 8, 5, 10, 7, 12};

        int referDay = referenceDays[((year / 100) - 2 ) % 4];
        int doomsDay = referDay + (year % 100) / 12 + (year % 100) % 12 + (year % 100) % 12 / 4;
        doomsDay %= 7;

        int day;
        if (isLeapYear(year)) {
            day = (doomsDay - (leapYearSameDayWithDoomsDay[month] - 1)) % 7;
        } else {
            day = (doomsDay - (sameDayWithDoomsDay[month] - 1)) % 7;
        }

        if (day < 0)
            day += 7;

        return day;
    }
}
