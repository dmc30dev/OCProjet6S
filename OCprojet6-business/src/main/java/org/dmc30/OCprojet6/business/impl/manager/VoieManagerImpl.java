package org.dmc30.OCprojet6.business.impl.manager;

import org.dmc30.OCprojet6.business.contract.manager.VoieManager;
import org.dmc30.OCprojet6.model.bean.Voie;

import javax.inject.Named;
import java.util.List;

@Named
public class VoieManagerImpl extends AbstractManager implements VoieManager {
    
    @Override
    public void createVoie(Voie pVoie) {
        getDaoFactory().getVoieDao().createVoie(pVoie);

    }

    @Override
    public Voie getVoieById(int pId) {
        Voie vVoie = getDaoFactory().getVoieDao().getVoieById(pId);
        return vVoie;
    }

    @Override
    public List<Voie> getAllVoies() {
        List<Voie> vListVoie = getDaoFactory().getVoieDao().getAllVoies();
        return vListVoie;
    }

    @Override
    public void updateVoie(Voie pVoie) {
        getDaoFactory().getVoieDao().updateVoie(pVoie);
    }

    @Override
    public void deleteVoie(int pId) {
        getDaoFactory().getVoieDao().deleteVoie(pId);
    }
}