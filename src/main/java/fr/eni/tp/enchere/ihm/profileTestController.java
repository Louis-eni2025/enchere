package fr.eni.tp.enchere.ihm;

import fr.eni.tp.enchere.bll.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class profileTestController {

    UtilisateurService utilisateurService;

    public profileTestController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/profileTestDelete")
    public String deleteUser(int id) {
        utilisateurService.deleteUser(id);
        System.out.println(("supression ok !"));

        return "index";


    }
}
