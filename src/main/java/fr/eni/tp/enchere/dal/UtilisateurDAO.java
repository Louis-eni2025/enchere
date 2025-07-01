package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
    void create(Utilisateur utilisateur);
    Utilisateur read(int id);
    void update(Utilisateur utilisateur);
    void delete(int id);

    //Verification pseudo,email ou telephone déja existant

    boolean pseudoExist(String pseudo);

    boolean emailExist(String email);

    boolean telephoneExist(String telephone);
}
