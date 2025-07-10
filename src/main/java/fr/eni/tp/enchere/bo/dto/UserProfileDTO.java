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

    public UserProfileDTO() {
    }

    public UserProfileDTO(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public UserProfileDTO(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville) {
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public int getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
