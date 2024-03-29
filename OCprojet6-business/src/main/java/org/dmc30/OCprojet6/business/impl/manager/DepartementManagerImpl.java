package org.dmc30.OCprojet6.business.impl.manager;

import org.dmc30.OCprojet6.business.contract.manager.DepartementManager;
import org.dmc30.OCprojet6.model.bean.Departement;

import javax.inject.Named;
import java.util.List;

@Named
public class DepartementManagerImpl extends AbstractManager implements DepartementManager {

    @Override
    public void createDepartement(Departement pDepartement) {
        getDaoFactory().getDepartementDao().createDepartement(pDepartement);

    }

    @Override
    public Departement getDepartementByCode(int pCode) {
        return getDaoFactory().getDepartementDao().getDepartementByCode(pCode);
    }

    @Override
    public List<Departement> getDepartementsByRegion(int pRegionId) {
        return getDaoFactory().getDepartementDao().getDepartementsByRegion(pRegionId);
    }

    @Override
    public List<Departement> getMatchingDepartements(String pMotCle) {
        return getDaoFactory().getDepartementDao().getMatchingDepartements(pMotCle);
    }

    @Override
    public List<Departement> getAllDepartements() {
        return getDaoFactory().getDepartementDao().getAllDepartements();
    }


    @Override
    public void updateDepartement(Departement pDepartement) {
        getDaoFactory().getDepartementDao().updateDepartement(pDepartement);
    }

    @Override
    public void deleteDepartement(int pId) {
        getDaoFactory().getDepartementDao().deleteDepartement(pId);
    }
}
