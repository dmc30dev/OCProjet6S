package org.dmc30.OCprojet6.consumer.impl.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dmc30.OCprojet6.consumer.contract.dao.VilleDao;
import org.dmc30.OCprojet6.consumer.impl.rowmapper.VilleRM;
import org.dmc30.OCprojet6.model.bean.Ville;
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
public class VilleDaoImpl extends AbstractDao implements VilleDao {

    Logger logger = LogManager.getLogger(VilleDaoImpl.class);

    @Inject
    VilleRM villeRM;

    @Override
    public void createVille(Ville pVille) throws TechnicalException {
        String vSQL = "INSERT INTO ville (nom, departement_code) VALUES (:nom, :departementCode)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("nom", pVille.getNom());
        vParams.addValue("departementCode", pVille.getDepartement().getCode());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        try {
            vJdbcTemplate.update(vSQL,vParams);
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
    public Ville getVilleById(int pId) {
        String vSQL = "SELECT * FROM ville WHERE ville_id= :id ORDER BY nom";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pId);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        List<Ville> vListVilles = vJdbcTemplate.query(vSQL, vParams, villeRM);
        return vListVilles.get(0);
    }

    @Override
    public List<Ville> getVillesByDepartement(int pCode) {
        String vSQL = "SELECT * FROM ville WHERE departement_code= :departementCode";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("departementCode", pCode);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        return vJdbcTemplate.query(vSQL, vParams, villeRM);
    }

    @Override
    public List<Ville> getMatchingVilles(String pMotCle) {
        String vMotCle = "%"+pMotCle+"%";
        String vSQL = "SELECT * FROM ville WHERE nom ILIKE :motCle";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("motCle", vMotCle);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        List<Ville> vListVilles = vJdbcTemplate.query(vSQL, vParams, villeRM);
        return vListVilles;
    }

    @Override
    public List<Ville> getAllVilles() {
        String vSQL = "SELECT * FROM ville ORDER BY nom";
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        return vJdbcTemplate.query(vSQL, villeRM);
    }

    @Override
    public int getLastId() {
        String vSQL = "SELECT MAX(ville_id) FROM ville";
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        return vJdbcTemplate.queryForObject(vSQL, Integer.TYPE);
    }

    @Override
    public void updateVille(Ville pVille) {

    }

    @Override
    public void deleteVille(int pId) {

    }

}
