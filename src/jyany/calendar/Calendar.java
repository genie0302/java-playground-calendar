package jyany.calendar;

import java.util.Scanner;

public class Calendar {
    private static final int[] MAX_DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public void printSampleCalendar() {
        System.out.println("일 월  화 수  목 금  토");
        System.out.println("--------------------");
        System.out.println(" 1  2  3  4  5  6  7");
        System.out.println(" 8  9 10 11 12 13 14");
        System.out.println("15 16 17 18 19 20 21");
        System.out.println("22 23 24 25 26 27 28");
    }
    public int getMaxDaysOfMonth (int month){
        return MAX_DAYS[month];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calendar cal = new Calendar();

        while (true){
            System.out.println("월을 입력하세요.");
            System.out.print("cal> ");
            int month = sc.nextInt();

            if (month == -1){
                break;
            }
            else if (month < 1 || month > 12){
                continue;
            }

            System.out.printf("%d월은 %d일까지 있습니다.\n", month, cal.getMaxDaysOfMonth(month));

        }

        System.out.println("Have a nice day~");
    }
}
