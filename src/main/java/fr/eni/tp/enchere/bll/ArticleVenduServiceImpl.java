package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;
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
        return List.of();
    }

    @Override
    public List<ArticleVendu> displayArticlesByCategorie(Integer categorieId) {
        return articleVenduDAO.findAllByCategorie(categorieId);
    }

    @Override
    public List<ArticleVendu> displayArticlesRecherche(String recherche) {
        return List.of();
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
