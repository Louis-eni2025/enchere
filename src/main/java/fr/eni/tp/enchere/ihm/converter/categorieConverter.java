package fr.eni.tp.enchere.ihm.converter;

import fr.eni.tp.enchere.bll.ArticleVenduService;
import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class categorieConverter implements Converter<String, Categorie> {

    @Autowired
    private ArticleVenduService service;


    //a travailler pour convertir le select des categories
    @Override
    public Categorie convert(String source) {
        return null;
    }
}
