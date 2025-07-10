package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.bo.dto.UserProfileDTO;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface InscriptionService {

    void create(Utilisateur utilisateur) ;

    //verif utilisateur déja existant
    boolean pseudoExist(String pseudo);
    boolean emailExist(String email);
    boolean telephoneExist(String telephone);

    Utilisateur read(int id);
    Utilisateur read(String email);

    void update(Utilisateur utilisateur, String email);

    void update(UserProfileDTO userProfileDTO);

    boolean confirmPassword(String password1, String password2);

    void validPassword(String email, String motDePasse);

    void delete(int id);

    /*void deleteArticle(int idUser);*/

    void deleteEnchere(int idUser);

    boolean validPseudo(String email, String pseudo);

    void validUser(Utilisateur utilisateur, String userEmail);
}
