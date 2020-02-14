package org.dmc30.OCprojet6.webapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dmc30.OCprojet6.model.bean.Photo;
import org.dmc30.OCprojet6.model.bean.Site;
import org.dmc30.OCprojet6.webapp.resource.PhotoResource;
import org.dmc30.OCprojet6.webapp.resource.SiteResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;

@Controller
public class PhotoController extends AbstractController {

    @Inject
    SiteResource siteResource;
    @Inject
    PhotoResource photoResource;

    final Logger logger = LogManager.getLogger(PhotoController.class);

    /**
     * Upload une image sur le serveur et la référence dans la base de données.
     *
     * @param pNomPhoto : Le nom de l'image renseigné dans le formulaire.
     * @param pFile     : Le fichier à uploader.
     * @param pSiteId   : L'identifiant de site correspondant.
     * @return le ModelAndView
     */
    @PostMapping("/uploadFile")
    public ModelAndView uploadPhoto(@RequestParam("nomPhoto") String pNomPhoto,
                                    @RequestParam("file") MultipartFile pFile,
                                    @RequestParam("siteId") int pSiteId,
                                    HttpServletRequest request) {

        String vRef = "site";
        String vNomPhoto = "";
        String vUploadMsg;
        ModelAndView vMaV = new ModelAndView();

        if (!pFile.isEmpty()) {
            try {
                byte[] bytes = pFile.getBytes();

                // Crée le repertoire de stockage des images
                String rootPath = request.getSession().getServletContext().getRealPath("/");
                File dir = new File(rootPath + File.separator + "resources/img");

                if (!dir.exists())
                    dir.mkdirs();

                // crée le nom du fichier (avec une référence aléatoire pour diminuer
                // le risque de non concordance entre la BD et le répertoire
                Random rand = new Random();
                int vRandomRef = rand.nextInt(1000000);
                vNomPhoto = "Site" + pSiteId + "_" + vRandomRef + "_" + pNomPhoto + ".jpeg";
                // crée le fichier dans le repertoire
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + vNomPhoto);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                logger.info("Repertoire créé = " + serverFile.getAbsolutePath());
                logger.info("Fichier créé = " + vNomPhoto);

                vUploadMsg = "L'image " + pNomPhoto + " a été chargée !";
            } catch (Exception e) {
                vUploadMsg = "Echec du chargement de l'image " + pNomPhoto + " => " + e.getMessage();
            }
        } else {
            vUploadMsg = "Il n'y a pas d'image à charger";
        }

        Photo vPhoto = new Photo(vNomPhoto, vRef, pSiteId);
        photoResource.createPhoto(vPhoto);

//        vMaV = showSitePage(pSiteId);
//        vMaV.addObject("uploadMessage", vUploadMsg);

        // création du site à retourner
        Site vSite = siteResource.getSiteById(pSiteId);
        // création de la liste de photo correspondantes au site
        List<Photo> vListPhotos = photoResource.getPhotoByRefId(vPhoto.getRefId(), vPhoto.getRef());

        vMaV.addObject("site", vSite);
        vMaV.addObject("listPhotos", vListPhotos);
        vMaV.addObject("uploadMsg", vUploadMsg);
        vMaV.setViewName("sites");
        return vMaV;
    }

}