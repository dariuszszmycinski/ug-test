package dasz.xml;

import dasz.model.Invoice;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class XmlInvoiceWriter {

    public static void saveInvoiceToXml(Invoice invoice){
        String fileToWrite = "src/main/java/dasz/xmlArchive/invoice "+ LocalDate.now() +".xml";
        try {
            FileWriter myWriter = new FileWriter(fileToWrite, true);
            myWriter.write(invoice.toString());
            myWriter.close();
            System.out.println("Utworzono plik Xml \"invoice "+LocalDate.now()+".xml\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
