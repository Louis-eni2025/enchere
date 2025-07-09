package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.dal.ArticleVenduDAO;
import fr.eni.tp.enchere.dal.CategorieDAO;
import fr.eni.tp.enchere.dal.UtilisateurDAOImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {


    private final ArticleVenduDAO articleVenduDAO;
    private final CategorieDAO categorieDAO;
    private final UtilisateurDAOImpl utilisateurDAOImpl;


    public List<Categorie> categories = new ArrayList<>();

    public ArticleVenduServiceImpl(
            ArticleVenduDAO articleVenduDAO,
            CategorieDAO categorieDAO,
            UtilisateurDAOImpl utilisateurDAOImpl) {
        this.articleVenduDAO = articleVenduDAO;
        this.categorieDAO = categorieDAO;
        this.utilisateurDAOImpl = utilisateurDAOImpl;
    }



    @Override
    public void createArticleVendu(ArticleVendu articleVendu) {
        articleVenduDAO.create(articleVendu);
    }



    @Override
    public List<ArticleVendu> displayArticles(){

        List<ArticleVendu> articles = articleVenduDAO.findAll();

        if(!articles.isEmpty()){
            articles.forEach(this::loadRelations);
        }

        return articles;
    }

    @Override
    public List<ArticleVendu> displayArticlesByCategorieAndRecherche(Integer categorieId, String recherche) {
        List<ArticleVendu> articles;
        if(categorieId == 0 ){
            articles = articleVenduDAO.findAllByRecherche(recherche);
        } else {
            articles = articleVenduDAO.findAllByCategorieAndRecherche(categorieId, recherche);
        }

        if(!articles.isEmpty()){
            articles.forEach(this::loadRelations);
        }

        return articles;
    }

    @Override
    public List<ArticleVendu> displayArticlesByCategorie(Integer categorieId) {
        if(categorieId == 0 ){
            return displayArticles();
        }

        List<ArticleVendu> articles = articleVenduDAO.findAllByCategorie(categorieId);

        if(!articles.isEmpty()){
            articles.forEach(this::loadRelations);
        }

        return articles;
    }

    @Override
    public List<ArticleVendu> displayArticlesRecherche(String recherche) {
        List<ArticleVendu> articles = articleVenduDAO.findAllByRecherche(recherche);

        if(!articles.isEmpty()){
            articles.forEach(this::loadRelations);
        }

        return articles;
    }

    @Override
    public void delete(int id) {
        articleVenduDAO.delete(id);
    }

    private void loadRelations(ArticleVendu articleVendu){
        Categorie categorie = categorieDAO.readById(articleVendu.getCategorie().getNoCategorie());
        articleVendu.setCategorie(categorie);
        Utilisateur utilisateur = utilisateurDAOImpl.read(articleVendu.getUtilisateur().getNoUtilisateur());
        articleVendu.setUtilisateur(utilisateur);
    }
}
