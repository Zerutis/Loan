package calculation;

import static java.lang.Math.pow;

public class Annuity {
    public static double countLeftToPay(double money, double credit) { //* liko mokėti
        return money - credit;
    }

    public static double countCredit (double interest, double monthlyPay) { //* kreditas
        return monthlyPay - interest;
    }

    public static double countMonthlyPay(double money, double pct, int months) {
        return money*(pct/100.0/12.0) * pow(1.0+pct/100.0/12.0,months)/(pow(1.0+pct/100.0/12.0,months)-1.0);
    }

    public static double countInterest(double money, double pct) { //* palūkanos
        return pct/100/12.0*money;
    }
}
