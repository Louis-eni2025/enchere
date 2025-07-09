package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.bo.Retrait;

import java.util.List;

public interface ArticleVenduDAO {
    ArticleVendu create(ArticleVendu articleVendu);
    ArticleVendu readById(Integer no_article);



    //void createRetrait(Retrait retrait);

    List<ArticleVendu> findAll();
    List<ArticleVendu> findAllByRecherche(String recherche);
    List<ArticleVendu> findAllByCategorieAndRecherche(Integer idCategorie, String recherche);
    List<ArticleVendu> findAllByCategorie(Integer categorieId);
    void update(ArticleVendu articleVendu);
    void delete(int id);
}
