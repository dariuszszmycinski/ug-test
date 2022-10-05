package dasz;

import dasz.model.Computer;
import dasz.nbpconsumingrest.UsdReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UgTestApplication {


    public static void main(String[] args)  {
        SpringApplication.run(UgTestApplication.class, args);
        System.out.println(UsdReader.getUsdForDate("2022-09-01"));
        Computer computer = new Computer("maszyna", "2022-09-01", 345);
        System.out.println(computer);

    }

}




