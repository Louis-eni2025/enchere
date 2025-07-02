package fr.eni.tp.enchere.ihm;

import fr.eni.tp.enchere.bll.ContexteService;
import fr.eni.tp.enchere.bll.InscriptionService;
import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.exceptions.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
