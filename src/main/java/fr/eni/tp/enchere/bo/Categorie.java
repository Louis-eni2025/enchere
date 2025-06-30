package fr.eni.tp.enchere.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private int noCategorie;
    private String libelle;
    private List<ArticleVendu> LstArticleVendu = new ArrayList<ArticleVendu>();

    public Categorie() {
    }

    public Categorie(String libelle, List<ArticleVendu> lstArticleVendu) {
        this.libelle = libelle;
        LstArticleVendu = lstArticleVendu;
    }

    public Categorie(int noCategorie, String libelle, List<ArticleVendu> lstArticleVendu) {
        this.noCategorie = noCategorie;
        this.libelle = libelle;
        LstArticleVendu = lstArticleVendu;
    }

    public int getNoCategorie() {
        return noCategorie;
    }

    public void setNoCategorie(int noCategorie) {
        this.noCategorie = noCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<ArticleVendu> getLstArticleVendu() {
        return LstArticleVendu;
    }

    public void setLstArticleVendu(List<ArticleVendu> lstArticleVendu) {
        LstArticleVendu = lstArticleVendu;
    }
}
