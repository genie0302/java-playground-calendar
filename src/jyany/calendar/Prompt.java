package jyany.calendar;

import java.util.Scanner;

public class Prompt {
    private static final String PROMPT = "cal> ";
    public void runPrompt(){
        Scanner sc = new Scanner(System.in);
        Calendar cal = new Calendar();

        while (true){
            System.out.println("월을 입력하세요.");
            System.out.print(PROMPT);
            int month = sc.nextInt();

            if (month == -1){
                break;
            }
            else if (month < 1 || month > 12){
                continue;
            }
            else {
                cal.printCalendar(2023, month);
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
