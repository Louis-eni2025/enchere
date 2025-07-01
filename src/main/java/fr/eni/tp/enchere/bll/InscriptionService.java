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

    void update(Utilisateur utilisateur);

    void delete(int id);
}
