package fr.eni.tp.enchere.configuration;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class EnchereControllerAdvice {
    //@ExceptionHandler (SQLRuntimeException.class)

    @ExceptionHandler(DataAccessException.class)
    public ModelAndView DataBaseExeption(Exception e) {
        //utilisation de la page error.html
        ModelAndView model = new ModelAndView("error");

        model.addObject("errorMessage", "Erreur SQL : "  + e.getMessage());
        model.addObject("status", 1000);

        return model;
    }
}
