package dasz;

import dasz.controller.CmdUserController;
import dasz.model.Computer;
import dasz.model.Invoice;
import dasz.nbpconsumingrest.UsdReader;
import dasz.xml.XmlInvoiceWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UgTestApplication {


    public static void main(String[] args)  {
        SpringApplication.run(UgTestApplication.class, args);
        //System.out.println(UsdReader.getUsdForDate("2022-09-01"));
        //Computer computer = new Computer("maszyna", "2022-01-03", 345);
        //Computer computer1 = new Computer("bestia", "2022-01-10", 543);
        //Invoice invoice = new Invoice();
        //invoice.addComputer(computer);
        //invoice.addComputer(computer1);
        //System.out.println(invoice);
        //XmlInvoiceWriter.saveInvoiceToXml(invoice);
        CmdUserController.start();


    }

}




