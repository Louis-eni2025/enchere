package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.bo.dto.UserProfileDTO;
import fr.eni.tp.enchere.dal.UtilisateurDAO;
import fr.eni.tp.enchere.exceptions.BusinessCode;
import fr.eni.tp.enchere.exceptions.BusinessException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ContexteServiceImpl implements ContexteService {
    private final UtilisateurDAO utilisateurDAO;

    public ContexteServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    @Override
    public Utilisateur charger(String email) {
        try {
            return utilisateurDAO.read(email);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());

            throw new BusinessException(BusinessCode.VALIDATION_UTILISATEUR_INCONNU);
        }
    }

    @Override
    public UserProfileDTO read(String email) {
        Utilisateur utilisateur = charger(email);
        UserProfileDTO userProfileDTO = new UserProfileDTO();

        userProfileDTO.setNom(utilisateur.getNom());
        userProfileDTO.setPrenom(utilisateur.getPrenom());
        userProfileDTO.setEmail(utilisateur.getEmail());
        userProfileDTO.setNoUtilisateur(utilisateur.getNoUtilisateur());
        userProfileDTO.setPseudo(utilisateur.getPseudo());
        userProfileDTO.setCodePostal(utilisateur.getCodePostal());
        userProfileDTO.setVille(utilisateur.getVille());
        userProfileDTO.setRue(utilisateur.getRue());
        userProfileDTO.setTelephone(utilisateur.getTelephone());

        return userProfileDTO;
    }
}

