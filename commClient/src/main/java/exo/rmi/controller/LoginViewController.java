package exo.rmi.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import commande.model.User;
import exo.rmi.utils.Fabrique;
import exo.rmi.utils.LoadView;
import exo.rmi.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javax.jws.soap.SOAPBinding;
import javax.rmi.CORBA.Util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginViewController{
    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnLog;

    @FXML
    void login_user(ActionEvent event) throws Exception {
        User user = Fabrique.getiUser().findByLogin(txtEmail.getText());
        if (user != null){
            if (user.getPassword().equals(hash(txtPassword.getText()))) {
                MainViewController controller = new MainViewController();
                controller.getUser(user);
                LoadView.showView("", "mainView.fxml", 1);
            }else
                Utils.showMessage("", "", "Email ou mot de passe incorrect");
        }else {
            Utils.showMessage("", "", "Email ou mot de passe incorrect");
        }
    }

    public static String hash(String pwd) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("MD5");
        byte[] message = mDigest.digest(pwd.getBytes());
        BigInteger number = new BigInteger(1, message);
        StringBuilder hashText = new StringBuilder(number.toString(16));
        while (hashText.length() < 32) {
            hashText.insert(0, "0");
        }
        return hashText.toString();
    }

    public void logout(User u){
        u = null;
        LoadView.showView("", "loginView.fxml", 1);
    }
}
