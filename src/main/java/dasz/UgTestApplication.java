package dasz;

import dasz.nbpconsumingrest.UsdReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UgTestApplication {


    public static void main(String[] args)  {
        SpringApplication.run(UgTestApplication.class, args);
        System.out.println(UsdReader.getUsdForDate("2022-09-01"));


    }

}




