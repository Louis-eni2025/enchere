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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String modifierProfile(@ModelAttribute Utilisateur utilisateur, @RequestParam("confrimation") String confrimation, Model model, BindingResult bindingResult, Principal principal) {

        //verification
        String password = utilisateur.getMotDePasse();
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).+$";

        boolean validPassword = password.matches(regex);



            if (principal != null) {
                if (bindingResult.hasErrors()) {

                    return "modifierProfile";
                }
                try {
                    inscriptionService.update(utilisateur);

                    return "redirect:/profile";
                } catch (BusinessException be) {
                    //ajout de la liste des participant (realisateur ou acteurs

                    be.getClefsExternalisations().forEach(
                            key -> {
                                ObjectError error = new ObjectError("globalError", key);
                                bindingResult.addError(error);
                            }
                    );
                    return "modifierProfile";
                }

            }
            return "redirect:/login";
        }
}

