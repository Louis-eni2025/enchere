package fr.eni.tp.enchere.bll;


import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.dal.UtilisateurDAO;
import fr.eni.tp.enchere.dal.UtilisateurDAOImpl;
import fr.eni.tp.enchere.exceptions.BusinessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InscriptionServiceImpl implements  InscriptionService {

    private final UtilisateurDAO utilisateurDAO ;
    private final PasswordEncoder passwordEncoder ;

    public InscriptionServiceImpl(UtilisateurDAO utilisateurDAO, PasswordEncoder passwordEncoder) {
        this.utilisateurDAO = utilisateurDAO;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public void create(Utilisateur utilisateur) {
        String Cryptage = passwordEncoder.encode(utilisateur.getMotDePasse());
        utilisateur.setMotDePasse(Cryptage);
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
        return utilisateurDAO.read(id);
    }

    @Override
    public Utilisateur read(String email) {
        return utilisateurDAO.read(email);
    }


    @Override
    public void update(Utilisateur utilisateur) {
        utilisateurDAO.update(utilisateur);
    }

    @Override
    public boolean confirmPassword(String password1, String password2) {
        return password1.equals(password2);
    }

    @Override
    public boolean validPassword(Utilisateur utilisateur, String motDePasse) {
        String mdp = utilisateur.getMotDePasse();
        return mdp.equals(motDePasse);
    }

    @Override
    public void delete(int id) {
        utilisateurDAO.delete(id);
    }
}
