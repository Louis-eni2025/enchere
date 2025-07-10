package fr.eni.tp.enchere.ihm;


import fr.eni.tp.enchere.bll.ArticleVenduService;
import fr.eni.tp.enchere.bll.DetailArticleService;
import fr.eni.tp.enchere.bo.ArticleVendu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DetailArticleController {


    DetailArticleService detailArticleService;


    public DetailArticleController(DetailArticleService detailArticleService) {
        this.detailArticleService = detailArticleService;
    }

    @GetMapping("/detailsArticle")
    public String detailsArticle(@RequestParam(name="no_article") int no_article, Model model) {

        System.out.println("parametre noarticle recu : " + no_article);
        ArticleVendu articles = detailArticleService.articleById(no_article);

        if(articles == null){
            model.addAttribute("message", "non trouve");
        }else {
            model.addAttribute("articleVenduDetail", articles);

        }


        return "view_details";
    }
}
