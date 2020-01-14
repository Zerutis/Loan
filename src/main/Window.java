package main;

import calculation.Annuity;
import calculation.Linear;
import file.txtFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import validation.Error;
import validation.Format;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Window implements Initializable {

    @FXML private TableView<Loan> table;
    @FXML private TableColumn<Loan, Integer> loanCol;
    @FXML private TableColumn<Loan, String> monthlyIncomeCol;
    @FXML private TableColumn<Loan, String> interestCol;
    @FXML private TableColumn<Loan, String> creditCol;
    @FXML private TableColumn<Loan, String> monthCol;

    @FXML private TextField interestFld;
    @FXML private TextField moneyFld;
    @FXML private TextField monthFld;
    @FXML private TextField yearFld;

    @FXML private Button graphBtn;
    @FXML private Button filterBtn;
    @FXML private Button linearBtn;
    @FXML private Button delayBtn;
    @FXML private Button annuityBtn;
    @FXML private Button File;

    @FXML private ComboBox<String> fromBox;
    @FXML private ComboBox<String> toBox;

    int from;
    int to;
    UserInput fromOther;

    UserInput userInput;
    ObservableList<Loan> loanList = FXCollections.observableArrayList();

    public ObservableList<Loan> calculateLinear(UserInput userInput) {
        double leftToPayMoney = userInput.getMoney();
        double credit = Linear.countCredit(userInput.getMoney(),userInput.getTerm());
        double monthlyPay;
        double interest;
        fillCBox();

        for(int i = 0; i < userInput.getTerm(); i++) {

            interest = Linear.countInterest(leftToPayMoney,userInput.getPct());
            monthlyPay = Linear.countMonthlyPay(credit,interest);

            Loan loan = new Loan(i+1,
                    Format.getNumberFormat(leftToPayMoney),
                    Format.getNumberFormat(monthlyPay),
                    Format.getNumberFormat(interest),
                    Format.getNumberFormat(credit));
            loanList.add(loan);

            if(leftToPayMoney == 0.0)
                break;
            leftToPayMoney = Linear.countLeftToPay(leftToPayMoney,credit);
        }
        return loanList;
    }

    public ObservableList<Loan> calculateAnnuity(UserInput userInput){
        double leftToPayMoney = userInput.getMoney();
        double credit;
        double monthlyPay = Annuity.countMonthlyPay(userInput.getMoney(),userInput.getPct(),userInput.getTerm());
        double interest;
        fillCBox();

        for(int i = 0; i < userInput.getTerm(); i++) {
            interest = Annuity.countInterest(leftToPayMoney,userInput.getPct());
            credit = Annuity.countCredit(interest,monthlyPay);
            Loan loan = new Loan(i+1,Format.getNumberFormat(leftToPayMoney),
                    Format.getNumberFormat(monthlyPay),
                    Format.getNumberFormat(interest),
                    Format.getNumberFormat(credit));
            loanList.add(loan);

            if(leftToPayMoney == 0.0)
                continue;
            leftToPayMoney = Annuity.countLeftToPay(leftToPayMoney,credit);
        }
        return loanList;
    }

    @FXML void countLinear(ActionEvent event) {
        table.getItems().removeAll(loanList);
        userInput = new UserInput(moneyFld.getText(), interestFld.getText(), yearFld.getText(), monthFld.getText());
        loanList = calculateLinear(userInput);
        table.setItems(loanList);
    }

    @FXML void countAnnuity(ActionEvent event) {
        if(!(Error.isCorrectMoney(moneyFld) && Error.isCorrectPct(interestFld) && Error.isCorrectYears(yearFld) && Error.isCorrectMonth(monthFld))){
            JOptionPane.showMessageDialog(null, "Noob, you won't get the money");
        }
        table.getItems().removeAll(loanList);
        userInput = new UserInput(moneyFld.getText(), interestFld.getText(), yearFld.getText(), monthFld.getText());
        loanList = calculateAnnuity(userInput);
        table.setItems(loanList);
    }

    @FXML void filterOpt(ActionEvent event) {
        ObservableList<Loan> loan = loanList;
        while (from != loan.size()){
            loan.remove(0);
            from--;
        }
        while (to != loan.size()){
            loan.remove(to);
        }
        table.setItems(loan);
    }

    @FXML void showGraph(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GraphWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        GraphWindow controller = loader.getController();
        controller.initChart(loanList);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Graph");
        window.setScene(scene);
        window.show();
    }

    @FXML void saveToFile(ActionEvent event) {
        txtFile file = new txtFile();
        file.openFile();
        file.addRecords(loanList);
        file.closeFile();
    }
    void initWindow2(int from, int to, UserInput userInput){
        this.from = from;
        this.to = to;
        fromOther = userInput;
    }

    @FXML void selectFrom(ActionEvent event) {
        from = Integer.parseInt(fromBox.getValue());
    }

    @FXML void selectTo(ActionEvent event) {
        to = Integer.parseInt(toBox.getValue());
    }

    void initWindow(ObservableList<Loan> loan){
        loanList = loan;
    }

    @FXML void isDoubleM(ActionEvent event) {
        if(!moneyFld.getText().matches("[0.00-9.99]+")){
            moneyFld.setStyle("-fx-text-inner-color: red;");
        }
        else
            moneyFld.setStyle("-fx-text-inner-color: black;");
    }

    @FXML void isDoubleI(ActionEvent event) {
        if(!interestFld.getText().matches("[0.00-9.99]+")){
            interestFld.setStyle("-fx-text-inner-color: red;");
        }
        else
            interestFld.setStyle("-fx-text-inner-color: black;");
    }

    @FXML void isIntY(ActionEvent event) {
        if(!yearFld.getText().matches("[0-9]+")){
            yearFld.setStyle("-fx-text-inner-color: red;");
        }
        else
            yearFld.setStyle("-fx-text-inner-color: black;");
    }

    @FXML void isIntM(ActionEvent event) {
        if(!monthFld.getText().matches("[0-9]+")){
            monthFld.setStyle("-fx-text-inner-color: red;");
        }
        else
            monthFld.setStyle("-fx-text-inner-color: black;");
    }

    void fillCBox(){
        for (int i = 1; i <userInput.getTerm()+1;i++){
            fromBox.getItems().add(Integer.toString(i));
            toBox.getItems().addAll(Integer.toString(i));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        userInput = new UserInput();
        monthCol.setCellValueFactory(new PropertyValueFactory<>("index"));
        loanCol.setCellValueFactory(new PropertyValueFactory<>("loanMoney"));
        monthlyIncomeCol.setCellValueFactory(new PropertyValueFactory<>("monthlyPay"));
        interestCol.setCellValueFactory(new PropertyValueFactory<>("interest"));
        creditCol.setCellValueFactory(new PropertyValueFactory<>("credit"));
        table.setItems(calculateLinear(userInput));
    }
}
