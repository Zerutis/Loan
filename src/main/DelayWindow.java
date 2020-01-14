package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DelayWindow implements Initializable {

    @FXML private ComboBox<String> fromCBox;
    @FXML private ComboBox<String> toCBox;

    private UserInput user;
    int from, to;

   public void initUser(UserInput userInput){
       user = userInput;
       for(int i = 1; i < user.getTerm()+1; i++) {
           fromCBox.getItems().add(Integer.toString(i));
           toCBox.getItems().addAll(Integer.toString(i));
       }
    }

    @FXML void selectFrom(ActionEvent event) {
        System.out.println(fromCBox.getValue());
        from = Integer.parseInt(fromCBox.getValue());
    }

    @FXML void selectTo(ActionEvent event) {
        to = Integer.parseInt(toCBox.getValue());
        System.out.println(toCBox.getValue());
    }

    @FXML void getBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Window.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Window controller = loader.getController();
        controller.initWindow2(from, to, user);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Palūkanų skaičiuoklė");
        window.setScene(scene);
        window.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       //To Do
    }
}
