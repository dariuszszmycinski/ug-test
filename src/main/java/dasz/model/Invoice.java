package dasz.model;

import java.util.ArrayList;


public class Invoice {
    private ArrayList<Computer> computerList;

    public Invoice(ArrayList<Computer> computerList) {
        this.computerList = computerList;
    }

    public void addComputer(Computer computer){
        computerList.add(computer);
    }

    public ArrayList<Computer> getComputerList() {
        return computerList;
    }
}
