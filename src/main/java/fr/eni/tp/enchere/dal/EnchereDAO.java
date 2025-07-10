package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Enchere;

public interface EnchereDAO {
    Enchere create(Enchere enchere);
    void update(Enchere enchere);
    void delete(int idEnchere);
    Enchere findById(int idEnchere);
}
