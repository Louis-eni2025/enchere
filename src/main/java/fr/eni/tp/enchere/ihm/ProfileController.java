package fr.eni.tp.enchere.ihm;

import fr.eni.tp.enchere.bll.ContexteService;
import fr.eni.tp.enchere.bll.InscriptionService;
import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.bo.dto.UserPasswordDTO;
import fr.eni.tp.enchere.bo.dto.UserProfileDTO;
import fr.eni.tp.enchere.exceptions.BusinessCode;
import fr.eni.tp.enchere.exceptions.BusinessException;
import jakarta.validation.Valid;
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
                /*Utilisateur utilisateur = contexteService.charger(principal.getName());
                model.addAttribute("utilisateur", utilisateur);*/

                UserProfileDTO userProfileDTO = contexteService.read(principal.getName());
                model.addAttribute("userProfileDTO", userProfileDTO);
                return "modifierProfile";
            } catch (BusinessException e) {

                e.printStackTrace();
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/modifierProfile")
    public String modifierProfile(

            @Valid @ModelAttribute UserProfileDTO userProfileDTO,
            BindingResult bindingResult,
            Model model,
            Principal principal) {
        System.out.println("on va modifier le profile");

        if (principal != null) {
            String userEmail = principal.getName();
            Utilisateur utilisateur = contexteService.charger(principal.getName());
            try {
                if (bindingResult.hasErrors()) {
                    model.addAttribute("errors", bindingResult.getAllErrors());
                    return "modifierProfile";
                }
                inscriptionService.validUser(utilisateur, userEmail);
                inscriptionService.update(userProfileDTO);
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

                inscriptionService.deleteEnchere(id);
                inscriptionService.delete(id);

                System.out.println("delete fait");
                return "redirect:/logout";
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/resetPassword")
    public String resetPassword(Model model, Principal principal) {

        if (principal != null) {
            String userEmail = principal.getName();
            UserPasswordDTO userPasswordDTO = contexteService.readPassword(userEmail);
            model.addAttribute("userPasswordDTO", userPasswordDTO);
            return "/resetPassword";
        }
        return "redirect:/login";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@Valid @ModelAttribute UserPasswordDTO userPasswordDTO,
                                BindingResult bindingResult,
                                Model model, Principal principal) {

        if (principal != null) {

            try {
                String userEmail = principal.getName();
                inscriptionService.validPassword(userEmail, userPasswordDTO.getMotDePasse());

            } catch (BusinessException e) {
                e.add(BusinessCode.VALIDATION_UTILISATEUR_MDP_INVALIDE);
                e.getClefsExternalisations().forEach(
                        key -> {
                            ObjectError error = new ObjectError("globalError", key);
                            bindingResult.addError(error);
                        }
                );
            }

            if (!inscriptionService.confirmPassword(userPasswordDTO.getNouveauMotDePasse(), userPasswordDTO.getConfirmation())) {
                BusinessException e = new BusinessException();
                ;
                e.add(BusinessCode.VALIDATION_UTILISATEUR_MDP_NONIDENTIQUE);
                e.getClefsExternalisations().forEach(
                        key -> {
                            ObjectError error = new ObjectError("globalError", key);
                            bindingResult.addError(error);
                        }
                );
            }
            contexteService.resetPassword(userPasswordDTO.getNoUtilisateur(), userPasswordDTO.getNouveauMotDePasse());
            return "redirect:/";
        }

        return "redirect:/login";
    }
}

