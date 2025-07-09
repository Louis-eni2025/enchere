package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduService {

    void createArticleVendu(ArticleVendu articleVendu) ;


    List<ArticleVendu> displayArticles();
    List<ArticleVendu> displayArticlesByCategorieAndRecherche(Integer categorieId, String recherche);
    List<ArticleVendu> displayArticlesByCategorie(Integer categorieId);
    List<ArticleVendu> displayArticlesRecherche(String recherche);
    void delete(int id);
}
