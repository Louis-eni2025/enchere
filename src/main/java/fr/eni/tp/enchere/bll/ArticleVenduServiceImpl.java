package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.dal.ArticleVenduDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {


    private final ArticleVenduDAO articleVenduDAO;

    public List<Categorie> categories = new ArrayList<>();

    public ArticleVenduServiceImpl(ArticleVenduDAO articleVenduDAO) {
        this.articleVenduDAO = articleVenduDAO;
    }



    @Override
    public void createArticleVendu(ArticleVendu articleVendu) {
        articleVenduDAO.create(articleVendu);

    }



    @Override
    public List<ArticleVendu> displayArticles(){
        return articleVenduDAO.findAll();
    }
}
