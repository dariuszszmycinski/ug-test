package dasz;

import dasz.controller.CmdUserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UgTestApplication {

    public static void main(String[] args)  {
        SpringApplication.run(UgTestApplication.class, args);
        CmdUserController.start();
    }

}




