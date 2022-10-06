package dasz.controller;

import dasz.database.H2Interface;
import dasz.model.Computer;
import dasz.model.Invoice;
import dasz.nbpconsumingrest.UsdReader;
import dasz.xml.XmlInvoiceWriter;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class CmdUserController {


    public static void start() {
        boolean run = true;
        Scanner in = new Scanner(System.in);
        H2Interface h2Interface = new H2Interface();
        try {
            h2Interface.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (run) {
            System.out.println("Wybierz opcję:\n1 - nowa faktura\n2 - wyszukaj komputery po nazwie\n3 - wyszukaj komputery po dacie\n4 - wyjście");
            byte option = getOption(in, 4);
            switch (option) {
                case 1:
                    Invoice invoice = createInvoice(in);
                    XmlInvoiceWriter.saveInvoiceToXml(invoice);
                    try {
                        for (Computer c : invoice.getComputerList()) {
                            h2Interface.addComputer(c);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    String name = getName(in);
                    try {
                        h2Interface.showComputersByName(name);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    String date = getDate(in);
                    try {
                        h2Interface.showComputersByDate(date);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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
                case 1:
                    invoice.addComputer(createComputer(in));
                    break;
                case 2:
                    run = false;
                    break;
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
}
