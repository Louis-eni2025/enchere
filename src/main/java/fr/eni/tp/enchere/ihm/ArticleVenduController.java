package fr.eni.tp.enchere.ihm;


import fr.eni.tp.enchere.bll.ArticleVenduService;
import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.exceptions.BusinessException;
import jakarta.validation.Valid;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArticleVenduController {


    ArticleVenduService articleVenduService;

    public ArticleVenduController(ArticleVenduService articleVenduService) {
        this.articleVenduService = articleVenduService;
    }

    @GetMapping("/index")
    public String index(Model model) {


        List<ArticleVendu> article = articleVenduService.displayArticles();
        model.addAttribute("articleVenduLst", article);


        return "index";
    }

    @GetMapping("/addArticle")
    public String addArticle(Model model) {

        model.addAttribute("articleVendu", new ArticleVendu());

        model.addAttribute("articleVenduLst", articleVenduService.displayArticles());
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
    public String addArticleVendu(@Valid @ModelAttribute("articleVendu") ArticleVendu articleVendu,
                                  BindingResult bindingResult, Model model, Authentication auth) {


        if (hasRole(auth, "ROLE_ADMIN")) {

                articleVenduService.createArticleVendu(articleVendu);
                return "article_form";
            }
            /*
            try {
                articleVenduService.createArticleVendu(articleVendu);
                return "redirect:/index";
            } catch (BusinessException be) {
                model.addAttribute("articleVenduLst", articleVenduService.displayArticles());
                be.getClefsExternalisations().forEach(
                        key->{
                    ObjectError error = new ObjectError("globalError", key);
                    bindingResult.addError(new ObjectError("globalError", key));
                });

                return "article_form";
            }*/



            return "index";
    }
}
