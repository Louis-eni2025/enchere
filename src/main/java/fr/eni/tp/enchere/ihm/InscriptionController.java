package fr.eni.tp.enchere.ihm;


import fr.eni.tp.enchere.bll.InscriptionService;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String inscription(@ModelAttribute Utilisateur utilisateur, Model model)


{



    inscriptionService.create(utilisateur);


    //verification
    String password = utilisateur.getMotDePasse();
    String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).+$";

    boolean validPassword = password.matches(regex);

    if (!validPassword ) {
        System.out.println("password incorrecte");
        return "redirect:/inscription";
    }

    if(!inscriptionService.pseudoExist(utilisateur.getPseudo())) {

        model.addAttribute("message", "pseudo déja use");
        return "view_inscription";
    }
    if(!inscriptionService.emailExist(utilisateur.getEmail())) {

        model.addAttribute("message", "email déja use");
        return "view_inscription";
    }
    if(!inscriptionService.telephoneExist(utilisateur.getTelephone())) {

        model.addAttribute("message", "telephone déja attribué");
        return "view_inscription";
    }

    model.addAttribute("message", "inscription reussit");





        return "loginAsupp";
}



}
