package fr.eni.tp.enchere.ihm;

import fr.eni.tp.enchere.bll.UtilisateurService;
import org.springframework.web.bind.annotation.GetMapping;

public class profileTestController {

    UtilisateurService utilisateurService;

    public profileTestController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/profileTestDelete")
    public void deleteUser(int id) {
        utilisateurService.deleteUser(id);
    }
}
