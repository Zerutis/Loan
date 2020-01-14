package file;

import javafx.collections.ObservableList;
import main.Loan;

import java.security.spec.ECField;
import java.util.Formatter;

public class txtFile {
    private Formatter file;

    public void openFile(){
        try {
            file = new Formatter("LoanINFO.txt");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void addRecords(ObservableList<Loan> loan){
        file.format("%s   %s    %s    %s    %s\n", "Index,", "Money,", "Monthly Pay,", "Interest,", "Credit,");
        for(int i =0; i < loan.size(); i++) {
            file.format("%d      %s      %s        %s       %s\n", loan.get(i).getIndex(),loan.get(i).getLoanMoney(), loan.get(i).getMonthlyPay(), loan.get(i).getInterest(), loan.get(i).getCredit());
        }
    }
    public void closeFile(){
        file.close();
    }
}
