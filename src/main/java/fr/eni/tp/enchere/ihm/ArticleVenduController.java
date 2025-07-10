package fr.eni.tp.enchere.ihm;


import fr.eni.tp.enchere.bll.ArticleVenduService;
import fr.eni.tp.enchere.bll.ContexteService;
import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Utilisateur;
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
    public String addArticle(Model model) {
        //model.addAttribute("articleVenduLst", articleVenduService.displayArticles());
        return "article_form";
    }

    private boolean hasRole(Authentication auth, String role) {
        return auth != null && auth.isAuthenticated()
                && auth
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(role));
    }


    // a travailler
    @PostMapping("/addArticle")
    public String addArticleVendu(@Valid @ModelAttribute("articleVendu") ArticleVendu articleVendu, Model model) {

        model.addAttribute("articleVendu", new ArticleVendu());


        articleVenduService.createArticleVendu(articleVendu);
        return "index";
    }
}
