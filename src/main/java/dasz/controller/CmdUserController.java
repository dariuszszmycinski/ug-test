package dasz.controller;

import java.util.Scanner;

public class CmdUserController {


    public static void start() {
        boolean run = true;
        Scanner in = new Scanner(System.in);
        while (run) {
            System.out.println("Wybierz opcję:\n1 - nowa faktura\n2 - wyszukaj komputery po nazwie\n3- wyszukaj komputery po dacie\n4 - wyjście");
            byte option = getOption(in);
            switch (option) {
                case 1:
                    System.out.println("1");
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    System.out.println("3");
                    break;
                case 4:
                    System.out.println("Pa pa");
                    run = false;
                    break;
            }
        }
        System.out.println("koniec programu");
        in.close();
        System.exit(0);
    }

    private static byte getOption(Scanner in) {
        boolean correct = false;
        byte option = 0;
        while (!correct) {
            try {
                option = Byte.parseByte(in.nextLine());
                if (option < 1 || option > 4) throw new Exception();
                correct = true;
            } catch (Exception e) {
                System.err.println("Wybierz opcję od 1 do 4!");
            }
        }
        return option;
    }
}
