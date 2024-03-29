package org.dmc30.OCprojet6.consumer.contract.dao;

import org.dmc30.OCprojet6.model.bean.Departement;

import java.util.List;

public interface DepartementDao {

    void createDepartement (Departement pDepartement);
    Departement getDepartementByCode(int pCode);
    List<Departement> getDepartementsByRegion(int pRegionId);
    List<Departement> getMatchingDepartements(String pMotCle);
    List<Departement> getAllDepartements();
    void updateDepartement (Departement pDepartement);
    void deleteDepartement(int pId);
}

