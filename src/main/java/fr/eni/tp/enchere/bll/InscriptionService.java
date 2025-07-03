package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface InscriptionService {

    void create(Utilisateur utilisateur) ;

    //verif utilisateur déja existant
    boolean pseudoExist(String pseudo);
    boolean emailExist(String email);
    boolean telephoneExist(String telephone);

    Utilisateur read(int id);
    Utilisateur read(String email);

    void update(Utilisateur utilisateur);

    boolean confirmPassword(String password1, String password2);

    boolean validPassword(Utilisateur utilisateur, String motDePasse);

    void delete(int id);
}
