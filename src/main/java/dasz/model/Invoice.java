package dasz.model;

import java.util.ArrayList;


public class Invoice {
    private ArrayList<Computer> computerList;

    public Invoice() {
        this.computerList = new ArrayList<Computer>();
    }

    public void addComputer(Computer computer){
        computerList.add(computer);
    }

    public ArrayList<Computer> getComputerList() {
        return computerList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<faktura>\n");
        for (Computer c:computerList){
            sb.append("<komputer>\n");
            sb.append(c.toString());
            sb.append("</komputer>\n");
        }
        sb.append("</faktura>\n");
        return sb.toString();
    }


}
