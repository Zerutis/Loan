package main;

import validation.Error;

public class UserInput {
    private final double money;
    private final double pct;
    private final int year;
    private final int month;
    private final int term;

    public UserInput(){
        this.money = 1000.0;
        this.pct = 2.0;
        this.year = 1;
        this.month = 0;
        this.term = year*12+month;
    }

    public UserInput(String money, String pct, String year, String month) {
        this.money = Double.parseDouble(money);
        this.pct = Double.parseDouble(pct);
        this.year = Integer.parseInt(year);
        this.month = Integer.parseInt(month);
        this.term = this.year*12+this.month;
    }

    public double getMoney() {
        return money;
    }

    public double getPct() {
        return pct;
    }

    public int getTerm() {
        return term;
    }
}
