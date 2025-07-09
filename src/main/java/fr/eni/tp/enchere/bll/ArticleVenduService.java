package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.bo.Retrait;

import java.util.List;

public interface ArticleVenduService {

    ArticleVendu createArticleVendu(ArticleVendu articleVendu) ;


    Retrait createRetrait(Retrait retrait);

    List<ArticleVendu> displayArticles();
    List<ArticleVendu> displayArticlesByCategorieAndRecherche(Integer categorieId, String recherche);
    List<ArticleVendu> displayArticlesByCategorie(Integer categorieId);
    List<ArticleVendu> displayArticlesRecherche(String recherche);


    Categorie categorieById(int id);

    List<Categorie> categories();
    void delete(int id);
}
