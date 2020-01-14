package main;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GraphWindow implements Initializable {

    @FXML private NumberAxis AxisY;
    @FXML private CategoryAxis AxisX;
    @FXML private LineChart<?,?> loanLineChart;
    private ObservableList<Loan> loan;

    public void initChart(ObservableList<Loan> loanList){
        loan = loanList;
        XYChart.Series series = new XYChart.Series();
        String a;
        for (int i = 0; i < loan.size(); i++) {
            a = loan.get(i).getMonthlyPay();
            a = a.replace(',','.');
            series.getData().add(new XYChart.Data(Integer.toString(i), Double.parseDouble(a)));
        }
        loanLineChart.getData().addAll(series);
    }

    @FXML void getBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Window.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Window controller = loader.getController();
        controller.initWindow(loan);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Paskolos skaičiuoklė");
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TO DO
    }
}