package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import loginapp2.Helper;
import loginapp2.Income;
import loginapp2.IncomeValidation;
import model.IncomeRepository;

public class IncomeCalculatorController implements Initializable {

    @FXML
    private TextField costSubject;

    @FXML
    private DatePicker LocalDate;

    @FXML
    private Button incomeBack;

    @FXML
    private TextField incomePrice;

    @FXML
    private TextField incomeSubject;

    @FXML
    private TextField incomeuserId;

    @FXML
    private TextField priceCost;

    @FXML
    private Button submit;

    @FXML
    private ChoiceBox<String> typeCost;

    private String userId;
    List<Income> incomes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Preferences preferences = Preferences.userRoot();
        userId = preferences.get("usersId", "empty");
        incomeuserId.setText(userId);
        typeCost.getItems().addAll("rent", "Grocery", "water bill", "Electric bill");
    }

    @FXML
    void submit(ActionEvent event) {

        IncomeRepository repository = new IncomeRepository();
        incomes = repository.readIncomesUsers();
        System.out.println(incomes.toString());
        Helper ValidationHelp = new Helper();

        IncomeValidation incomvalid = new IncomeValidation();
        List<String> incomevalidation = incomvalid.validateIncome(priceCost.getText(), incomePrice.getText(), costSubject.getText(), incomeSubject.getText());
        if (!incomevalidation.isEmpty()) {
            String alert = "Please fix the following errors:\n";
            for (String incomeerror : incomevalidation) {
                alert += " * " + incomeerror + "\n";
            }
            ValidationHelp.help(alert);
            return;
        }

        String UseridText = incomeuserId.getText();
        String incomePriceText = incomePrice.getText();
        if (incomePriceText.length() == 0) {
            incomePriceText = "0";
        }
        Float incomePricFloat = Float.parseFloat(incomePriceText);

        String priceCostText = priceCost.getText();
        Float priceCostFloat = Float.parseFloat(priceCostText);

        LocalDate selectedDate = LocalDate.getValue();

        String selectedItem = typeCost.getValue();

        repository.writeRegisterUsers(UseridText, selectedDate, incomePricFloat, incomeSubject.getText(),
                selectedItem, priceCostFloat, costSubject.getText());
        ValidationHelp.help("Successfully registered");
    }

    @FXML
    void incomeBack(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("../view/profile.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
}
