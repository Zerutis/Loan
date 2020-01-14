package validation;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class Error {
    public static boolean isCorrectPct(TextField textField){
        double pct = Double.parseDouble(textField.getText());
        if (pct <= 100.0 && pct >= 0.0)
            return true;
        return false;
    }
    public static boolean isCorrectMoney(TextField textField){
        double money = Double.parseDouble(textField.getText());
        if (money <= 1000000.0 && money >= 0.0)
            return true;
        return false;
    }
    public static boolean isCorrectYears(TextField textField){
        int year = Integer.parseInt(textField.getText());
        if (year <= 50 && year >= 0)
            return true;
        return false;
    }
    public static boolean isCorrectMonth(TextField textField){
        int month = Integer.parseInt(textField.getText());
        if (month <= 11 && month >= 0)
            return true;
        return false;
    }
}
