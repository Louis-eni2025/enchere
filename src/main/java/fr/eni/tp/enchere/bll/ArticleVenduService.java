package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;

import java.util.List;

public interface ArticleVenduService {

    void createArticleVendu(ArticleVendu articleVendu) ;
    List<ArticleVendu> displayArticles();


    Categorie categorieById();

    List<Categorie> categories();
}
