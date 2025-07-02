package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleVendu;

import java.sql.SQLException;

public interface ArticleVenduService {

    void createArticleVendu(ArticleVendu articleVendu) ;

    void readById(int id);
}
