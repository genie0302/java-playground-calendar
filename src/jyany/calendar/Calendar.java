package jyany.calendar;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Calendar {
    private static final int[] MAX_DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] LEAP_YEAR_MAX_DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String SAVE_FILE = "calendar.dat";
    private final Map<LocalDate, PlanItem> planMap;

    public Calendar(){
        planMap = new HashMap<>();
        loadPlan();
    }

    public void loadPlan() {
        try {
            File file = new File(SAVE_FILE);
            if (!file.exists()) {
                return;
            }
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);

            String input;
            PlanItem planItem = null;
            while(true){
                input = bufReader.readLine();
                if(input == null){
                    break;
                }
                if (input.contains("-")){
                    planItem = new PlanItem(input);
                }
                else {
                    planItem.addPlan(input);
                }
                planMap.put(planItem.date, planItem);
            }
            bufReader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void registerPlan (String strDate, String plan) {
        LocalDate date = PlanItem.getDateFromString(strDate);

        if (planMap.containsKey(date)){
            PlanItem planItem = planMap.get(date);
            planItem.addPlan(plan);
            clearPlan();
            saveAllPlans();
        } else {
            PlanItem planItem = new PlanItem(strDate, plan);
            planMap.put(date, planItem);
            savePlan(planItem);
        }

    }

    public void saveAllPlans(){
        Set<LocalDate> keySet = planMap.keySet();
        for (LocalDate date : keySet) {
            savePlan(planMap.get(date));
        }
    }

    public void clearPlan() {
        try {
            File file = new File(SAVE_FILE);

            // 1. 파일 존재여부 체크 및 생성
            if (!file.exists()) {
                file.createNewFile();
            }

            // 2. Writer 생성
            FileWriter fw = new FileWriter(file);

            // 3. Filewriter close
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void savePlan(PlanItem planItem) {
        try {
            File file = new File(SAVE_FILE);

            // 2. 파일 존재여부 체크 및 생성
            if (!file.exists()) {
                file.createNewFile();
            }

            // 3. Writer 생성
            FileWriter fw = new FileWriter(file, true);
            PrintWriter writer = new PrintWriter(fw);

            // 4. 파일에 쓰기
            writer.println(planItem.getStrDate());
            for (int i = 0; i < planItem.planList.size(); i++){
                writer.println(planItem.planList.get(i));
            }

            // 5. PrintWriter close
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
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
