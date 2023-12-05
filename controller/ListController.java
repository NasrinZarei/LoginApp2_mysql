package controller;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import loginapp2.Income;
import model.IncomeRepository;

public class ListController implements Initializable {

    ObservableList<IncomeTable> list = FXCollections.observableArrayList();

    @FXML
    private TableColumn<IncomeTable, String> costPrice;

    @FXML
    private TableColumn<IncomeTable, String> costSubject;

    @FXML
    private TableColumn<IncomeTable, String> date;

    @FXML
    private TableColumn<IncomeTable, String> incomePrice;

    @FXML
    private TableColumn<IncomeTable, String> incomeSubject;

    @FXML
    private TableColumn<IncomeTable, String> id;

    @FXML
    private TableView<IncomeTable> tableView;
    @FXML
    private Button backList;

    private String userId;

    List<Income> incomes;
    String formattedString;
    @FXML
    private Button delete;

    String setUserId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Preferences preferences = Preferences.userRoot();
        userId = preferences.get("usersId", "empty");

        initiateCode();
        loadDate();
    }

    private void initiateCode() {
        // Set the cell value factories for the table view columns.
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        incomePrice.setCellValueFactory(new PropertyValueFactory<>("incomePrice"));
        incomeSubject.setCellValueFactory(new PropertyValueFactory<>("incomeSubject"));
        costPrice.setCellValueFactory(new PropertyValueFactory<>("costPrice"));
        costSubject.setCellValueFactory(new PropertyValueFactory<>("costSubject"));

    }

    private void loadDate() {

        IncomeRepository incomerepository = new IncomeRepository();
        incomes = incomerepository.readIncomesUsers();

        for (Income item : incomes) {
            IncomeTable income = new IncomeTable();
            setUserId = item.getUserid();

            // If the user ID matches the current user ID, add the income record to the table view.
            if (userId.equals(setUserId)) {
                System.out.println(item.getId());

                LocalDate getDate = item.getLocalDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
                formattedString = getDate.format(formatter);
                // Set the values of the IncomeTable object.
                income.setDate(formattedString);
                income.setIncomePrice(Float.toString(item.getIncomePrice()));
                income.setIncomeSubject(item.getIncomSubject());
                income.setCostPrice(Float.toString(item.getPriceCost()));
                income.setCostSubject(item.getCostSubject());
                income.setId(Integer.toString(item.getId()));
                // Refresh the table view.
                list.addAll(income);

            }

        }
        tableView.getItems().addAll(list);
    }

    @FXML
    /**
     * Deletes the selected income record from the table view and database.
     */
    void deleteItem(ActionEvent event) {
        IncomeTable incomeUser = tableView.getSelectionModel().getSelectedItem();
        int incomeUserid = Integer.valueOf(incomeUser.getId());
        tableView.getItems().remove(incomeUser);
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "1234");
            Statement myStat = myConn.createStatement();
            int result = myStat.executeUpdate("delete from incomes where id = " + incomeUserid);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static class IncomeTable {

        private StringBuilder sb = new StringBuilder();
        private SimpleStringProperty id;
        private SimpleStringProperty date;
        private SimpleStringProperty incomePrice;
        private SimpleStringProperty incomeSubject;
        private SimpleStringProperty costPrice;
        private SimpleStringProperty costSubject;

        public String getId() {
            return id.get();
        }

        public String getDate() {
            return date.get();
        }

        public String getIncomePrice() {
            return incomePrice.get();
        }

        public String getIncomeSubject() {
            return incomeSubject.get();
        }

        public String getCostPrice() {
            return costPrice.get();
        }

        public String getCostSubject() {
            return costSubject.get();
        }

        public void setId(String id) {

            this.id = new SimpleStringProperty(id);
        }

        public void setDate(String date) {

            this.date = new SimpleStringProperty(date);
        }

        public void setIncomePrice(String incomePrice) {
            this.incomePrice = new SimpleStringProperty(incomePrice);
        }

        public void setIncomeSubject(String incomeSubject) {
            this.incomeSubject = new SimpleStringProperty(incomeSubject);
        }

        public void setCostPrice(String costPrice) {
            this.costPrice = new SimpleStringProperty(costPrice);
        }

        public void setCostSubject(String costSubject) {
            this.costSubject = new SimpleStringProperty(costSubject);
        }

    }

    @FXML
    void backList(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("../view/profile.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
}
