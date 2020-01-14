package validation;

import java.text.DecimalFormat;


public class Format {

    public static String getNumberFormat(double number){
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(number);
    }
}
