package org.dmc30.OCprojet6.webapp.controller;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dmc30.OCprojet6.model.bean.Departement;
import org.dmc30.OCprojet6.model.bean.Site;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Labo extends AbstractController {

    Logger logger = LogManager.getLogger(Labo.class);

    @GetMapping("/autoPopulate")
    public void autoPopulate (@RequestParam(value = "region") int pId,
                              HttpServletResponse response) throws IOException {
        logger.debug("Into autoPopulate Test : id = "+pId);
        List<Departement> vListDepartement = geographicResource.getDepartementsByRegion(pId);
        // Creation de la liste de noms correspondants à la recherche en format JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String vJSONSearchResult = new Gson().toJson(vListDepartement);
        logger.debug("JSON searchList = " + vJSONSearchResult);
        response.getWriter().write(vJSONSearchResult);

    }

//    @PostMapping("/creationSiteC")
//    public ModelAndView createSite(Model pModel,
//                                   @RequestParam(value = "nom", required = false) String pNomSite,
//                                   @RequestParam(value = "description", required = false) String pDescription,
//                                   @RequestParam(value = "ville", required = false) String pNomVille,
//                                   @RequestParam(value = "region", required = false) Integer pRegionId,
//                                   @RequestParam(value = "departement", required = false) Integer pDepartementCode,
//                                   @RequestParam(value = "typeRoche", required = false) Integer pTypeRocheId) {
//
//        Site vNewSite = null;
//        List<Site> vListSites = new ArrayList<>();
//        ModelAndView vMaV = new ModelAndView();
//        try {
//            vNewSite = siteResource.createSite(pNomSite, pDescription, pNomVille, pRegionId, pDepartementCode, pTypeRocheId);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        } finally {
//            String vMessageCreationSite = "Le nouveau site " + pNomSite + " est créé !";
//            // debug
//            vNewSite.setId(siteResource.getLastId());
//            vListSites.add(vNewSite);
//            vMaV.addObject("messageCreationSite", vMessageCreationSite);
//            vMaV.addObject("listSites", vListSites);
//            vMaV.setViewName("jsTestPage");
//            afficherListe(pModel);
//        }
//        return vMaV;
//    }




}
