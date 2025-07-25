package fr.eni.tp.enchere.ihm;


import fr.eni.tp.enchere.bll.InscriptionService;
import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String inscription(@Valid @ModelAttribute("inscriptionForm") Utilisateur utilisateur, BindingResult bindingResult, @RequestParam("confirmation") String confirmation, Model model) {

        String password = utilisateur.getMotDePasse();

        String errorMessage;

        //Validation
        if(!confirmation.equals(password)) {

            model.addAttribute("message", "Les mots de passe ne correspondent pas ...");
        }


        if(inscriptionService.pseudoExist(utilisateur.getPseudo()) || inscriptionService.emailExist(utilisateur.getEmail()) || inscriptionService.telephoneExist(utilisateur.getTelephone())) {
            errorMessage ="Utilisateur déja enregistré";
        } else {
            inscriptionService.create(utilisateur);

            return "redirect:/login";
        }

        model.addAttribute("message", errorMessage);

        return "view_inscription";
    }
}
