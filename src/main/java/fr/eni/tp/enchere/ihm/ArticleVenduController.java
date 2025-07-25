package fr.eni.tp.enchere.ihm;


import fr.eni.tp.enchere.bll.ArticleVenduService;
import fr.eni.tp.enchere.bll.ContexteService;
import fr.eni.tp.enchere.bll.DetailArticleService;
import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.bo.Retrait;
import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.bo.dto.ArticleForm;
import fr.eni.tp.enchere.bo.dto.ArticleVenduDTO;
import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleVenduController {


    ArticleVenduService articleVenduService;
    ContexteService contexteService;
    DetailArticleService detailArticleService;

    public ArticleVenduController(
            ArticleVenduService articleVenduService,
            ContexteService contexteService
    ) {
        this.articleVenduService = articleVenduService;
        this.contexteService = contexteService;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(value = "categorie",required = false) String categorie, @RequestParam(value = "recherche",required = false) String recherche, @RequestParam(value = "enCours",required = false) Boolean enCours, Principal principal) {
        Utilisateur currentUser = null;
        if(enCours == null){
            enCours = false;
        }
        if(principal != null){
            currentUser = contexteService.charger(principal.getName());
        }

        List<ArticleVenduDTO> articles = articleVenduService.manageRecherche(recherche, categorie, enCours, currentUser);
        model.addAttribute("articleVenduLst", articles);





        return "index";
    }

    @GetMapping("/addArticle")
    public String addArticle(Categorie categorie, Model model) {

          model.addAttribute("articleForm", new ArticleForm());


        List<Categorie> categories = articleVenduService.categories();
        model.addAttribute("categorieLst", categories);

        return "article_form";
    }




    @PostMapping("/addArticle")
    public String addArticleVendu(@Valid @ModelAttribute("articleForm") ArticleForm articleForm,
                                 Principal principal, Model model) {
        String mail = principal.getName();

        Utilisateur utilisateur = contexteService.charger(mail);


        ArticleVendu articleVenduVenantDuFormulaire = articleForm.getArticleVendu();
        articleVenduVenantDuFormulaire.setUtilisateur(utilisateur);

        ArticleVendu articleVenduVenantDeLaBDD = articleVenduService.createArticleVendu(articleVenduVenantDuFormulaire);


        Retrait retrait = articleForm.getRetrait();
        retrait.setArticleVendu(articleVenduVenantDuFormulaire);

        articleVenduService.createRetrait(retrait);



        System.out.println("articleVendu " + articleVenduVenantDeLaBDD);
        System.out.println("retrait " + retrait);

        articleVenduVenantDeLaBDD.setUtilisateur(utilisateur);
        articleVenduVenantDeLaBDD.setRetrait(retrait);

        return "redirect:/";
    }
}
