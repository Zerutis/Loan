package main;

public class Loan {
    private int index;
    private String loanMoney;
    private String monthlyPay;
    private String interest;
    private String credit;

    public Loan(int index, String loanMoney, String monthlyPay, String interest, String credit) {
        this.index = index;
        this.loanMoney = loanMoney;
        this.monthlyPay = monthlyPay;
        this.interest = interest;
        this.credit = credit;
    }

    public int getIndex() { return index; }

    public String getLoanMoney() {
        return loanMoney;
    }

    public String getMonthlyPay() {
        return monthlyPay;
    }

    public String getInterest() {
        return interest;
    }

    public String getCredit() {
        return credit;
    }
}
