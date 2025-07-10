package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.dal.ArticleVenduDAO;
import org.springframework.stereotype.Service;

@Service
public class DetailArticleServiceImpl implements DetailArticleService {

    private final ArticleVenduDAO articleVenduDAO;

    public DetailArticleServiceImpl(ArticleVenduDAO articleVenduDAO) {
        this.articleVenduDAO = articleVenduDAO;
    }



    @Override
    public ArticleVendu articleById(int no_article) {
        return articleVenduDAO.readById(no_article);
    }
}
