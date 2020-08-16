package exo.rmi.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.jfoenix.controls.*;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormatSymbols;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import commande.model.*;
import exo.rmi.utils.Fabrique;
import exo.rmi.utils.LoadView;
import exo.rmi.utils.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MainViewController {

    @FXML
    public JFXButton btnLogout;
    @FXML
    private ImageView logoImage;

    @FXML
    private Pane headerPane;

    @FXML
    private Label lblName;


    @FXML
    private Pane menuPane;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnProduit;

    @FXML
    private JFXButton btnCommande;

    @FXML
    private JFXButton btnFacture;

    @FXML
    private JFXButton btnUser;

    @FXML
    private JFXButton btnNewProduit;

    @FXML
    private JFXButton btnUpdateProduit;

    @FXML
    private JFXButton btnDeleteProduit;

    @FXML
    private Pane homePane;

    @FXML
    private Pane productPane;

    @FXML
    private Pane commandePane;

    @FXML
    private Pane facturePane;

    @FXML
    private Pane newCommPane;

    @FXML
    private JFXComboBox<Categorie> cbxCategorie;

    @FXML
    private Pane newProductPane;

    @FXML
    private Pane userPane;

    @FXML
    private TableView<Produit> produitTable;

    @FXML
    private TableColumn<Produit, String> clnCategorieProd;

    @FXML
    private TableColumn<Produit, String> clnLibelleProduit;

    @FXML
    private TableColumn<Produit, Integer> clnPrixProduit;

    @FXML
    private TableColumn<Produit, Integer> clnQuantiteProduit;

    @FXML
    private JFXTextField txtLibelleProduit;

    @FXML
    private JFXTextField txtPrixProduit;

    @FXML
    private JFXTextField txtQuantite;

    @FXML
    private JFXTextField txtMarge;

    @FXML
    private JFXTextField txtQuantiteSeuil;

    @FXML
    private JFXButton btnAjouterProduit;

    @FXML
    private JFXButton btnUpdateProduct;

    @FXML
    private JFXButton btnApprovisionner;

    @FXML
    private JFXTextField txtQuantiteAppro;

    @FXML
    private JFXButton btnNewAppro;

    @FXML
    private TableView<Commande> tableCommande;



    @FXML
    private TableColumn<Commande, String> clnNumComm;

    @FXML
    private TableColumn<Commande, Date> clnDateComm;

    @FXML
    private TableColumn<Commande, Integer> clnMontantComm;

    @FXML
    private TableColumn<Commande, Integer> clnAcompteComm;

    @FXML
    private TableColumn<Commande, String> clnClientCommande;

    @FXML
    private JFXButton btnNewComm;

    @FXML
    private JFXButton btnInfosComm;

    @FXML
    private JFXButton btnAnnulerComm;


    @FXML
    private JFXTextField txtSearchComm;


    @FXML
    private TableView<Detail> tableListeCommande;

    @FXML
    private TableColumn<Detail, String> clnProduitDetail;

    @FXML
    private TableColumn<Detail, Integer> clnQuantiteDetail;

    @FXML
    private TableColumn<Detail, Integer> clnPrixDetail;

    @FXML
    private TableColumn<Detail, Integer> clnTotalDetail;

    @FXML
    private JFXComboBox<Produit> cbxProduitComm;

    @FXML
    private JFXTextField txtQuantiteComm;

    @FXML
    private JFXTextField txtTotalCommande;

    @FXML
    private JFXButton btnAddCommande;

    @FXML
    private JFXButton btnDeleteDetail;

    @FXML
    private JFXTextField txtNomClient;

    @FXML
    private JFXTextField txtPrenomClient;

    @FXML
    private JFXDatePicker txtDateLivraison;

    @FXML
    private JFXTextField txtIdClient;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXDatePicker txtDateNaissance;

    @FXML
    private JFXTextField txtAdresse;

    @FXML
    private JFXComboBox<TypeClient> cbxTypeClient;

    @FXML
    private JFXComboBox<Marque> cbxMarque;

    @FXML
    private JFXTextField txtNumProd;

    @FXML
    private TableView<Commande> tableCommandeFact;

    @FXML
    private TableColumn<Commande, String> clnNumCommFact;

    @FXML
    private TableColumn<Commande, Date> clnDateCommFact;

    @FXML
    private TableColumn<Commande, Integer> clnMontantCommFact;

    @FXML
    private TableColumn<Commande, Integer> clnAcompteCommFact;

    @FXML
    private TableColumn<Commande, String> clnClientCommandeFact;

    @FXML
    private JFXButton btnSaveCommande;

    @FXML
    private JFXButton btnSaveFacture;

    @FXML
    private LineChart<String, Integer> sellChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private TableView<User> tableUser;

    @FXML
    private TableColumn<User, String> clnUserId;

    @FXML
    private TableColumn<User, String> clnUserNom;

    @FXML
    private TableColumn<User, String> clnUserPrenom;

    @FXML
    private TableColumn<User, String> clnUserAdresse;

    @FXML
    private TableColumn<User, String> clnUserProfil;

    @FXML
    private TableColumn<User, String> clnUserStatus;

    @FXML
    private JFXButton btnNewUser;

    @FXML
    private Pane newUserPane;

    @FXML
    private JFXTextField txtPrenomUser;

    @FXML
    private JFXTextField txtIdUser;

    @FXML
    private JFXTextField txtNomUser;

    @FXML
    private JFXTextField txtAdresseUser;

    @FXML
    private JFXTextField txtEmailUser;

    @FXML
    private JFXComboBox<Profil> cbxProfilUser;

    @FXML
    private JFXButton btnSaveUser;

    @FXML
    private JFXButton btnBloqueUser;

    @FXML
    private JFXButton btnParams;

    @FXML
    private Pane paramsPane;

    @FXML
    private JFXTextField txtPrenomLog;

    @FXML
    private JFXTextField txtIdLog;

    @FXML
    private JFXTextField txtNomLog;

    @FXML
    private JFXTextField txtAdresseLog;

    @FXML
    private JFXTextField txtEmailLog;

    @FXML
    private JFXTextField txtProfilLog;

    @FXML
    private JFXButton btnUpdateUser;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtConfirm;


    @FXML
    void param_loader(ActionEvent event) {
        removeMenuStyle(btnParams);
        paramsPane.toFront();
        txtPrenomLog.setText(user.getPrenom());
        txtNomLog.setText(user.getNom());
        txtIdLog.setText(user.getIdentification());
        txtAdresseLog.setText(user.getAdresse());
        txtEmailLog.setText(user.getEmail());
        txtProfilLog.setText(user.getProfil().getLibelle());
        txtPassword.setText("");
        txtConfirm.setText("");
    }

    @FXML
    void update_user(ActionEvent event) throws NoSuchAlgorithmException {
        if (!txtPassword.getText().trim().isEmpty() && txtPassword.getText().equals(txtConfirm.getText())){
            user.setPassword(hash(txtPassword.getText()));
            try {
                Fabrique.getiUser().updateUser(user);
                Utils.showMessage("", "", "Mot de passe modifié");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else
            Utils.showMessage("", "", "Les mots de passe ne correspondent pas!!!");
    }
    @FXML
    void logout_user(ActionEvent event) {
        LoginViewController login = new LoginViewController();
        login.logout(user);
        LoadView.showView("", "loginView.fxml", 1);
    }

    @FXML
    void nouvelle_user(ActionEvent event) {

        try {
            cbxProfilUser.setItems(FXCollections.observableArrayList(Fabrique.getiParametre().getProfil()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        newUserPane.toFront();
    }

    @FXML
    void save_user(ActionEvent event) throws Exception {
        if (!txtIdUser.getText().trim().isEmpty() && !txtAdresseUser.getText().trim().isEmpty() && !txtNomUser.getText().trim().isEmpty() &&
        !txtPrenomUser.getText().trim().isEmpty() && !txtEmailUser.getText().trim().isEmpty() && cbxProfilUser.getSelectionModel().getSelectedItem() != null)
        {
            User u = new User();
            u.setIdentification(txtIdUser.getText());
            u.setNom(txtNomUser.getText());
            u.setPrenom(txtPrenomUser.getText());
            u.setAdresse(txtAdresseUser.getText());
            u.setEmail(txtEmailUser.getText());
            u.setProfil(cbxProfilUser.getValue());
            u.setPassword(hash("javaProject"));

            Fabrique.getiUser().addUser(u);
            actualiserTables();
            userPane.toFront();
        }
    }


    @FXML
    void bloque_user(ActionEvent event) throws Exception {
        User u = tableUser.getSelectionModel().getSelectedItem();
        if (u.getEtat() == 0)
            Fabrique.getiUser().bloqueUser(u.getId());
        else {
            u.setEtat(0);
            Fabrique.getiUser().updateUser(u);
        }

        actualiserTables();
    }


    List<Detail> listeCommande = new ArrayList<>();
    @FXML
    void add_commande(ActionEvent event) {
        if (cbxProduitComm.getSelectionModel().getSelectedItem().getQuantite() >= Integer.parseInt(txtQuantiteComm.getText())){
            boolean trouve = false;
            int position = 0;
            for (Detail detail : listeCommande){
                if(detail.getProduit().getId() == cbxProduitComm.getSelectionModel().getSelectedItem().getId()){
                    trouve = true;
                    if ((detail.getQuantite() + Integer.parseInt(txtQuantiteComm.getText())) < cbxProduitComm.getSelectionModel().getSelectedItem().getQuantite()){
                        listeCommande.get(position).setQuantite(detail.getQuantite() + Integer.parseInt(txtQuantiteComm.getText()));
                        listeCommande.get(position).setTotal(detail.getProduit().getPrixUnitaire() * listeCommande.get(position).getQuantite());
                        tableListeCommande.getItems().clear();
                        try {
                            Fabrique.getiProduit().findProduit(cbxProduitComm.getSelectionModel().getSelectedItem().getId()).setQuantite(cbxProduitComm.getSelectionModel().getSelectedItem().getQuantite() - detail.getQuantite());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else {
                        Utils.showMessage("", "", "Quantité non disponible en stock");
                        txtQuantiteComm.setText(String.valueOf(cbxProduitComm.getSelectionModel().getSelectedItem().getQuantite()-detail.getQuantite()));

                    }
                }
                position++;
            }
            if(!trouve){
                Detail d = new Detail();
                d.setProduit(cbxProduitComm.getSelectionModel().getSelectedItem());
                d.setQuantite(Integer.parseInt(txtQuantiteComm.getText()));
                d.setTotal(d.getProduit().getPrixUnitaire() * d.getQuantite());
                listeCommande.add(d);
            }
            actualiserCommListe(listeCommande);
            int total = listeCommande.stream().filter(c -> c.getTotal() > 0).mapToInt(Detail::getTotal).sum();
            txtTotalCommande.setText(total + " CFA");

        }else {
            Utils.showMessage("", "", "Quantité non disponible en stock");
            txtQuantiteComm.setText(String.valueOf(cbxProduitComm.getSelectionModel().getSelectedItem().getQuantite()));
        }


    }

    @FXML
    void supprimer_detail(ActionEvent event) {
        if (listeCommande.size() > 0){
            listeCommande.removeIf(d -> d.getProduit().getLibelle().equals(tableListeCommande.getSelectionModel().getSelectedItem().getProduit().getLibelle()));
        }
        btnDeleteDetail.setVisible(false);
        actualiserCommListe(listeCommande);
        int total = listeCommande.stream().filter(c -> c.getTotal() > 0).mapToInt(Detail::getTotal).sum();
        txtTotalCommande.setText(String.valueOf(total));
    }

    @FXML
    void add_stock(ActionEvent event) {
        if(produit != null && !txtQuantiteAppro.getText().trim().equals("")){
            produit.setQuantite(produit.getQuantite() + Integer.parseInt(txtQuantiteAppro.getText()));
            try {
                Fabrique.getiProduit().updateProduit(produit);
                disableButtons();
                Stock st = new Stock();
                st.setType("Entree");
                st.setQuantite(Integer.parseInt(txtQuantiteAppro.getText()));
                st.setDate(asDate(LocalDate.now()));
                st.setProduit(produit);
                st.setUser(user);
                Fabrique.getiStock().addEntree(st);
                actualiserTables();
                txtQuantiteAppro.setText("");
                productPane.toFront();
                produit = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    Commande commande;
    @FXML
    void annuler_commande(ActionEvent event) {
        commande = tableCommande.getSelectionModel().getSelectedItem();
        try {
            Fabrique.getiCommande().bloqueCommande(commande.getId());
            List<Detail> details = Fabrique.getiDetail().getByCommande(commande);
            for (Detail d: details) {
                Produit p = Fabrique.getiProduit().findProduit(d.getProduit().getId());
                p.setQuantite(p.getQuantite() + d.getQuantite());
                Fabrique.getiProduit().updateProduit(p);
            }
            actualiserTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void appro_produit(ActionEvent event) throws Exception {
        cbxCategorie.setItems(FXCollections.observableArrayList(Fabrique.getiParametre().getAllCategorie()));
        cbxMarque.setItems(FXCollections.observableArrayList(Fabrique.getiParametre().getAllMarque()));
        produit = produitTable.getSelectionModel().getSelectedItem();
        txtQuantiteSeuil.setText(String.valueOf(produit.getSeuil()));
        txtNumProd.setText(produit.getNumSerie());
        txtNumProd.setDisable(true);
        txtLibelleProduit.setText(produit.getLibelle());
        txtQuantite.setText(String.valueOf(produit.getQuantite()));
        txtPrixProduit.setText(String.valueOf(produit.getPrixUnitaire()));
        txtMarge.setText(String.valueOf(produit.getMarge()));
        txtMarge.setDisable(true);
        txtPrixProduit.setDisable(true);
        txtQuantite.setDisable(true);
        txtLibelleProduit.setDisable(true);
        txtQuantiteSeuil.setDisable(true);
        txtQuantiteAppro.setVisible(true);
        for (Categorie c: cbxCategorie.getItems()) {
            if (c.getId() == produit.getCategorie().getId())
                cbxCategorie.getSelectionModel().select(c);
        }
        for (Marque m: cbxMarque.getItems()) {
            if (m.getId() == produit.getMarque().getId())
                cbxMarque.getSelectionModel().select(m);
        }
        cbxCategorie.setDisable(true);
        cbxMarque.getSelectionModel().select(produit.getMarge());
        cbxMarque.setDisable(true);
        disableButtons();
        btnAjouterProduit.setVisible(false);
        btnApprovisionner.setVisible(true);
        btnUpdateProduct.setVisible(false);
        newProductPane.toFront();
    }

    @FXML
    void save_produit(ActionEvent event) {
        Produit p = new Produit();
        p.setLibelle(txtLibelleProduit.getText());
        p.setPrixUnitaire(Integer.parseInt(txtPrixProduit.getText()));
        p.setQuantite(Integer.parseInt(txtQuantite.getText()));
        p.setMarge(Math.min(Integer.parseInt(txtMarge.getText()), 30));
        p.setSeuil(Math.max(Integer.parseInt(txtQuantiteSeuil.getText()), 5));
        p.setNumSerie(txtNumProd.getText());
        p.setMarque(cbxMarque.getSelectionModel().getSelectedItem());
        p.setCategorie(cbxCategorie.getSelectionModel().getSelectedItem());
        try {
            Fabrique.getiProduit().addProduit(p);
            p = Fabrique.getiProduit().findProduit(p.getLibelle());
            if (Integer.parseInt(txtQuantite.getText()) > 0){
                Stock s = new Stock();
                s.setProduit(p);
                s.setDate(asDate(LocalDate.now()));
                s.setQuantite(Integer.parseInt(txtQuantite.getText()));
                s.setType("Entree");
                Fabrique.getiStock().addEntree(s);
            }
            actualiserTables();
            disableButtons();
            productPane.toFront();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Client client;
    @FXML
    void save_commande(ActionEvent event) throws Exception {

        if (!txtIdClient.getText().trim().isEmpty() && !txtNomClient.getText().trim().isEmpty()
                && txtDateNaissance.getValue() != null &&
                !txtAdresse.getText().trim().equals("") && !txtEmail.getText().trim().equals("") && listeCommande.size() > 0
                && cbxTypeClient.getSelectionModel().getSelectedIndex() != -1
        ){
            boolean verif = true;
            if (client == null) {
                client = new Client();
                client.setIdentification(txtIdClient.getText());
                client.setNom(txtNomClient.getText());
                if (cbxTypeClient.getSelectionModel().getSelectedItem().getLibelle().equals("Particulier") && !txtPrenomClient.getText().trim().isEmpty())
                    client.setPrenom(txtPrenomClient.getText());
                else
                    client.setPrenom("");
                client.setAdresse(txtAdresse.getText());
                client.setEmail(txtEmail.getText());
                client.setDateNaissance(asDate(txtDateNaissance.getValue()));
                client.setType(cbxTypeClient.getSelectionModel().getSelectedItem());
                Fabrique.getiClient().addClient(client);
                client = Fabrique.getiClient().findByMatClient(txtIdClient.getText());
            }
            Commande commande = new Commande();
            commande.setDetails(listeCommande);
            DateTimeFormatter df = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            String num = "C-"+ LocalDateTime.now().format(df);
            commande.setNumero(num);
            commande.setClient(client);
            commande.setEtat(0);
            commande.setDateCommande(asDate(LocalDate.now()));
            if (txtDateLivraison.getValue() != null)
                commande.setDateLivraison(asDate(txtDateLivraison.getValue()));
            else
                commande.setDateLivraison(asDate(LocalDate.now()));

            commande.setMontant(Integer.parseInt(txtTotalCommande.getText()));

            if (client.getType().getLibelle().equals("Particulier"))
                commande.setAcompte(0);
            else
                commande.setAcompte(commande.getMontant()*30/100);

            commande.setUser(user);
            Fabrique.getiCommande().addCommande(commande);
            Commande c = Fabrique.getiCommande().findByNumber(num);
            generate_bon(c);
            for (Detail d: listeCommande) {
                d.setCommande(c);
                Fabrique.getiDetail().addDetail(d);
                //d.getProduit().setQuantite(d.getProduit().getQuantite() - d.getQuantite());
                Fabrique.getiProduit().updateProduit(d.getProduit());
                    Stock stock = new Stock();
                    stock.setType("Sortie");
                    stock.setDate(asDate(LocalDate.now()));
                    stock.setProduit(d.getProduit());
                    stock.setQuantite(d.getQuantite());
                    Fabrique.getiStock().addEntree(stock);
            }
            actualiserTables();
            commandePane.toFront();
            listeCommande.clear();
            txtIdClient.setText("");
            txtPrenomClient.setText("");
            txtNomClient.setText("");
            txtEmail.setText("");
            txtDateNaissance.setValue(null);
        }else{
            Utils.showMessage("", "", "Veuiller remplir tout les champs et choisir des produits");
        }
    }

    @FXML
    void facture(ActionEvent event) {
        facturePane.toFront();
        removeMenuStyle(btnFacture);
    }

    @FXML
    void new_facture(ActionEvent event) throws Exception {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String num = "F-"+ LocalDateTime.now().format(df);
        Commande c = tableCommandeFact.getSelectionModel().getSelectedItem();
        Facture facture = new Facture();
        facture.setCommande(c);
        facture.setClient(c.getClient());
        facture.setDate(asDate(LocalDate.now()));
        facture.setNumero(num);
        facture.setUser(user);
        Fabrique.getiFacture().addFacture(facture);
        generate_facture(facture);
        if (c.getClient().getType().getLibelle().equals("Particulier"))
            c.setEtat(2);
        else {
            if (c.getClient().getType().getLibelle().equals("Entreprise") && c.getEtat() == 0){
                c.setEtat(1);
            }else
                c.setEtat(2);
        }
        for (Detail d: listeCommande) {
            d.getProduit().setQuantite(d.getProduit().getQuantite() - d.getQuantite());
            Fabrique.getiProduit().updateProduit(d.getProduit());
        }
        Fabrique.getiCommande().updateCommande(c);
    }

    @FXML
    void home(ActionEvent event) {
        homePane.toFront();
        removeMenuStyle(btnHome);
    }

    @FXML
    void order(ActionEvent event) {
        commandePane.toFront();
        removeMenuStyle(btnCommande);

    }

    @FXML
    void product(ActionEvent event) {
        productPane.toFront();
        removeMenuStyle(btnProduit);
        disableButtons();
    }


    @FXML
    void users(ActionEvent event) {
        try {
            actualiserTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userPane.toFront();
        removeMenuStyle(btnUser);
    }

    @FXML
    void new_commande(ActionEvent event) {
        try {
            cbxProduitComm.setItems(FXCollections.observableArrayList(Fabrique.getiProduit().getAll()));
            cbxTypeClient.setItems(FXCollections.observableArrayList(Fabrique.getiParametre().getAllType()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        newCommPane.toFront();
    }

    @FXML
    void infos_comm(ActionEvent event) throws Exception {
        Commande c = tableCommande.getSelectionModel().getSelectedItem();
        cbxTypeClient.setItems(FXCollections.observableArrayList(Fabrique.getiParametre().getAllType()));
        txtIdClient.setText(c.getClient().getIdentification());
        txtNomClient.setText(c.getClient().getNom());
        if (c.getClient().getType().getLibelle().equals("Particulier")) {
            txtPrenomClient.setDisable(false);
            txtPrenomClient.setText(c.getClient().getPrenom());
        }else
            txtPrenomClient.setDisable(true);
        txtDateNaissance.setValue(asLocalDate(c.getClient().getDateNaissance()));
        txtEmail.setText(c.getClient().getEmail());
        txtAdresse.setText(c.getClient().getAdresse());
        for (TypeClient t: cbxTypeClient.getItems()) {
            if (c.getClient().getType().getId() == t.getId()){
                cbxTypeClient.getSelectionModel().select(t);
                break;
            }
        }
        cbxTypeClient.setDisable(false);
        actualiserCommListe(c.getDetails());
        newCommPane.toFront();
    }


    @FXML
    public void new_produit(ActionEvent actionEvent) {
        cbxCategorie.setDisable(false);
        cbxMarque.setDisable(false);
        try {
            cbxCategorie.setItems(FXCollections.observableArrayList(Fabrique.getiParametre().getAllCategorie()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        newProductPane.toFront();
    }

    Produit produit;
    @FXML
    public void update_produit(ActionEvent actionEvent) throws Exception {
        cbxCategorie.setItems(FXCollections.observableArrayList(Fabrique.getiParametre().getAllCategorie()));
        cbxMarque.setItems(FXCollections.observableArrayList(Fabrique.getiParametre().getAllMarque()));
        produit = produitTable.getSelectionModel().getSelectedItem();
        txtQuantiteSeuil.setText(String.valueOf(produit.getSeuil()));
        txtNumProd.setText(produit.getNumSerie());
        txtLibelleProduit.setText(produit.getLibelle());
        txtQuantite.setText(String.valueOf(produit.getQuantite()));
        txtQuantite.setDisable(true);
        txtPrixProduit.setText(String.valueOf(produit.getPrixUnitaire()));
        txtMarge.setText(String.valueOf(produit.getMarge()));
        for (Categorie c: cbxCategorie.getItems()) {
            if (c.getId() == produit.getCategorie().getId())
                cbxCategorie.getSelectionModel().select(c);
        }
        for (Marque m: cbxMarque.getItems()) {
            if (m.getId() == produit.getMarque().getId())
                cbxMarque.getSelectionModel().select(m);
        }
        disableButtons();
        btnAjouterProduit.setVisible(false);
        btnApprovisionner.setVisible(false);
        btnUpdateProduct.setVisible(true);
        newProductPane.toFront();
    }

    @FXML
    void modifier_produit(ActionEvent event) {
        if (produit != null){
            produit.setLibelle(txtLibelleProduit.getText());
            produit.setPrixUnitaire(Integer.parseInt(txtPrixProduit.getText()));
            produit.setQuantite(Integer.parseInt(txtQuantite.getText()));
            produit.setMarge(Math.min(Integer.parseInt(txtMarge.getText()), 30));
            produit.setSeuil(Math.max(Integer.parseInt(txtQuantiteSeuil.getText()), 5));
            try {
                Fabrique.getiProduit().updateProduit(produit);
                actualiserTables();
                disableButtons();
                actualiserTables();
                productPane.toFront();
                produit = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void delete_produit(ActionEvent actionEvent) {
        produit = produitTable.getSelectionModel().getSelectedItem();
        if (produit.getQuantite() > 0){
            Utils.showMessage("Erreur", "", "La quantite de ce produit est superieur à 0");
        }else {
            boolean b = false;
            ButtonType buttonType = Utils.showConfirmationMessage("Attention", "Voulez-vous vraiment le supprimer ?");
            if(buttonType == ButtonType.OK){
                b = true;
                produit.setEtat(1);
            }
            try {
                if (b) {
                    Fabrique.getiProduit().updateProduit(produit);
                    actualiserTables();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private ObservableList<String> monthNames = FXCollections.observableArrayList();
    static User user;
    @FXML
    void initialize() throws Exception {

        homePane.toFront();
        lblName.setText(user.getPrenom() + " " + user.getNom());
        try {
            actualiserTables();
        } catch (Exception e) {
            e.printStackTrace();
        }

        produitTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                activateButtons();
            }
        });

        tableUser.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                if (newSelection.getEtat() == 0)
                    btnBloqueUser.setText("Bloquer");
                else
                    btnBloqueUser.setText("Debloquer");
                btnBloqueUser.setDisable(false);
            }
        });

        tableCommande.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if (newSelection != null) {
                if (newSelection.getClient().getType().getLibelle().equals("Entreprise"))
                    btnAnnulerComm.setDisable(false);
                else
                    btnAnnulerComm.setDisable(true);

                btnInfosComm.setDisable(false);
                //btnBon.setDisable(false);
            }
        });

        tableListeCommande.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnDeleteDetail.setVisible(true);
            }
        });

        txtIdClient.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue){
                if (!txtIdClient.getText().trim().isEmpty())
                {
                    try {
                        client = Fabrique.getiClient().findByMatClient(txtIdClient.getText());
                        if (client == null){
                            txtNomClient.setEditable(true);
                            txtPrenomClient.setEditable(true);
                            txtAdresse.setEditable(true);
                            txtDateNaissance.setEditable(true);
                            txtEmail.setEditable(true);
                            cbxTypeClient.setDisable(false);
                        }else {
                            txtNomClient.setEditable(false);
                            txtNomClient.setText(client.getNom());
                            txtPrenomClient.setEditable(false);
                            txtPrenomClient.setText(client.getPrenom());
                            txtAdresse.setEditable(false);
                            txtAdresse.setText(client.getAdresse());
                            txtDateNaissance.setEditable(false);
                            txtDateNaissance.setValue(asLocalDate(client.getDateNaissance()));
                            txtEmail.setEditable(false);
                            txtEmail.setText(client.getEmail());
                            cbxTypeClient.setDisable(true);
                            cbxTypeClient.getSelectionModel().select(client.getType().getId()-1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        txtSearchComm.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
            if (oldValue != null && (newValue.length() < oldValue.length())){
                try {
                    tableCommande.setItems(FXCollections.observableArrayList(Fabrique.getiCommande().getAll()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String value = newValue.toLowerCase();
            ObservableList<Commande> filtered = FXCollections.observableArrayList();
            long count = tableCommande.getColumns().size();
            for (int i = 0; i < tableCommande.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + tableCommande.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)){
                        filtered.add(tableCommande.getItems().get(i));
                        break;
                    }
                }
            }
            tableCommande.setItems(filtered);
        });

        cbxTypeClient.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue)->{
            if (newValue.getLibelle().equals("Entreprise")){
                txtDateLivraison.setVisible(true);
                txtPrenomClient.setDisable(true);
            }else {
                txtDateLivraison.setVisible(false);
                txtPrenomClient.setDisable(false);
            }
        });

        cbxCategorie.getSelectionModel().selectedItemProperty().addListener((option, oldValue, newValue)->{
            if (newValue != oldValue){
                try {
                    cbxMarque.setItems(FXCollections.observableArrayList(newValue.getMarques()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // force the field to be numeric only
        forceInputNumber(txtMarge);
        forceInputNumber(txtPrixProduit);
        forceInputNumber(txtQuantite);
        forceInputNumber(txtQuantiteAppro);
        forceInputNumber(txtQuantiteSeuil);
        forceInputNumber(txtQuantiteComm);
        forceInputNumber(txtIdClient);
        forceInputNumber(txtNumProd);

        // Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.FRENCH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));

        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);

        setData(Fabrique.getiCommande().getAll());

        if (user.getProfil().getLibelle().equals("Admin")){
            btnProduit.setVisible(true);
            btnCommande.setVisible(true);
            btnFacture.setVisible(true);
            btnUser.setVisible(true);
        }

        if (user.getProfil().getLibelle().equals("Gestionnaire de Stock")){
            btnProduit.setVisible(true);
            btnCommande.setVisible(false);
            btnFacture.setVisible(false);
            btnUser.setVisible(false);
        }
        if (user.getProfil().getLibelle().equals("Gestionnaire des ventes")){
            btnProduit.setVisible(false);
            btnCommande.setVisible(true);
            btnFacture.setVisible(false);
            btnUser.setVisible(false);
        }
        if (user.getProfil().getLibelle().equals("Caissier")){
            btnProduit.setVisible(false);
            btnCommande.setVisible(false);
            btnFacture.setVisible(true);
            btnUser.setVisible(false);
        }
    }

    public void setData(List<Commande> commandes) {
        // Count the number of people having their birthday in a specific month.
        int[] monthCounter = new int[12];
        for (Commande c : commandes) {
            int month = asLocalDate(c.getDateCommande()).getMonthValue() - 1;
            monthCounter[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }

        sellChart.getData().add(series);

    }

    private void actualiserTables() throws Exception {
        clnCategorieProd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCategorie().getLibelle()));
        clnLibelleProduit.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getLibelle()));
        clnPrixProduit.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPrixUnitaire()));
        clnQuantiteProduit.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getQuantite()));

        produitTable.setItems(FXCollections.observableArrayList(Fabrique.getiProduit().getAll()));


        clnNumComm.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getNumero()));
        clnDateComm.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDateLivraison()));
        clnAcompteComm.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAcompte()));
        clnMontantComm.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getMontant()));
        clnClientCommande.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getClient().getPrenom() + " " + cellData.getValue().getClient().getNom()));

        tableCommande.setItems(FXCollections.observableArrayList(Fabrique.getiCommande().getAll()));

        clnNumCommFact.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getNumero()));
        clnDateCommFact.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDateLivraison()));
        clnAcompteCommFact.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAcompte()));
        clnMontantCommFact.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getMontant()));
        clnClientCommandeFact.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getClient().getPrenom() + " " + cellData.getValue().getClient().getNom()));

        tableCommandeFact.setItems(FXCollections.observableArrayList(Fabrique.getiCommande().getAll()));

        clnUserId.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getIdentification()));
        clnUserNom.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getNom()));
        clnUserPrenom.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPrenom()));
        clnUserAdresse.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAdresse()));
        clnUserProfil.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProfil().getLibelle()));
        clnUserStatus.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(userStatus(cellData.getValue())));

        tableUser.setItems(FXCollections.observableArrayList(Fabrique.getiUser().getAll()));
    }

    private void removeMenuStyle(Button b){
        btnHome.setStyle(null);
        btnHome.textFillProperty().setValue(Color.valueOf("BLACK"));
        btnCommande.setStyle(null);
        btnCommande.textFillProperty().setValue(Color.valueOf("BLACK"));
        btnFacture.setStyle(null);
        btnFacture.textFillProperty().setValue(Color.valueOf("BLACK"));
        btnProduit.setStyle(null);
        btnProduit.textFillProperty().setValue(Color.valueOf("BLACK"));
        btnUser.setStyle(null);
        btnUser.textFillProperty().setValue(Color.valueOf("BLACK"));
        activeMenu(b);
    }

    private void activeMenu(Button b){
        b.setStyle("-fx-background-color: #4a148c;");
        b.textFillProperty().setValue(Color.valueOf("WHITE"));
    }

    private void disableButtons(){
        btnDeleteProduit.setDisable(true);
        btnUpdateProduit.setDisable(true);
        btnNewAppro.setDisable(true);
        btnInfosComm.setDisable(true);
        btnAnnulerComm.setDisable(true);
    }

    private void activateButtons(){
        btnDeleteProduit.setDisable(false);
        btnUpdateProduit.setDisable(false);
        btnNewAppro.setDisable(false);
    }

    private void forceInputNumber(JFXTextField txt){
        txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    txt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    private void actualiserCommListe(List<Detail> liste){
        clnProduitDetail.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProduit().getLibelle()));
        clnQuantiteDetail.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getQuantite()));
        clnPrixDetail.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProduit().getPrixUnitaire()));
        clnTotalDetail.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getTotal()));

        tableListeCommande.setItems(FXCollections.observableArrayList(liste));

        try {
            cbxProduitComm.setItems(FXCollections.observableArrayList(Fabrique.getiProduit().getAll()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtQuantiteComm.setText("");
    }

    private static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static void generate_bon(Commande c){
        Document document = new Document();
        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Documents/BonDeCommande/"+c.getNumero()+".pdf"));
            document.open();
            //String imageUrl = "https://freelogo-assets.s3.amazonaws.com/sites/all/themes/freelogoservices/images/lps/lp1/l12_FR.png";
            String imageUrl = "/home/marshall/Documents/JavaProject/commClient/Documents/logo.png";
            Image img = Image.getInstance(imageUrl);
            img.setAbsolutePosition(0, PageSize.A4.getHeight() - img.getScaledHeight());
            document.add(img);
            Paragraph p = new Paragraph(LocalDate.now().format(DateTimeFormatter.ofPattern("dd - MM - yyyy")), new Font(Font.FontFamily.TIMES_ROMAN,16f));
            p.setAlignment(2);
            document.add(p);
            Paragraph p1 = new Paragraph("\nBon de Commande\n\n", new Font(Font.FontFamily.TIMES_ROMAN,24f,Font.BOLD));
            p1.setAlignment(1);
            document.add(p1);
            Paragraph pr = new Paragraph("Destinataire :", new Font(Font.FontFamily.TIMES_ROMAN,14f));
            pr.setTabSettings(new TabSettings(350f));
            pr.add(Chunk.TABBING);
            pr.add(c.getClient().toString());
            document.add(pr);
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            float[] widths = {3f, 1f, 1f, 1f};
            table.setWidths(widths);

            PdfPCell cell = new PdfPCell(new Paragraph("Designation", new Font(Font.FontFamily.TIMES_ROMAN,14f, Font.BOLD)));
            PdfPCell cell1 = new PdfPCell(new Paragraph("Quantité", new Font(Font.FontFamily.TIMES_ROMAN,14f, Font.BOLD)));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Prix Unitaire", new Font(Font.FontFamily.TIMES_ROMAN,14f, Font.BOLD)));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Total", new Font(Font.FontFamily.TIMES_ROMAN,14f, Font.BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorderWidth(2);
            cell1.setBorderWidth(2);
            cell2.setBorderWidth(2);
            cell3.setBorderWidth(2);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            for (Detail detail : c.getDetails()){
                PdfPCell c1 = new PdfPCell(new Paragraph(detail.getProduit().getLibelle(), new Font(Font.FontFamily.TIMES_ROMAN,14f)));
                PdfPCell c2 = new PdfPCell(new Paragraph(String.valueOf(detail.getQuantite()), new Font(Font.FontFamily.TIMES_ROMAN,14f)));
                PdfPCell c3 = new PdfPCell(new Paragraph(String.valueOf(detail.getProduit().getPrixUnitaire()), new Font(Font.FontFamily.TIMES_ROMAN,14f)));
                PdfPCell c4 = new PdfPCell(new Paragraph(String.valueOf(detail.getTotal()), new Font(Font.FontFamily.TIMES_ROMAN,14f)));
                c1.setBorderWidthBottom(0);
                c2.setBorderWidthBottom(0);
                c4.setBorderWidthBottom(0);
                c3.setBorderWidthBottom(0);
                c1.setBorderWidthLeft(2);
                table.addCell(c1);
                table.addCell(c2);
                table.addCell(c3);
                table.addCell(c4);
            }
            document.add(table);

            Paragraph foot = new Paragraph("Total :", new Font(Font.FontFamily.TIMES_ROMAN,16f, Font.BOLD));
            foot.setTabSettings(new TabSettings(350f));
            foot.add(Chunk.TABBING);
            foot.add(String.valueOf(c.getMontant()));

            foot.add(" CFA\n\n");
            Font ft = new Font(Font.FontFamily.TIMES_ROMAN, 14f, Font.UNDERLINE);
            ft.setStyle(Font.BOLDITALIC);
            Paragraph sign = new Paragraph("Le Vendeur", ft);
            sign.setTabSettings(new TabSettings(380f));
            sign.add(Chunk.TABBING);
            sign.add("Le Client");
            ColumnText ct = new ColumnText(writer.getDirectContent());
            ct.setSimpleColumn(50f,0,500,200);
            ct.addElement(foot);
            ct.addElement(sign);
            ct.go();
            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void generate_facture(Facture f){
        Document document = new Document();
        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Documents/Factures/"+f.getNumero()+".pdf"));
            document.open();
            String imageUrl = "https://freelogo-assets.s3.amazonaws.com/sites/all/themes/freelogoservices/images/lps/lp1/l12_FR.png";
            Image img = Image.getInstance(imageUrl);
            img.setAbsolutePosition(0, PageSize.A4.getHeight() - img.getScaledHeight());
            document.add(img);
            Paragraph p = new Paragraph(LocalDate.now().format(DateTimeFormatter.ofPattern("dd - MM - yyyy")), new Font(Font.FontFamily.TIMES_ROMAN,16f));
            p.setAlignment(2);
            document.add(p);
            Paragraph p1 = new Paragraph("\nFacture\n\n", new Font(Font.FontFamily.TIMES_ROMAN,24f,Font.BOLD));
            p1.setAlignment(1);
            document.add(p1);
            Paragraph pr = new Paragraph("Destinataire :", new Font(Font.FontFamily.TIMES_ROMAN,14f));
            pr.setTabSettings(new TabSettings(350f));
            pr.add(Chunk.TABBING);
            pr.add(f.getCommande().getClient().toString());
            document.add(pr);
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            float[] widths = {3f, 1f, 1f, 1f};
            table.setWidths(widths);

            PdfPCell cell = new PdfPCell(new Paragraph("Designation", new Font(Font.FontFamily.TIMES_ROMAN,14f, Font.BOLD)));
            PdfPCell cell1 = new PdfPCell(new Paragraph("Quantité", new Font(Font.FontFamily.TIMES_ROMAN,14f, Font.BOLD)));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Prix Unitaire", new Font(Font.FontFamily.TIMES_ROMAN,14f, Font.BOLD)));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Total", new Font(Font.FontFamily.TIMES_ROMAN,14f, Font.BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorderWidth(2);
            cell1.setBorderWidth(2);
            cell2.setBorderWidth(2);
            cell3.setBorderWidth(2);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            for (Detail detail : f.getCommande().getDetails()){
                PdfPCell c1 = new PdfPCell(new Paragraph(detail.getProduit().getLibelle(), new Font(Font.FontFamily.TIMES_ROMAN,14f)));
                PdfPCell c2 = new PdfPCell(new Paragraph(String.valueOf(detail.getQuantite()), new Font(Font.FontFamily.TIMES_ROMAN,14f)));
                PdfPCell c3 = new PdfPCell(new Paragraph(String.valueOf(detail.getProduit().getPrixUnitaire()), new Font(Font.FontFamily.TIMES_ROMAN,14f)));
                PdfPCell c4 = new PdfPCell(new Paragraph(String.valueOf(detail.getTotal()), new Font(Font.FontFamily.TIMES_ROMAN,14f)));
                c1.setBorderWidthBottom(0);
                c2.setBorderWidthBottom(0);
                c4.setBorderWidthBottom(0);
                c3.setBorderWidthBottom(0);
                c1.setBorderWidthLeft(2);
                table.addCell(c1);
                table.addCell(c2);
                table.addCell(c3);
                table.addCell(c4);
            }
            document.add(table);

            Paragraph foot = new Paragraph("Total :", new Font(Font.FontFamily.TIMES_ROMAN,16f, Font.BOLD));
            foot.setTabSettings(new TabSettings(350f));
            foot.add(Chunk.TABBING);
            foot.add(String.valueOf(f.getCommande().getMontant()));

            foot.add(" CFA\n\n");

            Font ft = new Font(Font.FontFamily.TIMES_ROMAN, 14f, Font.UNDERLINE);
            ft.setStyle(Font.BOLDITALIC);
            Paragraph sign = new Paragraph("Le Vendeur", ft);
            sign.setTabSettings(new TabSettings(380f));
            sign.add(Chunk.TABBING);
            sign.add("Le Client");
            ColumnText ct = new ColumnText(writer.getDirectContent());
            ct.setSimpleColumn(50f,0,500,200);
            ct.addElement(foot);
            if (f.getClient().getType().getLibelle().equals("Entreprise") && f.getCommande().getEtat() == 0)
            {
                Paragraph foot1 = new Paragraph("Montant Perçu :", new Font(Font.FontFamily.TIMES_ROMAN,16f, Font.BOLD));
                foot1.setTabSettings(new TabSettings(350f));
                foot1.add(Chunk.TABBING);
                foot1.add(String.valueOf(f.getCommande().getAcompte()));
                foot1.add(" CFA\n\n");
                ct.addElement(foot1);
            }else if (f.getClient().getType().getLibelle().equals("Entreprise") && f.getCommande().getEtat() == 1){
                Paragraph foot1 = new Paragraph("Montant Perçu :", new Font(Font.FontFamily.TIMES_ROMAN,16f, Font.BOLD));
                foot1.setTabSettings(new TabSettings(350f));
                foot1.add(Chunk.TABBING);
                foot1.add(String.valueOf(f.getCommande().getMontant() - f.getCommande().getAcompte()));
                foot1.add(" CFA\n\n");
                ct.addElement(foot1);
            }
            ct.addElement(sign);
            ct.go();
            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String hash(String pwd) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("MD5");
        byte[] message = mDigest.digest(pwd.getBytes());
        BigInteger number = new BigInteger(1, message);
        String hashText = number.toString(16);
        while (hashText.length() < 32) {
            hashText = "0" + hashText;
        }
        return hashText;
    }

    public static String userStatus(User u){
        if (u.getEtat() == 0)
            return "Actif";
        else
            return "Bloquer";
    }

    public void getUser(User u){
        user = u;
    }
}