package fr.eni.tp.enchere.ihm;


import fr.eni.tp.enchere.bll.InscriptionService;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InscriptionController {

    InscriptionService inscriptionService;

    public InscriptionController(InscriptionService inscriptionService) {

        this.inscriptionService = inscriptionService;
    }

    @GetMapping("/inscription")
    public String inscription(Model model) {

        model.addAttribute("utilisateur", new Utilisateur());

        return "view_inscription";
    }

    @PostMapping("/inscription")
    public String inscription(@ModelAttribute Utilisateur utilisateur,@RequestParam("confrimation") String confrimation, Model model) {

    //verification
        String password = utilisateur.getMotDePasse();
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).+$";

        boolean validPassword = password.matches(regex);

        if(!confrimation.equals(password)) {
            model.addAttribute("message", "Les mot de passe ne correspondent pas");
            return "view_inscription";
        }

        if (!validPassword ) {
            model.addAttribute("message", "Mot de passe invalide");
            return "view_inscription";
        }


        if(inscriptionService.pseudoExist(utilisateur.getPseudo()) || inscriptionService.emailExist(utilisateur.getEmail()) || inscriptionService.telephoneExist(utilisateur.getTelephone())) {
            model.addAttribute("message", "Utilisateur déja enregistré");
            return "view_inscription";
        } else {

            inscriptionService.create(utilisateur);
            model.addAttribute("message", "Inscription réussie");

            return "redirect:/login";
        }
    }
}
