package fr.eni.tp.enchere.bll;


import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.bo.dto.UserProfileDTO;
import fr.eni.tp.enchere.dal.UtilisateurDAO;
import fr.eni.tp.enchere.exceptions.BusinessCode;
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
    public void update(Utilisateur utilisateur, String email) {
        utilisateurDAO.update(utilisateur, email);
    }

    @Override
    public void update(UserProfileDTO userProfileDTO) {
        utilisateurDAO.update(userProfileDTO);
    }

    @Override
    public boolean confirmPassword(String password1, String password2) {
        return password1.equals(password2);
    }

    @Override
    public void validPassword(String email, String motDePasse) throws BusinessException {
        String mdp = utilisateurDAO.passwordSelect(email);

        if (!passwordEncoder.matches(motDePasse, mdp)) {
            BusinessException be = new BusinessException();
            be.add(BusinessCode.VALIDATION_UTILISATEUR_MDP_INVALIDE);
            throw be;
        }
    }

    @Override
    public void delete(int id) {
        utilisateurDAO.delete(id);
    }

    /*@Override
    public void deleteArticle(int idUser) {
        utilisateurDAO.deleteArticle(idUser);
    }*/

    @Override
    public void deleteEnchere(int idUser) {
        utilisateurDAO.deleteEnchere(idUser);
    }

    @Override
    public boolean validPseudo(String email, String pseudo) {
        return utilisateurDAO.validPseudo(email, pseudo);
    }

    @Override
    public void validUser(Utilisateur utilisateur, String userEmail) throws BusinessException {

        String pseudo = utilisateur.getPseudo();
        String email = utilisateur.getEmail();
        boolean validEmail = email.equals(userEmail) || !emailExist(email);

        if(validPseudo(userEmail, pseudo))
        {
            System.out.println("Pseudo valide");
        } else {
            BusinessException be = new BusinessException();
            be.add(BusinessCode.VALIDATION_UTILISATEUR_PSEUDOEXIST);
            throw be;
        }

        if(validEmail)
        {
            System.out.println("Email valide");
        } else {
            BusinessException be = new BusinessException();
            be.add(BusinessCode.VALIDATION_UTILISATEUR_EMAILEXIST);
            throw be;
        }
    }
}
