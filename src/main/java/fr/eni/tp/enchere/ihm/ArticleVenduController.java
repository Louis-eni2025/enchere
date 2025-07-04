package fr.eni.tp.enchere.ihm;


import fr.eni.tp.enchere.bll.ArticleVenduService;
import fr.eni.tp.enchere.bll.ContexteService;
import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.bo.Utilisateur;
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
import java.util.List;

@Controller
public class ArticleVenduController {


    ArticleVenduService articleVenduService;
    ContexteService contexteService;

    public ArticleVenduController(ArticleVenduService articleVenduService, ContexteService contexteService) {
        this.articleVenduService = articleVenduService;
        this.contexteService= contexteService;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(value = "categorie",required = false) String categorie, @RequestParam(value = "recherche",required = false) String recherche) {

        if((categorie != null && !categorie.isEmpty()) && (recherche != null && !recherche.isEmpty())){
            List<ArticleVendu> articles = articleVenduService.displayArticlesByCategorieAndRecherche(Integer.valueOf(categorie), recherche);
            model.addAttribute("articleVenduLst", articles);
        } else if ((categorie != null && !categorie.isEmpty())) {
            List<ArticleVendu> articles = articleVenduService.displayArticlesByCategorie(Integer.valueOf(categorie));
            model.addAttribute("articleVenduLst", articles);
        } else if (recherche != null && !recherche.isEmpty()) {
            List<ArticleVendu> articles = articleVenduService.displayArticlesRecherche(recherche);
            model.addAttribute("articleVenduLst", articles);
        } else {
            List<ArticleVendu> articles = articleVenduService.displayArticles();
            model.addAttribute("articleVenduLst", articles);
        }






        return "index";
    }

    //recup principale
    //getname sur principale pour mail
    //use contextservice pour recup l utilisateur avec son mailen parametre

        //principal.getName();

    @GetMapping("/addArticle")
    public String addArticle(Categorie categorie,Model model) {
        model.addAttribute("articleVendu", new ArticleVendu());

        List<Categorie> categories = articleVenduService.categories();
        model.addAttribute("categorieLst", categories);


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
    public String addArticleVendu(@Valid @ModelAttribute("articleVendu") ArticleVendu articleVendu, Principal principal, Model model) {



        if(principal!= null){
            String mail = principal.getName();



            Utilisateur utilisateur = contexteService.charger(mail);

            articleVendu.setUtilisateur(utilisateur);

            articleVenduService.createArticleVendu(articleVendu);

            System.out.println("ok");
        }


        return "redirect:/";
    }
}
