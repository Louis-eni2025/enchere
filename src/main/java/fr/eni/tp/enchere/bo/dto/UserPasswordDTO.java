package fr.eni.tp.enchere.bo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserPasswordDTO {

    private int noUtilisateur;

    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).+$")
    private String motDePasse;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).+$")
    private String nouveauMotDePasse;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).+$")
    private String confirmation;

    public UserPasswordDTO() {
    }

    public UserPasswordDTO(String email, String motDePasse, String nouveauMotDePasse, String confirmation) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.nouveauMotDePasse = nouveauMotDePasse;
        this.confirmation = confirmation;
    }

    public UserPasswordDTO(int noUtilisateur, String email, String motDePasse, String nouveauMotDePasse, String confirmation) {
        this.noUtilisateur = noUtilisateur;
        this.email = email;
        this.motDePasse = motDePasse;
        this.nouveauMotDePasse = nouveauMotDePasse;
        this.confirmation = confirmation;
    }

    public int getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNouveauMotDePasse() {
        return nouveauMotDePasse;
    }

    public void setNouveauMotDePasse(String nouveauMotDePasse) {
        this.nouveauMotDePasse = nouveauMotDePasse;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }
}
