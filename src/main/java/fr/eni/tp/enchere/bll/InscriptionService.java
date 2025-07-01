package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.stereotype.Service;


public interface InscriptionService {

    void create(Utilisateur utilisateur);

    Utilisateur read(int id);

    void update(Utilisateur utilisateur);

    void delete(int id);
}
