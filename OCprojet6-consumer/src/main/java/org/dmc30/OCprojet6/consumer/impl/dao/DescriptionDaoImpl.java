package org.dmc30.OCprojet6.consumer.impl.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dmc30.OCprojet6.consumer.contract.dao.DescriptionDao;
import org.dmc30.OCprojet6.consumer.impl.rowmapper.DescriptionRM;
import org.dmc30.OCprojet6.model.bean.Description;
import org.dmc30.OCprojet6.model.exception.ErrorMessages;
import org.dmc30.OCprojet6.model.exception.TechnicalException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class DescriptionDaoImpl extends AbstractDao implements DescriptionDao {

    Logger logger = LogManager.getLogger(DescriptionDaoImpl.class);

    @Inject
    DescriptionRM descriptionRM;

    @Override
    public void createDescription(Description pDescription) throws TechnicalException {
        String vSQL = "INSERT INTO description (description, info) VALUES (:description, :info)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("description", pDescription.getDescription());
        vParams.addValue("info", pDescription.getInfo());
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
    public Description getDescriptionById(int pId) {
        String vSQL = "SELECT * FROM description where description_id="+pId;
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        List<Description> vListDescription = vJdbcTemplate.query(vSQL,descriptionRM);
        return vListDescription.get(0);
    }

    @Override
    public List<Description> getAllDescriptions() {
        String vSQL = "SELECT * FROM description";
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        RowMapper<Description> descriptionRowMapper = new DescriptionRM();
        return vJdbcTemplate.query(vSQL, descriptionRowMapper);
    }

    @Override
    public int getLastId() {
        String vSQL = "SELECT MAX(description_id) FROM description";
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        return vJdbcTemplate.queryForObject(vSQL, Integer.TYPE);
    }

    @Override
    public void updateDescription(Description pDescription) throws TechnicalException {
        String vSQL ="UPDATE description SET description = :vDescription, info = :vInfo WHERE description_id= :vDescriptionId";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("vDescriptionId", pDescription.getId());
        vParams.addValue("vDescription", pDescription.getDescription());
        vParams.addValue("vInfo", pDescription.getInfo());
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

}
