package fr.eni.tp.enchere.bo.dto;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Retrait;

public class ArticleForm {
    private ArticleVendu articleVendu;
    private Retrait retrait;

    public ArticleForm() {
        articleVendu = new ArticleVendu();
        retrait = new Retrait();
    }

    // Getters et setters
    public ArticleVendu getArticleVendu() { return articleVendu; }
    public void setArticleVendu(ArticleVendu articleVendu) { this.articleVendu = articleVendu; }

    public Retrait getRetrait() { return retrait; }
    public void setRetrait(Retrait retrait) { this.retrait = retrait; }
}