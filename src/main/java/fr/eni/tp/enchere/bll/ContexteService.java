package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.bo.dto.UserPasswordDTO;
import fr.eni.tp.enchere.bo.dto.UserProfileDTO;

public interface ContexteService {
    Utilisateur charger(String email);
    UserProfileDTO read(String email);
    UserPasswordDTO readPassword(String email);
    void resetPassword(int id, String nouveauPassword);
}
