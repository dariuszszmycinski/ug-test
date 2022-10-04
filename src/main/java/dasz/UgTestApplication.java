package dasz;

import dasz.nbpconsumingrest.UsdReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UgTestApplication {

    public static void main(String[] args)  {
        SpringApplication.run(UgTestApplication.class, args);
        System.out.println(UsdReader.getUsdForDate("2022-09-01"));




        /*NbpUSD nbpUSD=new NbpUSD();
        Rates rates = new Rates();
        rates.setNo("169/C/NBP/2022");
        rates.setEffectiveDate("2022-09-01");
        rates.setBid(4.6768);
        rates.setAsk(4.7712);
        nbpUSD.setTable('C');
        nbpUSD.setCurrency("dolar amerykański");
        nbpUSD.setCode("USD");
        nbpUSD.setRates(rates);
        System.out.println(nbpUSD);*/

    }
}




