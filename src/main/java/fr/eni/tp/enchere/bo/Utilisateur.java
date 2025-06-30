package fr.eni.tp.enchere.bo;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    private int noUtilisateur;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String rue;
    private String codePostal;
    private String ville;
    private String motDePasse;
    private int credit;
    private boolean administrateur;
    private List<ArticleVendu> LstArticle = new ArrayList<>();
    private List<Enchere> LstEnchere = new ArrayList<>();


}
