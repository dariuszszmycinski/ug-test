package dasz.model;

import dasz.nbpconsumingrest.UsdReader;

public class Computer {
    private String name;
    private String date;
    private double costUSD;
    private double costPLN;

    public Computer(String name, String date, double costUSD) {
        this.name = name;
        this.date = date;
        this.costUSD = costUSD;
        this.costPLN = UsdReader.getUsdForDate(date)*costUSD;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public double getCostUSD() {
        return costUSD;
    }

    public double getCostPLN() {
        return costPLN;
    }

    @Override
    public String toString() {
        return
                "<nazwa></"+name+">\n" +
                "<data_ksiegowania></" + date + ">\n" +
                "<koszt_USD></" + costUSD + ">\n" +
                "<koszt_PLN></" + costPLN +">";
    }
}
