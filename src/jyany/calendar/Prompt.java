package jyany.calendar;

import java.util.Scanner;

public class Prompt {
   // private static final String PROMPT = "cal> ";
    public void runPrompt(){
        Scanner sc = new Scanner(System.in);
        Calendar cal = new Calendar();

        while (true){
            System.out.println("년도를 입력하세요.");
            System.out.print("YEAR> ");
            int year = sc.nextInt();

            if (year == -1) {
                break;
            }

            System.out.println("월을 입력하세요.");
            System.out.print("MONTH> ");
            int month = sc.nextInt();

            if (month < 1 || month > 12){
                continue;
            }
            else {
                cal.printCalendar(year, month);
            }
        }
        System.out.println("Bye~");
    }
    public static void main(String[] args) {
        //쉘 실행
        Prompt p = new Prompt();
        p.runPrompt();
    }
}
