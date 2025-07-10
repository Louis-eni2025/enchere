package fr.eni.tp.enchere.bo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserProfileDTO {
    private int noUtilisateur;

    @NotBlank
    @Size(min=2, max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String pseudo;

    @NotBlank
    @Size(min=2, max = 30)
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]{2,30}$")
    private String nom;

    @NotBlank
    @Size(min=2, max = 30)
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]{2,30}$")
    private String prenom;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private String email;

    @NotBlank
    @Pattern(regexp = "^((\\+33\\s?|0)[1-9](\\s?\\d{2}){4})$")
    private String telephone;

    @NotBlank
    @Pattern(regexp = "^[0-9A-Za-zÀ-ÖØ-öø-ÿ'\\.\\-\\s]{3,100}$")
    private String rue;

    @NotBlank
    @Pattern(regexp = "^\\d{5}$")
    private String codePostal;

    @NotBlank
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\-\\s]{2,50}$")
    private String ville;

}
