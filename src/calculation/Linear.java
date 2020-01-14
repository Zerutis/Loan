package calculation;

public class Linear {
    public static double countLeftToPay(double money, double credit) { return money - credit; }

    public static double countCredit (double money, int months) { return money/months; }

    public static double countInterest(double money, double pct) { return pct/100/12.0*money; }

    public static double countMonthlyPay(double credit, double interest) { return credit + interest; }
}
