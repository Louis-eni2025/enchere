package fr.eni.tp.enchere.bll;


import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.dal.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscriptionServiceImpl implements  InscriptionService {


    private final UtilisateurDAO utilisateurDAO ;

    public InscriptionServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }


    @Override
    public void create(Utilisateur utilisateur) {

        System.out.println("impl ok");
        utilisateurDAO.create(utilisateur);

    }

    @Override
    public Utilisateur read(int id) {
        return null;
    }

    @Override
    public void update(Utilisateur utilisateur) {

    }

    @Override
    public void delete(int id) {

    }
}
