package controller;


import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import loginapp2.User;
import loginapp2.Validation;
import model.UserRepository;


public class LoginController implements Initializable {

    @FXML
    private Button Login;

    @FXML
    private Button backLogin;

    @FXML
    private TextField email;

    @FXML
    private TextField password;
    
    List<User> Users;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
     /**
   * Opens the profile window if the login credentials are valid.
   *
   * @param event The action event that triggered the profile window opening.
   * @throws ClassNotFoundException If the profile window FXML file cannot be found.
     */
     @FXML
    void profileWindows(ActionEvent event) throws IOException, ClassNotFoundException{
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/profile.fxml"));
        Parent root = loader.load();
        newStage.setScene(new Scene(root));
        
        if((this.loginValidation(email.getText(), password.getText())== true)){
            newStage.show();
        }else {
            Alert emailAlert = new Alert(Alert.AlertType.INFORMATION, "Email or passsword is not coorect");
            emailAlert.show();

        }
        
        Stage stage = (Stage) Login.getScene().getWindow();
        stage.close();
    }
     /**
   * Validates the login credentials.
   *
   * @param email The user's email address.
   * @param password The user's password.
   * @return `true` if the login credentials are valid, `false` otherwise.
   * @throws ClassNotFoundException If the user data cannot be found.
   
     */
    private boolean loginValidation(String email, String password)  {
        Validation validemails = new Validation();
        validemails.validateEmail(email);
        UserRepository read = new UserRepository();
        Users = read.readRegisterUsers();
        
        for(User item : Users){
            String usersId = Integer.toString(item.getId());
            if(item.getEmail().equals(email)&& item.getPasword().equals(password)){
                
                //String usersId = Integer.toString(item.getId());
                System.out.println(usersId);
                Preferences preferences = Preferences.userRoot();
                preferences.put("usersId", usersId);
                return true;
            }
        }
        
        return false;
    }
    @FXML
    void backLogin (ActionEvent event) throws IOException {
       Parent home_page_parent = FXMLLoader.load(getClass().getResource("../view/View.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}
