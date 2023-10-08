package jyany.calendar;

import java.util.*;

public class Prompt {

    public void runPrompt(){
        printMenu();

        Scanner sc = new Scanner(System.in);
        Calendar cal = new Calendar();

        label:
        while (true) {
            System.out.println("명령 (1, 2, 3, h, q)");
            System.out.print("> ");
            String input = sc.nextLine();

            switch (input) {
                case "1":
                    cmdRegister(sc, cal);
                    break;
                case "2":
                    cmdSearch(sc, cal);
                    break;
                case "3":
                    cmdCal(sc, cal);
                    break;
                case "h":
                    printMenu();
                    break;
                case "q":
                    break label;
            }
        }
        System.out.println("Bye");
    }

    private void cmdCal(Scanner sc, Calendar cal) {
        while (true){
            System.out.println("년도를 입력하세요.");
            System.out.print("YEAR> ");
            int year = Integer.parseInt(sc.nextLine());

            System.out.println("월을 입력하세요.");
            System.out.print("MONTH> ");
            int month = Integer.parseInt(sc.nextLine());
            if (month < 1 || month > 12){
                System.out.println("잘못된 입력입니다.");
                return;
            }
            cal.printCalendar(year, month);
        }
    }

    public void printMenu(){
        System.out.println("+----------------------+");
        System.out.println("| 1. 일정 등록");
        System.out.println("| 2. 일정 검색");
        System.out.println("| 3. 달력 보기");
        System.out.println("| h. 도움말 q. 종료");
        System.out.println("+----------------------+");
    }

    public void cmdRegister(Scanner sc, Calendar cal){
        System.out.println("[일정 등록] 날짜를 입력하세요.");
        System.out.print("> ");
        String date = sc.nextLine();

        System.out.println("일정을 입력하세요.");
        System.out.print("> ");
        String schedule = sc.nextLine();

        cal.registerSchedule(date, schedule);

        System.out.println("일정이 등록되었습니다.");
    }

    public void cmdSearch (Scanner sc, Calendar cal){
        System.out.println("[일정 검색] 날짜를 검색하세요.");
        System.out.print("> ");
        String date = sc.nextLine();

        cal.searchSchedule(date);
    }
    public static void main(String[] args) {
        //쉘 실행
        Prompt p = new Prompt();
        p.runPrompt();
    }
}
