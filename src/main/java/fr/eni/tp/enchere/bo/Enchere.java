package fr.eni.tp.enchere.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Enchere {
    private int noEnchere;
    private Date dateEnchere;
    private int montantEnchere;
    private List<Utilisateur> LstUtilisateur = new ArrayList<>();
    private ArticleVendu articleVendu;
}
