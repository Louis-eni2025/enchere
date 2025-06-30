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

    public ArticleVendu() {}

    public ArticleVendu(String nomArticle, String description, Date dateDebutEnchere, Date dateFinEnchere, int miseAPrix, int prixVente, String etatVente, List<Enchere> lstEnchere, Retrait retrait, Categorie categorie, Utilisateur utilisateur) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEnchere = dateDebutEnchere;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
        LstEnchere = lstEnchere;
        this.retrait = retrait;
        this.categorie = categorie;
        this.utilisateur = utilisateur;
    }

    public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEnchere, Date dateFinEnchere, int miseAPrix, int prixVente, String etatVente, List<Enchere> lstEnchere, Retrait retrait, Categorie categorie, Utilisateur utilisateur) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEnchere = dateDebutEnchere;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
        LstEnchere = lstEnchere;
        this.retrait = retrait;
        this.categorie = categorie;
        this.utilisateur = utilisateur;
    }

    public int getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebutEnchere() {
        return dateDebutEnchere;
    }

    public void setDateDebutEnchere(Date dateDebutEnchere) {
        this.dateDebutEnchere = dateDebutEnchere;
    }

    public Date getDateFinEnchere() {
        return dateFinEnchere;
    }

    public void setDateFinEnchere(Date dateFinEnchere) {
        this.dateFinEnchere = dateFinEnchere;
    }

    public int getMiseAPrix() {
        return miseAPrix;
    }

    public void setMiseAPrix(int miseAPrix) {
        this.miseAPrix = miseAPrix;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public String getEtatVente() {
        return etatVente;
    }

    public void setEtatVente(String etatVente) {
        this.etatVente = etatVente;
    }

    public List<Enchere> getLstEnchere() {
        return LstEnchere;
    }

    public void setLstEnchere(List<Enchere> lstEnchere) {
        LstEnchere = lstEnchere;
    }

    public Retrait getRetrait() {
        return retrait;
    }

    public void setRetrait(Retrait retrait) {
        this.retrait = retrait;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
