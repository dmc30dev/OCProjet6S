package org.dmc30.OCprojet6.webapp.resource;

import org.dmc30.OCprojet6.model.bean.Site;

import javax.inject.Named;
import java.util.List;

@Named
public class SiteResource extends AbstractResource {

    public Site getSiteById (int pId) {
        return getManagerFactory().getSiteManager().getSiteById(pId);
    }

    public List<Site> getSitesByRegion (int pRegionId) {
        return getManagerFactory().getSiteManager().getSitesByRegion(pRegionId);
    }

    public List<Site> getSitesByDepartement (int pDepartementCode) {
        return getManagerFactory().getSiteManager().getSitesByDepartement(pDepartementCode);
    }

    public List<Site> getSitesByVille (int pVilleId) {
        return getManagerFactory().getSiteManager().getSitesByVille(pVilleId);
    }


    public List<Site> getListSites() {
        return getManagerFactory().getSiteManager().getAllSites();
    }
}
