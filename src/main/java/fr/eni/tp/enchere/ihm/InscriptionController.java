package fr.eni.tp.enchere.ihm;


import fr.eni.tp.enchere.bll.InscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InscriptionController {

    InscriptionService inscriptionService;

    public InscriptionController(InscriptionService inscriptionService) {

        this.inscriptionService = inscriptionService;
    }

@PostMapping("/inscription")
    public String inscription(@RequestParam("pseudo") String pseudo, @RequestParam("nom") String nom,
                              @RequestParam("prenom") String prenom){


}



}
