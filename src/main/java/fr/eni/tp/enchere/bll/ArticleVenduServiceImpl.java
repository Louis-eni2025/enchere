package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.bo.Enchere;
import fr.eni.tp.enchere.bo.Retrait;
import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.dal.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {

    private final ArticleVenduDAO articleVenduDAO;
    private final CategorieDAO categorieDAO;
    private final UtilisateurDAOImpl utilisateurDAOImpl;
    private final RetraitDAO retraitDAO;




    public ArticleVenduServiceImpl(
            ArticleVenduDAO articleVenduDAO,
            CategorieDAO categorieDAO,
            UtilisateurDAOImpl utilisateurDAOImpl, RetraitDAO retraitDAO) {
        this.articleVenduDAO = articleVenduDAO;
        this.categorieDAO = categorieDAO;
        this.utilisateurDAOImpl = utilisateurDAOImpl;
        this.retraitDAO = retraitDAO;
    }

    @Override
    @Transactional
    public ArticleVendu createArticleVendu(ArticleVendu articleVendu) {

        System.out.println("service" + articleVendu);



        ArticleVendu article = articleVenduDAO.create(articleVendu);


        return article;
    }

    @Override
    public Retrait createRetrait(Retrait retrait) {


        System.out.println("Rue reçue par service: " + retrait.getRue());
        retraitDAO.createRetrait(retrait);

        return retrait;
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

    @Override
    public Enchere encherir(Enchere enchere) {
        return null;
    }


    //a travailler pour converter
    @Override
    public Categorie categorieById(int id) {
        return categorieDAO.readById(id);
    }


    @Override
    public List<Categorie> categories(){

        return categorieDAO.findAllCategories();

    }

    private void loadRelations(ArticleVendu articleVendu){
        Categorie categorie = categorieDAO.readById(articleVendu.getCategorie().getNoCategorie());
        articleVendu.setCategorie(categorie);
        Utilisateur utilisateur = utilisateurDAOImpl.read(articleVendu.getUtilisateur().getNoUtilisateur());
        articleVendu.setUtilisateur(utilisateur);
    }
}
