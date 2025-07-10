package fr.eni.tp.enchere.bo.dto;

import fr.eni.tp.enchere.bo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleVenduDTO {
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

    //CUSTOM
    private Utilisateur createur;
    private boolean isEnchereEnded;
    private boolean isEnchereStarted;
    private boolean hasCurrentUserBid;
    private Utilisateur utilisateurGagnant;

    private ArticleVenduDTO() {}

    public static ArticleVenduDTO createFromArticleVendu(ArticleVendu article){
        ArticleVenduDTO articleVenduDTO = new ArticleVenduDTO();

        articleVenduDTO.setNoArticle(article.getNoArticle());
        articleVenduDTO.setNomArticle(article.getNomArticle());
        articleVenduDTO.setDescription(article.getDescription());
        articleVenduDTO.setDateDebutEnchere(article.getDateDebutEnchere());
        articleVenduDTO.setDateFinEnchere(article.getDateFinEnchere());
        articleVenduDTO.setMiseAPrix(article.getMiseAPrix());
        articleVenduDTO.setPrixVente(article.getPrixVente());
        articleVenduDTO.setEtatVente(article.getEtatVente());
        articleVenduDTO.setLstEnchere(article.getLstEnchere());
        articleVenduDTO.setRetrait(article.getRetrait());
        articleVenduDTO.setCategorie(article.getCategorie());
        articleVenduDTO.setCreateur(article.getUtilisateur());

        return articleVenduDTO;
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

    public Utilisateur getCreateur() {
        return createur;
    }

    public void setCreateur(Utilisateur createur) {
        this.createur = createur;
    }

    public boolean isEnchereEnded() {
        return isEnchereEnded;
    }

    public void setEnchereEnded(boolean enchereEnded) {
        isEnchereEnded = enchereEnded;
    }

    public boolean isEnchereStarted() {
        return isEnchereStarted;
    }

    public void setEnchereStarted(boolean enchereStarted) {
        isEnchereStarted = enchereStarted;
    }

    public Utilisateur getUtilisateurGagnant() {
        return utilisateurGagnant;
    }

    public void setUtilisateurGagnant(Utilisateur utilisateurGagnant) {
        this.utilisateurGagnant = utilisateurGagnant;
    }

    public boolean isHasCurrentUserBid() {
        return hasCurrentUserBid;
    }

    public void setHasCurrentUserBid(boolean hasCurrentUserBid) {
        this.hasCurrentUserBid = hasCurrentUserBid;
    }
}
