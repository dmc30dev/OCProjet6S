package org.dmc30.OCprojet6.webapp.controller;

import org.dmc30.OCprojet6.model.bean.Caracteristique;
import org.dmc30.OCprojet6.webapp.resource.AllCaracteristiqueResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/caracteristique")
public class CaracteristiqueController {

    @Inject
    private AllCaracteristiqueResource allCaracteristiqueResource;

    @GetMapping("/liste")
    public String listCaracteristique(Model pModel) {
        List<Caracteristique> vCaracteristique = allCaracteristiqueResource.getListCaracteristique();
        pModel.addAttribute("caracteristiques", vCaracteristique);
        return "liste-caracteristique";
    }

    @GetMapping("/formulaireAjout")
    public String showFormForAdd(Model pModel) {
        Caracteristique vCaracteristique = new Caracteristique();
        pModel.addAttribute("caracteristique", vCaracteristique);
        return "formulaire-ajout";
    }

    @PostMapping("/ajoutCaracteristique")
    public String saveCaracteristique(@ModelAttribute("caracteristique") Caracteristique pCaracteristique) {
        allCaracteristiqueResource.createCaracteristique(pCaracteristique);
        return "redirect:/caracteristique/liste";
    }

    @GetMapping("/formulaireUpdate")
    public String showFormForUpdate(@RequestParam("caracteristiqueId") int pId, Model pModel) {
        Caracteristique vCaracteristique = allCaracteristiqueResource.getCaracteristiqueById(pId);
        pModel.addAttribute(vCaracteristique);
        return "formulaire-update";
    }

    @PostMapping("/updateCaracteristique")
    public String updateCaracteristique(@ModelAttribute("caracteristique") Caracteristique pCaracteristique) {
        allCaracteristiqueResource.updateCaracteristique(pCaracteristique);
        return "redirect:/caracteristique/liste";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("caracteristiqueId") int pId) {
        allCaracteristiqueResource.deleteCarateristique(pId);
        return "redirect:/caracteristique/liste";
    }
}

