package gesCommande;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import commande.service.*;
import gesCommande.services.*;

public class App 
{
    public static void main( String[] args )
    {
        System.setSecurityManager(new SecurityManager());
        try {
            Registry registre = LocateRegistry.createRegistry(8695);
            IClient iClient = new ClientDao();
            ICommande iCommande = new CommandeDao();
            IDetail iDetail = new DetailDao();
            IFacture iFacture = new FactureDao();
            IProduit iProduit = new ProduitDao();
            IParametre iParametre = new ParametreDao();
            IStock iStock = new StockDao();
            IUser iUser = new UserDao();
            registre.bind("clientRemote", iClient);
            registre.bind("commandeRemote", iCommande);
            registre.bind("detailRemote", iDetail);
            registre.bind("factureRemote", iFacture);
            registre.bind("produitRemote", iProduit);
            registre.bind("stockRemote", iStock);
            registre.bind("userRemote", iUser);
            registre.bind("parametreRemote", iParametre);
            System.out.println("\nServer Launch successfully at port 8695\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
