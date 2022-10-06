package dasz.controller;

import dasz.database.H2Interface;
import dasz.model.Computer;
import dasz.model.Invoice;
import dasz.nbpconsumingrest.UsdReader;
import dasz.xml.XmlInvoiceWriter;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CmdUserController {


    public static void start() {
        boolean run = true;
        Scanner in = new Scanner(System.in);
        H2Interface h2Interface = new H2Interface();
        h2Interface.createTable();
        List<Computer> computerList;
        while (run) {
            System.out.println("Wybierz opcję:\n1 - nowa faktura\n2 - wyszukaj komputery po nazwie\n3 - wyszukaj komputery po dacie\n4 - wyjście");
            byte option = getOption(in, 4);
            switch (option) {
                case 1 -> {
                    Invoice invoice = createInvoice(in);
                    XmlInvoiceWriter.saveInvoiceToXml(invoice);
                    for (Computer c : invoice.getComputerList()) {
                        h2Interface.addComputer(c);
                    }
                }
                case 2 -> {
                    String name = getName(in);
                    computerList = h2Interface.showComputersByName(name);
                    for (Computer c : computerList) {
                        System.out.println(c.toStringFlat());
                    }
                    chooseSorting(in, computerList);
                }
                case 3 -> {
                    String date = getDate(in);
                    computerList = h2Interface.showComputersByDate(date);
                    for (Computer c : computerList) {
                        System.out.println(c.toStringFlat());
                    }
                    chooseSorting(in, computerList);
                }
                case 4 -> {
                    System.out.println("Zapraszam ponownie!");
                    run = false;
                }
            }
        }
        System.out.println("koniec programu");
        in.close();
        System.exit(0);
    }

    private static byte getOption(Scanner in, int range) {
        boolean correct = false;
        byte option = 0;
        while (!correct) {
            try {
                option = Byte.parseByte(in.nextLine());
                if (option < 1 || option > range) throw new Exception();
                correct = true;
            } catch (Exception e) {
                System.err.println("Wybierz opcję od 1 do " + range + "!");
            }
        }
        return option;
    }

    private static Invoice createInvoice(Scanner in) {
        Invoice invoice = new Invoice();
        boolean run = true;
        if (invoice.getComputerList().isEmpty()) {
            invoice.addComputer(createComputer(in));
        }
        while (run) {
            System.out.println("Wybierz opcję:\n1 - dodaj kolejny komputer\n2 - zakończ fakturę");
            byte option = getOption(in, 2);
            switch (option) {
                case 1 -> invoice.addComputer(createComputer(in));
                case 2 -> run = false;
            }
        }
        return invoice;
    }

    private static Computer createComputer(Scanner in) {
        String name = getName(in);
        String date = getDate(in);
        while (UsdReader.getUsdForDate(date) == 0.0) {
            date = getDate(in);
        }
        double usd = getUsdCost(in);
        return new Computer(name, date, usd);
    }

    private static String getName(Scanner in) {
        System.out.println("Podaj nazwę komputera:");
        String name = null;
        boolean correct = false;
        while (!correct) {
            try {
                name = in.nextLine();
                if (name == null) throw new Exception();
                correct = true;
            } catch (Exception e) {
                System.err.println("Nazwa nie może być pusta!");
            }
        }
        return name;
    }

    private static String getDate(Scanner in) {
        System.out.println("Podaj datę fakturowania w formacie YYYY-MM-DD:");
        String date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        boolean correct = false;
        while (!correct) {
            try {
                date = in.nextLine();
                sdf.parse(date);
                sdf.setLenient(false);
                correct = true;
            } catch (Exception e) {
                System.err.println("Podaj datę w formacie YYYY-MM-DD!");
            }
        }
        return date;
    }

    private static double getUsdCost(Scanner in) {
        System.out.println("Podaj koszt w USD:");
        double usd = 0;
        boolean correct = false;
        while (!correct) {
            try {
                usd = Double.parseDouble(in.nextLine());
                if (usd < 0) {
                    throw new Exception();
                }
                correct = true;
            } catch (Exception e) {
                System.err.println("Koszt musi być liczbą dodatnią, centy podaj po \".\"");
            }
        }
        return usd;
    }

    private static void chooseSorting(Scanner in, List<Computer> computerList){
        boolean run = true;
        if (!computerList.isEmpty()) {
            while (run) {
                System.out.println("Wybierz opcję:\n1 - sortuj rosnąco po nazwie\n2 - sortuj malejąco po nazwie\n3 - sortuj rosnąco po dacie\n4 - sortuj malejąco po dacie\n5 - powrót");
                byte option = getOption(in, 5);
                switch (option) {
                    case 1 -> {
                        computerList.sort(Comparator.comparing(Computer::getName));
                        for (Computer c : computerList) {
                            System.out.println(c.toStringFlat());
                        }
                    }
                    case 2 -> {
                        computerList.sort(Comparator.comparing(Computer::getName).reversed());
                        for (Computer c : computerList) {
                            System.out.println(c.toStringFlat());
                        }
                    }
                    case 3 -> {
                        computerList.sort(Comparator.comparing(Computer::getDate));
                        for (Computer c : computerList) {
                            System.out.println(c.toStringFlat());
                        }
                    }
                    case 4 -> {
                        computerList.sort(Comparator.comparing(Computer::getDate).reversed());
                        for (Computer c : computerList) {
                            System.out.println(c.toStringFlat());
                        }
                    }
                    case 5 -> run = false;
                }
            }
        } else {
            System.out.println("Nie znaleziono wyników.");
        }

    }
}
