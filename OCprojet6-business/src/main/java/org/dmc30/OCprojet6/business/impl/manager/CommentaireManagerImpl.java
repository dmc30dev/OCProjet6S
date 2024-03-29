package org.dmc30.OCprojet6.business.impl.manager;

import org.dmc30.OCprojet6.business.contract.manager.CommentaireManager;
import org.dmc30.OCprojet6.model.bean.Commentaire;
import org.dmc30.OCprojet6.model.exception.TechnicalException;

import javax.inject.Named;
import java.util.List;

@Named
public class CommentaireManagerImpl extends AbstractManager implements CommentaireManager {

    @Override
    public void createCommentaire(Commentaire pCommentaire) throws TechnicalException {
        getDaoFactory().getCommentaireDao().createCommentaire(pCommentaire);

    }

    @Override
    public Commentaire getCommentaireById(int pId) {
        return getDaoFactory().getCommentaireDao().getCommentaireById(pId);
    }

    @Override
    public List<Commentaire> getAllCommentaires() {
        return getDaoFactory().getCommentaireDao().getAllCommentaires();
    }

    @Override
    public void updateCommentaire(Commentaire pCommentaire) throws TechnicalException {
        getDaoFactory().getCommentaireDao().updateCommentaire(pCommentaire);
    }

    @Override
    public void deleteCommentaire(int pId) throws TechnicalException {
        getDaoFactory().getCommentaireDao().deleteCommentaire(pId);
    }

    @Override
    public List<Commentaire> getValidatedCommentairesByReference(int pRefererenceId, int pRefId) {
        return getDaoFactory().getCommentaireDao().getValidatedCommentairesByReference(pRefererenceId, pRefId);
    }

    @Override
    public List<Commentaire> getNonValidatedCommentaires() {
        return getDaoFactory().getCommentaireDao().getNonValidatedCommentaires();
    }
}
