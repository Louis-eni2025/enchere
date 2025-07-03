package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;

import java.util.List;

public interface ArticleVenduDAO {
    void create(ArticleVendu articleVendu);
    ArticleVendu readById(int id);
    List<ArticleVendu> findAll();
    void update(ArticleVendu articleVendu);
    void delete(int id);
}
