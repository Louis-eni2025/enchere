package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.ArticleVendu;

public class ArticleVenduDAOImpl implements ArticleVenduDAO {

    private String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES(:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente, :no_utilisateur, :no_categorie)";

    @Override
    public void create(ArticleVendu articleVendu) {

    }

    @Override
    public ArticleVendu readById(int id) {
        return null;
    }

    @Override
    public ArticleVendu readByEmail(String email) {
        return null;
    }

    @Override
    public void update(ArticleVendu articleVendu) {

    }

    @Override
    public void delete(int id) {

    }
}
