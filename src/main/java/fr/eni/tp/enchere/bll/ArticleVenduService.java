package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.bo.dto.ArticleVenduDTO;

import java.util.List;

public interface ArticleVenduService {

    void createArticleVendu(ArticleVendu articleVendu) ;


    List<ArticleVendu> displayArticles();
    List<ArticleVendu> displayArticlesByCategorieAndRecherche(Integer categorieId, String recherche, boolean finie);
    List<ArticleVendu> displayArticlesByCategorie(Integer categorieId, boolean finie);
    List<ArticleVendu> displayArticlesRecherche(String recherche, boolean finie);
    List<ArticleVenduDTO> manageRecherche(String recherche, String categorieId, boolean finie, Utilisateur utilisateur);
    void delete(int id);

}
