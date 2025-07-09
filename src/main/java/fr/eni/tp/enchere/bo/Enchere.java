package fr.eni.tp.enchere.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Enchere {
    private int noEnchere;
    private Date dateEnchere;
    private int montantEnchere;
    private Utilisateur utilisateur;
    private ArticleVendu articleVendu;

    public Enchere() {
    }

    public Enchere(Utilisateur utilisateur, Date dateEnchere, int montantEnchere, ArticleVendu articleVendu) {
        this.utilisateur = utilisateur;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.articleVendu = articleVendu;
    }

    public Enchere(int noEnchere, Date dateEnchere, int montantEnchere, Utilisateur utilisateur, ArticleVendu articleVendu) {
        this.noEnchere = noEnchere;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.utilisateur = utilisateur;
        this.articleVendu = articleVendu;
    }

    public int getNoEnchere() {
        return noEnchere;
    }

    public void setNoEnchere(int noEnchere) {
        this.noEnchere = noEnchere;
    }

    public Date getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(Date dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontantEnchere() {
        return montantEnchere;
    }

    public void setMontantEnchere(int montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

    public ArticleVendu getArticleVendu() {
        return articleVendu;
    }

    public void setArticleVendu(ArticleVendu articleVendu) {
        this.articleVendu = articleVendu;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
