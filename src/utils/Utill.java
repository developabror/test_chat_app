package utils;

import java.util.Scanner;

public interface Utill {
    Scanner scanner = new Scanner(System.in);
    Scanner strScanner = new Scanner(System.in);

    static int getInt(String prompt) {
        try {
            System.out.println(prompt);
            return scanner.nextInt();
        }catch (Exception e){
            scanner.reset();
            return getInt(prompt);
        }
    }
    static String getString(String prompt) {
        System.out.println(prompt);
        return strScanner.nextLine();
    }

}
