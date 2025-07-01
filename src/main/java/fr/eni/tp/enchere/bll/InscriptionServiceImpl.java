package fr.eni.tp.enchere.bll;


import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.dal.UtilisateurDAO;
import fr.eni.tp.enchere.dal.UtilisateurDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscriptionServiceImpl implements  InscriptionService {


    private final UtilisateurDAO utilisateurDAO ;
    private final UtilisateurDAOImpl utilisateurDAOImpl;

    public InscriptionServiceImpl(UtilisateurDAO utilisateurDAO, UtilisateurDAOImpl utilisateurDAOImpl) {
        this.utilisateurDAO = utilisateurDAO;
        this.utilisateurDAOImpl = utilisateurDAOImpl;
    }


    @Override
    public void create(Utilisateur utilisateur) {


        utilisateurDAO.create(utilisateur);

    }

    //Verification pseudo,email ou telephone déja existant

    @Override
    public boolean pseudoExist(String pseudo) {
        return utilisateurDAO.pseudoExist(pseudo);
    }

    @Override
    public boolean emailExist(String email) {
        return utilisateurDAO.emailExist(email);
    }

    @Override
    public boolean telephoneExist(String telephone) {
        return utilisateurDAO.telephoneExist(telephone);
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
