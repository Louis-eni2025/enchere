package fr.eni.tp.enchere.ihm.converter;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class converterCategorie implements Converter<String, Categorie> {

    private ArticleVendu articleVendu;

    @Override
    public Categorie convert(String source) {

        Categorie categorie = new Categorie();

        long theSource = Long.parseLong(source);


        //A corriger
        return articleVendu.getCategorie(theSource);
    }


}
