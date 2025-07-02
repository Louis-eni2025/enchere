package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.dal.ArticleVenduDAO;
import org.springframework.stereotype.Service;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {


    private final ArticleVenduDAO articleVenduDAO;

    public ArticleVenduServiceImpl(ArticleVenduDAO articleVenduDAO) {
        this.articleVenduDAO = articleVenduDAO;
    }



    @Override
    public void createArticleVendu(ArticleVendu articleVendu) {
        articleVenduDAO.create(articleVendu);

    }

    @Override
    public void readById(int id){
        articleVenduDAO.readById(id);
    }
}
