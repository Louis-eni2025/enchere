package fr.eni.tp.enchere.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Enchere {
    private int noEnchere;
    private Date dateEnchere;
    private int montantEnchere;
    private List<Utilisateur> LstUtilisateur = new ArrayList<>();
    private ArticleVendu articleVendu;

    public Enchere() {
    }

    public Enchere(Date dateEnchere, int montantEnchere, List<Utilisateur> lstUtilisateur, ArticleVendu articleVendu) {
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        LstUtilisateur = lstUtilisateur;
        this.articleVendu = articleVendu;
    }

    public Enchere(int noEnchere, Date dateEnchere, int montantEnchere, List<Utilisateur> lstUtilisateur, ArticleVendu articleVendu) {
        this.noEnchere = noEnchere;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        LstUtilisateur = lstUtilisateur;
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

    public List<Utilisateur> getLstUtilisateur() {
        return LstUtilisateur;
    }

    public void setLstUtilisateur(List<Utilisateur> lstUtilisateur) {
        LstUtilisateur = lstUtilisateur;
    }

    public ArticleVendu getArticleVendu() {
        return articleVendu;
    }

    public void setArticleVendu(ArticleVendu articleVendu) {
        this.articleVendu = articleVendu;
    }
}
