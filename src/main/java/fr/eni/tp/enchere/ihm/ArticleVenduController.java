package fr.eni.tp.enchere.ihm;


import fr.eni.tp.enchere.bll.ArticleVenduService;
import fr.eni.tp.enchere.bo.ArticleVendu;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping
    public String addArticleVendu(Model model) {

        model.addAttribute("articleVendu", new ArticleVendu());
        model.addAttribute("articleVenduLst", articleVenduService.displayArticles());
        return "view_ajout_articles";


    }

}
