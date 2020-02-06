package org.dmc30.OCprojet6.webapp.controller;

import org.dmc30.OCprojet6.model.bean.Users;
import org.dmc30.OCprojet6.model.exception.TechnicalException;
import org.dmc30.OCprojet6.webapp.resource.AuthenticationResource;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.sql.SQLException;

@Controller
public class AuthenticationController {

    @Inject
    private AuthenticationResource authenticationResource;


    @GetMapping("/signin")
    public String showUserAccountForm() {
        return "signin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Identifiant ou mot de passe incorrect !");
        }
        if (logout != null) {
            model.addObject("msg", "Vous êtes deconnecté !");
        }
        model.setViewName("login");
        return model;
    }

    @PostMapping("/signup")
    public ModelAndView saveUsers(@ModelAttribute("users") Users pUsers) {
        ModelAndView vModel = new ModelAndView();
//        int[] vResult = authenticationResource.rechercheDoublon(pUsers);
//        if (vResult[0] != 0) {
//            vModel.addObject("error", "Cet identifiant existe déjà !");
//            vModel.setViewName("signin");
//        } else if (vResult[1] != 0) {
//            vModel.addObject("error", "Cet email existe déjà !");
//            vModel.setViewName("signin");
//        } else {
            try {
                authenticationResource.createUsers(pUsers);
                vModel.addObject("message", "Votre compte est créé !");
                vModel.setViewName("login");
            }
            catch (TechnicalException e) {
                vModel.addObject("message", e.getMessage());
                vModel.setViewName("login");
            }
//        }
        return vModel;
    }

}