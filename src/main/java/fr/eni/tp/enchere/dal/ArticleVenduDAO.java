package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.bo.Retrait;

import java.util.List;

public interface ArticleVenduDAO {
    ArticleVendu create(ArticleVendu articleVendu);
    ArticleVendu readById(int id);



    //void createRetrait(Retrait retrait);

    List<ArticleVendu> findAll();

    List<ArticleVendu> findAllByRecherche(String recherche, boolean enCours);
    List<ArticleVendu> findAllByCategorieAndRecherche(Integer idCategorie, String recherche, boolean enCours);
    List<ArticleVendu> findAllByCategorie(Integer categorieId, boolean enCours);
    List<ArticleVendu> findAllEnCours();

    void update(ArticleVendu articleVendu);
    void delete(int id);
}
