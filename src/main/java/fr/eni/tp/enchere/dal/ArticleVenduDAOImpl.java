package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.ArticleVendu;

public class ArticleVenduDAOImpl implements ArticleVenduDAO {

    private String INSERT = "INSERT INTO ARTICLES_VENDUS"

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
