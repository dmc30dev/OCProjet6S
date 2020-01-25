package org.dmc30.OCprojet6.business.manager.contract.manager;

import org.dmc30.OCprojet6.model.bean.Site;

import java.util.List;

public interface SiteManager {

    /**
     * Enregistre un objet de type Site dans la database
     */
    void createSite(Site pSite);

    /**
     * Cherche et renvoie un objet de type Site stocké dans la database
     *
     * @param pId l'identifiant de l'objet recherché
     * @return l'objet recherché
     */
    Site getSiteById(int pId);

    /**
     * Cherche et renvoie la liste des Sites stockée dans la database par région
     *
     * @param pRegionId l'identifiant de la région
     * @return la liste d'objets recherchée
     */
    List<Site> getSitesByRegion(int pRegionId);

    /**
     * Cherche et renvoie la liste des Sites stockée dans la database par région
     *
     * @param pCode le code du département
     * @return la liste d'objets recherchée
     */
    List<Site> getSitesByDepartement(int pCode);

    /**
     * Cherche et renvoie la liste des Sites stockée dans la database par région
     *
     * @param pVilleId l'identifiant de la ville
     * @return la liste d'objets recherchée
     */
    List<Site> getSitesByVille(int pVilleId);


    /**
     * Cherche et renvoie la liste des objets de type Site stockés dans la database
     *
     * @return la liste d'objets recherchée
     */
    List<Site> getAllSites();

    /**
     * Modifie un objet de type Site
     *
     * @param pSite : l'objet à modifier
     */
    void updateSite(Site pSite);

    /**
     * Supprimer un objet de type Site
     *
     * @param pId : l'identifiant de l'objet à supprimer
     */
    void deleteSite(int pId);
}
