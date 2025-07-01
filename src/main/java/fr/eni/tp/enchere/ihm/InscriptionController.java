package fr.eni.tp.enchere.ihm;


import fr.eni.tp.enchere.bll.InscriptionService;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    public String inscription(@ModelAttribute Utilisateur utilisateur, Model model)

{
    inscriptionService.create(utilisateur);

    System.out.println("controller ok");

    model.addAttribute("message", "inscription reussit");

        return "redirect:/login";
}
}
