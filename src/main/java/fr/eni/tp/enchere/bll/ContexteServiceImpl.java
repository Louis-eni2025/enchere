package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Utilisateur;
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
            throw new BusinessException(BusinessCode.VALIDATION_UTILISATEUR_INCONNU);
        }
    }
}

