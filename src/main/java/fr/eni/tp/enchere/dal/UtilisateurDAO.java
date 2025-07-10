package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.bo.dto.UserProfileDTO;

public interface UtilisateurDAO {
    void create(Utilisateur utilisateur);
    Utilisateur read(int id);

    Utilisateur read(String email);

    void update(Utilisateur utilisateur, String email);

    void delete(int id);

    //Verification pseudo,email ou telephone déja existant

    boolean pseudoExist(String pseudo);

    boolean emailExist(String email);

    boolean telephoneExist(String telephone);

    String passwordSelect(String email);

    boolean validPseudo(String email, String pseudo);

    void deleteArticle(int idUser);

    void deleteEnchere(int idUser);

    void resetPassword(int id, String nouveauPassword);

    void update(UserProfileDTO userProfileDTO);

}
