package fr.eni.tp.enchere.bo;

public class Retrait {
    private Integer noRetrait;
    private int noArticle;



    private String rue;
    private String codePostal;
    private String ville;
    private ArticleVendu articleVendu;

    public Retrait() {
    }

    public Retrait(String rue, String codePostal, String ville, ArticleVendu articleVendu) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.articleVendu = articleVendu;
    }

    public Retrait(Integer noRetrait,int noArticle, String rue, String codePostal, String ville, ArticleVendu articleVendu) {
        this.noRetrait = noRetrait;
        this.noArticle = noArticle;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.articleVendu = articleVendu;
    }

    public int getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    public Integer getNoRetrait() {
        return noRetrait;
    }

    public void setNoRetrait(Integer noRetrait) {
        this.noRetrait = noRetrait;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public ArticleVendu getArticleVendu() {
        return articleVendu;
    }

    public void setArticleVendu(ArticleVendu articleVendu) {
        this.articleVendu = articleVendu;
    }
}
