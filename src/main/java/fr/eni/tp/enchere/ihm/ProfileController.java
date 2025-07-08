package fr.eni.tp.enchere.ihm;

import fr.eni.tp.enchere.bll.ContexteService;
import fr.eni.tp.enchere.bll.InscriptionService;
import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.exceptions.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ProfileController {
    InscriptionService inscriptionService;
    ContexteService contexteService;

    public ProfileController(InscriptionService inscriptionService, ContexteService contexteService) {
        this.inscriptionService = inscriptionService;
        this.contexteService = contexteService;
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
            BindingResult bindingResult,
           /* @RequestParam("confirmation") String confirmation,
            @RequestParam("motDePasseActuel") String motDePasseActuel,*/
            Model model,

            Principal principal)
    {   System.out.println("on va modifier le profile");

        if (principal != null) {
            String userEmail = principal.getName();
          /*  try {

                inscriptionService.validPassword(userEmail, motDePasseActuel);

            } catch (BusinessException e) {
                e.add(BusinessCode.VALIDATION_UTILISATEUR_MDP_INVALIDE);
                e.getClefsExternalisations().forEach(
                        key -> {
                            ObjectError error = new ObjectError("globalError", key);
                            bindingResult.addError(error);
                        }
                );
            }

            if(!inscriptionService.confirmPassword(utilisateur.getMotDePasse(), confirmation)) {
                BusinessException e = new BusinessException();;
                e.add(BusinessCode.VALIDATION_UTILISATEUR_MDP_NONIDENTIQUE);
                e.getClefsExternalisations().forEach(
                        key -> {
                            ObjectError error = new ObjectError("globalError", key);
                            bindingResult.addError(error);
                        }
                );
            }*/

            try {
                if (bindingResult.hasErrors()) {
                    return "modifierProfile";
                }
                inscriptionService.validUser(utilisateur, userEmail);
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
        }
        return "redirect:/login";
    }

    @GetMapping("/deleteProfile")
    public String deleteProfile(Model model, Principal principal) {

        if (principal != null) {
            try {
                Utilisateur utilisateur = contexteService.charger(principal.getName());
                int id = utilisateur.getNoUtilisateur();
                inscriptionService.delete(id);
                return "redirect:/logout";
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/login";
    }
}

