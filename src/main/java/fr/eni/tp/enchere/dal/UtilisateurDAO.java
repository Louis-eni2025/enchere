package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
    void create(Utilisateur utilisateur);
    Utilisateur read(int id);
    void update(Utilisateur utilisateur);
    void delete(int id);
}
