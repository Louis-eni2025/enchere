package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Utilisateur;

public interface ContexteService {
    Utilisateur charger(String email);
}
