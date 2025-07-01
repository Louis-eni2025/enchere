package fr.eni.tp.enchere.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    // Liste des clefs d'externalisation
    private List<String> clefsExternalisations;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public List<String> getClefsExternalisations() {
        return clefsExternalisations;
    }

    /**
     * Permet d'ajouter une clef d'erreur
     *
     * @param clef
     *
     * @comportement initialise la liste si besoin
     */
    public void add(String clef) {
        if (clefsExternalisations == null) {
            clefsExternalisations = new ArrayList<>();
        }
        clefsExternalisations.add(clef);
    }
}

