package jyany.calendar;

import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        int num1, num2;
        //두 수의 입력을 받는다.
        System.out.println("두 수를 입력하세요.");
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        num1 = Integer.parseInt(s1);
        num2 = Integer.parseInt(s2);
        System.out.printf("%d와 %d의 합은 %d입니다.", num1, num2, num1 + num2);
    }
}
