package org.dmc30.OCprojet6.business.impl.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dmc30.OCprojet6.business.contract.manager.SiteManager;
import org.dmc30.OCprojet6.business.contract.manager.VilleManager;
import org.dmc30.OCprojet6.model.bean.Description;
import org.dmc30.OCprojet6.model.bean.Site;
import org.dmc30.OCprojet6.model.bean.Ville;
import org.dmc30.OCprojet6.model.exception.ErrorMessages;
import org.dmc30.OCprojet6.model.exception.TechnicalException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class SiteManagerImpl extends AbstractManager implements SiteManager {

    Logger logger = LogManager.getLogger(SiteManagerImpl.class);

    @Inject
    VilleManager villeManager;

    @Override
    @Transactional
    public Site createSite(Site pSite) throws TechnicalException {

        Site vNewSite;
        TransactionStatus vTransactionStatus
                = getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
        try {
            vNewSite = pSite;
            // verifier que la ville n'existe pas
            ArrayList<Integer> vListResult = villeManager.rechercheDoublon(pSite.getVille());
            // creer la ville ou la récuperer si elle existe
            if (vListResult.get(0) > 0) {
                Ville vVille = getDaoFactory().getVilleDao().getVilleById(vListResult.get(1));
                vNewSite.setVille(vVille);
            } else if (vListResult.get(0) == 0) {
                getDaoFactory().getVilleDao().createVille(pSite.getVille());
                int vNewVilleId = getDaoFactory().getVilleDao().getLastId();
                Ville vNewVille = getDaoFactory().getVilleDao().getVilleById(vNewVilleId);
                vNewSite.setVille(vNewVille);
            }
            //créer la description
            getDaoFactory().getDescriptionDao().createDescription(pSite.getDescription());
            int vNewDescriptionId = getDaoFactory().getDescriptionDao().getLastId();
            Description vNewDescription = getDaoFactory().getDescriptionDao().getDescriptionById(vNewDescriptionId);
            vNewSite.setDescription(vNewDescription);
            // creer le site dans la base de donnée
            getDaoFactory().getSiteDao().createSite(vNewSite);
            TransactionStatus vTScommit = vTransactionStatus;
            vTransactionStatus = null;
            getPlatformTransactionManager().commit(vTScommit);
        } catch (BadSqlGrammarException e) {
            logger.error("Problème de syntaxe dans la requète SQL");
            throw new TechnicalException(ErrorMessages.SQL_SYNTAX_ERROR.getErrorMessage());
        } catch (DataAccessException e) {
            logger.error("Problème d'accès à la base de données");
            throw new TechnicalException(ErrorMessages.SQL_UPDATE_ERROR.getErrorMessage());
        } catch (Exception e) {
            logger.error("Problème technique");
            throw new TechnicalException(ErrorMessages.TX_ERROR.getErrorMessage());
        } finally {
            if (vTransactionStatus != null) {
                getPlatformTransactionManager().rollback(vTransactionStatus);
            }
        }
        // renvoyer le site
        return vNewSite;
    }

    @Override
    @Transactional
    public Site updateSite(Site pSite) throws TechnicalException {

        Site vSite;
        TransactionStatus vTransactionStatus
                = getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
        try {
            vSite = pSite;
            // verifier que la ville n'existe pas
            ArrayList<Integer> vListResult = villeManager.rechercheDoublon(pSite.getVille());
            // creer la ville ou la récuperer si elle existe
            if (vListResult.get(0) > 0) {
                Ville vVille = getDaoFactory().getVilleDao().getVilleById(vListResult.get(1));
                vSite.setVille(vVille);
            } else if (vListResult.get(0) == 0) {
                getDaoFactory().getVilleDao().createVille(pSite.getVille());
                int vNewVilleId = getDaoFactory().getVilleDao().getLastId();
                Ville vNewVille = getDaoFactory().getVilleDao().getVilleById(vNewVilleId);
                vSite.setVille(vNewVille);
            }
            //modifier la description
            getDaoFactory().getDescriptionDao().updateDescription(pSite.getDescription());

            // modifier le site dans la base de donnée
            getDaoFactory().getSiteDao().updateSite(vSite);
            TransactionStatus vTScommit = vTransactionStatus;
            vTransactionStatus = null;
            getPlatformTransactionManager().commit(vTScommit);
        } catch (BadSqlGrammarException e) {
            logger.error("Problème de syntaxe dans la requète SQL");
            throw new TechnicalException(ErrorMessages.SQL_SYNTAX_ERROR.getErrorMessage());
        } catch (DataAccessException e) {
            logger.error("Problème d'accès à la base de données");
            throw new TechnicalException(ErrorMessages.SQL_UPDATE_ERROR.getErrorMessage());
        } catch (Exception e) {
            logger.error("Problème technique");
            throw new TechnicalException(ErrorMessages.TX_ERROR.getErrorMessage());
        } finally {
            if (vTransactionStatus != null) {
                getPlatformTransactionManager().rollback(vTransactionStatus);
            }
        }
        return vSite;
    }

    @Override
    public Site getSiteById(int pId) {
        return getDaoFactory().getSiteDao().getSiteById(pId);
    }

    @Override
    public List<Site> getSitesByRegion(int pRegionId) {
        return getDaoFactory().getSiteDao().getSitesByRegion(pRegionId);
    }

    @Override
    public List<Site> getSitesByDepartement(int pCode) {
        return getDaoFactory().getSiteDao().getSitesByDepartement(pCode);
    }

    @Override
    public List<Site> getSitesByVille(int pVilleId) {
        return getDaoFactory().getSiteDao().getSitesByVille(pVilleId);
    }

    @Override
    public List<Site> getMatchingSites(String pMotCle) {
        return getDaoFactory().getSiteDao().getMatchingSites(pMotCle);
    }

    @Override
    public List<Site> getAllSites() {
        return getDaoFactory().getSiteDao().getAllSites();
    }

    @Override
    public int getLastId() {
        return getDaoFactory().getSiteDao().getLastId();
    }

//    @Override
//    public void updateSite(Site pSite) {
//        getDaoFactory().getSiteDao().updateSite(pSite);
//    }

    @Override
    public void deleteSite(int pId) {
        getDaoFactory().getSiteDao().deleteSite(pId);
    }
}
