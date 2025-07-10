package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.bo.Enchere;
import fr.eni.tp.enchere.bo.Retrait;
import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.dal.*;
import fr.eni.tp.enchere.dal.ArticleVenduDAO;
import fr.eni.tp.enchere.dal.CategorieDAO;
import fr.eni.tp.enchere.dal.UtilisateurDAOImpl;
import fr.eni.tp.enchere.bo.dto.ArticleVenduDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
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
    public List<ArticleVenduDTO> manageRecherche(String recherche, String categorie, boolean enCours, Utilisateur utilisateur) {

        List<ArticleVendu> articles;
        List<ArticleVenduDTO> articlesDTO = new ArrayList<>();

        if((categorie != null && !categorie.isEmpty()) && (recherche != null && !recherche.isEmpty())){
            articles = displayArticlesByCategorieAndRecherche(Integer.valueOf(categorie), recherche, enCours);
        } else if ((categorie != null && !categorie.isEmpty())) {
            articles = displayArticlesByCategorie(Integer.valueOf(categorie), enCours);
        } else if (recherche != null && "".equals(recherche)) {
            articles = displayArticlesRecherche(recherche, enCours);
        } else if (enCours) {
            articles = articleVenduDAO.findAllEnCours();
        } else {
            articles = displayArticles();
        }


        //MANAGE DTO
        if(!articles.isEmpty()) {
            articles.forEach(this::loadRelations);
            articles.forEach(article -> {
                ArticleVenduDTO articleVenduDTO = ArticleVenduDTO.createFromArticleVendu(article);

                articleVenduDTO.setEnchereEnded(article.getDateFinEnchere().after(new java.util.Date()));
                articleVenduDTO.setEnchereStarted(article.getDateDebutEnchere().after(new java.util.Date()));
                if(articleVenduDTO.isEnchereEnded()){
                    articleVenduDTO.setUtilisateurGagnant(article.getLstEnchere().stream()
                            .max(Comparator.comparingInt(Enchere::getMontantEnchere))
                            .map(Enchere::getUtilisateur)
                            .orElse(null));
                }
                if(utilisateur != null){
                    articleVenduDTO.setHasCurrentUserBid(article.getLstEnchere().stream()
                            .anyMatch(enchere -> enchere.getUtilisateur().getNoUtilisateur() == utilisateur.getNoUtilisateur()));
                }

                articlesDTO.add(articleVenduDTO);
            });
        }

        return articlesDTO;
    }


    @Override
    public List<ArticleVendu> displayArticlesByCategorieAndRecherche(Integer categorieId, String recherche, boolean enCours) {
        List<ArticleVendu> articles;
        if(categorieId == 0 ){
            articles = articleVenduDAO.findAllByRecherche(recherche, enCours);
        } else {
            articles = articleVenduDAO.findAllByCategorieAndRecherche(categorieId, recherche, enCours);
        }

        return articles;
    }

    @Override
    public List<ArticleVendu> displayArticlesByCategorie(Integer categorieId, boolean enCours) {
        if(categorieId == 0 ){
            return displayArticles();
        }

        return articleVenduDAO.findAllByCategorie(categorieId, enCours);
    }

    @Override
    public List<ArticleVendu> displayArticlesRecherche(String recherche, boolean enCours) {

        return articleVenduDAO.findAllByRecherche(recherche, enCours);
    }

    @Override
    public void delete(int id) {
        articleVenduDAO.delete(id);
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
