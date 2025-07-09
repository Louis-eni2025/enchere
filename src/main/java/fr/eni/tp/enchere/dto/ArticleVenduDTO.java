package fr.eni.tp.enchere.dto;

import fr.eni.tp.enchere.bo.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleVenduDTO {
    private int noArticle;
    private String nomArticle;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebutEnchere;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFinEnchere;

    private int miseAPrix;
    private int prixVente;
    private String etatVente;
    private List<Enchere> LstEnchere = new ArrayList<>();
    private Retrait retrait;

    private Categorie categorie;
    private Utilisateur createur;

    private boolean isEnchereEnded;
    private boolean isEnchereStarted;
    private Utilisateur utilisateurGagnant;

    private ArticleVenduDTO() {}

    public ArticleVenduDTO createArticleVendu(ArticleVendu article){
        this.noArticle = article.getNoArticle();
        this.nomArticle = article.getNomArticle();
        this.description = article.getDescription();
        this.dateDebutEnchere = article.getDateDebutEnchere();
        this.dateFinEnchere = article.getDateFinEnchere();
        this.miseAPrix = article.getMiseAPrix();
        this.prixVente = article.getPrixVente();
        this.etatVente = article.getEtatVente();
        this.LstEnchere = article.getLstEnchere();
        this.retrait = article.getRetrait();
        this.categorie = article.getCategorie();
        this.createur = article.getUtilisateur();

        //ENCHERE DTO

        return this;
    }
}
