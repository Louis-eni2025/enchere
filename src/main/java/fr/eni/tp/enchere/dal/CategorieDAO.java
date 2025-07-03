package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Categorie;

import java.util.List;

public interface CategorieDAO {
    Categorie readById(int id);
    List<Categorie> findAll();
}
