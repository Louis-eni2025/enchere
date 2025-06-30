package fr.eni.tp.enchere.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleVendu {
    private int noArticle;
    private String nomArticle;
    private String description;
    private Date dateDebutEnchere;
    private Date dateFinEnchere;
    private int miseAPrix;
    private int prixVente;
    private String etatVente;
    private List<Enchere> LstEnchere = new ArrayList<>();
    private Retrait retrait;
    private Categorie categorie;
    private Utilisateur utilisateur;

}
