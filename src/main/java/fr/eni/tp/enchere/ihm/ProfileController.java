package fr.eni.tp.enchere.ihm;

import fr.eni.tp.enchere.bll.ContexteService;
import fr.eni.tp.enchere.bll.InscriptionService;
import fr.eni.tp.enchere.bll.InscriptionServiceImpl;
import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.dal.UtilisateurDAOImpl;
import fr.eni.tp.enchere.exceptions.BusinessCode;
import fr.eni.tp.enchere.exceptions.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ProfileController {
    private final UtilisateurDAOImpl utilisateurDAOImpl;
    InscriptionService inscriptionService;
    ContexteService contexteService;

    public ProfileController(InscriptionService inscriptionService, ContexteService contexteService, UtilisateurDAOImpl utilisateurDAOImpl) {
        this.inscriptionService = inscriptionService;
        this.contexteService = contexteService;
        this.utilisateurDAOImpl = utilisateurDAOImpl;
    }

    @GetMapping("/profile")
    public String afficheProfile(Model model, Principal principal) {

        if (principal != null) {
            try {
                Utilisateur utilisateur = contexteService.charger(principal.getName());
                model.addAttribute("utilisateur", utilisateur);
                return "profile";
            } catch (BusinessException e) {

                e.printStackTrace();
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/modifierProfile")
    public String pageModifProfile(Model model, Principal principal) {

        if (principal != null) {
            try {
                Utilisateur utilisateur = contexteService.charger(principal.getName());
                model.addAttribute("utilisateur", utilisateur);

                return "modifierProfile";
            } catch (BusinessException e) {

                e.printStackTrace();
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/modifierProfile")
    public String modifierProfile(
            @ModelAttribute Utilisateur utilisateur,
            @RequestParam("confirmation") String confirmation,
            @RequestParam("motDePasseActuel") String motDePasseActuel,
            Model model,
            BindingResult bindingResult,
            Principal principal)
    {

        System.out.println("on va modifier le profile");
        /*String nom = utilisateur.getNom(); // Objet utilisateur qui provient du formulaire
        String nom2 = principal.getName(); // Utilisateur connecté avec Spring Security*/
        // String nom3 = UtilisateurDAO.read(id) (RETOURNE UN USER)  // Utilisateur en base de donnée ayant l'id id

        if (principal != null) {

            String userEmail = principal.getName();

            try {
                inscriptionService.validPassword(userEmail, motDePasseActuel);
                if(inscriptionService.validPassword(userEmail, motDePasseActuel)) {
                    System.out.println("mdp valide");
                } else {
                    System.out.println("mdp non valide");
                }

            } catch (BusinessException e) {
                e.add(BusinessCode.VALIDATION_UTILISATEUR_MDP_INVALIDE);
                e.getClefsExternalisations().forEach(
                        key -> {
                            ObjectError error = new ObjectError("globalError", key);
                            bindingResult.addError(error);
                        }
                );
            }
            try {
                inscriptionService.confirmPassword(utilisateur.getMotDePasse(), confirmation);
                if(inscriptionService.confirmPassword(utilisateur.getMotDePasse(), confirmation)) {
                    System.out.println("mdp identique");
                } else {System.out.println("mdp non identique");}

            } catch (BusinessException e) {
                e.add(BusinessCode.VALIDATION_UTILISATEUR_MDP_NONIDENTIQUE);
                e.getClefsExternalisations().forEach(
                        key -> {
                            ObjectError error = new ObjectError("globalError", key);
                            bindingResult.addError(error);
                        }
                );
            }

            try {
                inscriptionService.validUser(utilisateur, userEmail);
                System.out.println("user valide");
                if (bindingResult.hasErrors()) {
                    return "modifierProfile";
                }
                try {
                    inscriptionService.update(utilisateur, userEmail);
                    System.out.println("update fait");
                    return "redirect:/profile";
                } catch (BusinessException be) {
                    be.getClefsExternalisations().forEach(
                            key -> {
                                ObjectError error = new ObjectError("globalError", key);
                                bindingResult.addError(error);
                            }
                    );
                }
            } catch (BusinessException be) {
                be.getClefsExternalisations().forEach(
                        key -> {
                            ObjectError error = new ObjectError("globalError", key);
                            bindingResult.addError(error);
                        }
                );
            }
        }
        return "redirect:/login";
    }
}

