package fr.eni.tp.enchere.bo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserPasswordDTO {

    private int noUtilisateur;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).+$")
    private String motDePasse;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).+$")
    private String nouveauMotDePasse;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).+$")
    private String confirmation;

}
