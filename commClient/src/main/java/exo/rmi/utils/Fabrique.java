package exo.rmi.utils;



import commande.service.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Fabrique {
    private static IClient iClient;
    private static ICommande iCommande;
    private static IDetail iDetail ;
    private static IFacture iFacture ;
    private static IProduit iProduit;
    private static IStock iStock;
    private static IUser iUser ;
    private static IParametre iParametre;

    private static void init() throws Exception{
        try {
            Registry registre = LocateRegistry.getRegistry(8695);
            iClient = (IClient) registre.lookup("clientRemote");
            iCommande = (ICommande) registre.lookup("commandeRemote");
            iDetail = (IDetail) registre.lookup("detailRemote");
            iFacture = (IFacture) registre.lookup("factureRemote");
            iProduit = (IProduit) registre.lookup("produitRemote");
            iStock = (IStock) registre.lookup("stockRemote");
            iUser = (IUser) registre.lookup("userRemote");
            iParametre = (IParametre) registre.lookup("parametreRemote");
        }catch (Exception e){
            throw e;
        }
    }

    public static IClient getiClient() throws Exception{
        try {
            if(iClient == null){
                init();
            }
            return iClient;
        }catch (Exception e){
            throw e;
        }
    }

    public static IDetail getiDetail() throws Exception{
        try {
            if(iDetail == null){
                init();
            }
            return iDetail;
        }catch (Exception e){
            throw e;
        }
    }

    public static ICommande getiCommande() throws Exception{
        try {
            if(iCommande == null){
                init();
            }
            return iCommande;
        }catch (Exception e){
            throw e;
        }
    }

    public static IFacture getiFacture() throws Exception{
        try {
            if(iFacture == null){
                init();
            }
            return iFacture;
        }catch (Exception e){
            throw e;
        }
    }

    public static IProduit getiProduit() throws Exception{
        try {
            if(iProduit == null){
                init();
            }
            return iProduit;
        }catch (Exception e){
            throw e;
        }
    }

    public static IStock getiStock() throws Exception{
        try {
            if(iStock == null){
                init();
            }
            return iStock;
        }catch (Exception e){
            throw e;
        }
    }

    public static IUser getiUser() throws Exception{
        try {
            if(iUser == null){
                init();
            }
            return iUser;
        }catch (Exception e){
            throw e;
        }
    }

    public static IParametre getiParametre() throws Exception{
        try {
            if(iParametre == null){
                init();
            }
            return iParametre;
        }catch (Exception e){
            throw e;
        }
    }
}
