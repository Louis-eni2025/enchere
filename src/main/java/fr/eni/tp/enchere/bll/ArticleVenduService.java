package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduService {

    void createArticleVendu(ArticleVendu articleVendu) ;


    List<ArticleVendu> displayArticles();

}
