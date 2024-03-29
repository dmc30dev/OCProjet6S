package org.dmc30.OCprojet6.consumer.impl.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dmc30.OCprojet6.consumer.contract.dao.PhotoDao;
import org.dmc30.OCprojet6.consumer.impl.rowmapper.PhotoRM;
import org.dmc30.OCprojet6.model.bean.Photo;
import org.dmc30.OCprojet6.model.exception.ErrorMessages;
import org.dmc30.OCprojet6.model.exception.TechnicalException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class PhotoDaoImpl extends AbstractDao implements PhotoDao {

    Logger logger = LogManager.getLogger(PhotoDaoImpl.class);

    @Inject
    PhotoRM photoRM;

    @Override
    public void createPhoto(Photo pPhoto) throws TechnicalException {
        String vSQL = "INSERT INTO photo (nom, reference_id, ref_id) VALUES (:nom, :referenceId, :refId)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("nom", pPhoto.getNom());
        vParams.addValue("referenceId", pPhoto.getReferenceId());
        vParams.addValue("refId", pPhoto.getRefId());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        try {
            vJdbcTemplate.update(vSQL, vParams);
        }catch (
                BadSqlGrammarException e) {
            logger.error("Problème de syntaxe dans la requète SQL");
            throw new TechnicalException(ErrorMessages.SQL_SYNTAX_ERROR.getErrorMessage());
        } catch (
                DataAccessException e) {
            logger.error("Problème d'accès à la base de données");
            throw new TechnicalException(ErrorMessages.SQL_UPDATE_ERROR.getErrorMessage());
        } catch (Exception e) {
            logger.error("Problème technique");
            throw new TechnicalException(ErrorMessages.TECHNICAL_ERROR.getErrorMessage());
        }
    }

    @Override
    public Photo getPhotoById(int pId) {
        String vSQL = "SELECT * FROM photo WHERE photo_id=" + pId;
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        List<Photo> vListPhotos = vJdbcTemplate.query(vSQL, photoRM);
        return vListPhotos.get(0);
    }

    @Override
    public List<Photo> getPhotosByRefId(int pReferenceId, int pRefId) {
        String vSQL = "SELECT * FROM photo WHERE reference_id = :referenceId AND ref_id = :refId";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("referenceId", pReferenceId);
        vParams.addValue("refId", pRefId);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        return vJdbcTemplate.query(vSQL, vParams, photoRM);
    }

    @Override
    public List<Photo> getAllPhotos() {
        String vSQL = "SELECT * FROM photo";
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        return vJdbcTemplate.query(vSQL, photoRM);
    }

    @Override
    public void updatePhoto(Photo pPhoto) {

    }

    @Override
    public void deletePhoto(int pId) {

    }

}
