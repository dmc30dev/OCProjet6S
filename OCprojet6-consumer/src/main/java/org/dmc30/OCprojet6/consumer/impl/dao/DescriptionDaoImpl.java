package org.dmc30.OCprojet6.consumer.impl.dao;

import org.dmc30.OCprojet6.consumer.contract.dao.DescriptionDao;
import org.dmc30.OCprojet6.consumer.impl.rowmapper.DescriptionRM;
import org.dmc30.OCprojet6.model.bean.Description;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class DescriptionDaoImpl extends AbstractDao implements DescriptionDao {

    @Inject
    DescriptionRM descriptionRM;
    @Override
    public void createDescription(Description pDescription) {
        String vSQL = "INSERT INTO description (description) VALUES (:vDescription)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("vDescription", pDescription.getDescription());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public Description getDescriptionById(int pId) {
        String vSQL = "SELECT * FROM description where description_id="+pId;
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        List<Description> vListDescription = vJdbcTemplate.query(vSQL,descriptionRM);
        Description vDescription = vListDescription.get(0);
        return vDescription;
    }

    @Override
    public List<Description> getAllDescriptions() {
        String vSQL = "SELECT * FROM description";
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        RowMapper<Description> descriptionRowMapper = new DescriptionRM();
        List<Description> vListDescriptions = vJdbcTemplate.query(vSQL, descriptionRowMapper);
        return vListDescriptions;
    }

    @Override
    public int getLastId() {
        String vSQL = "SELECT MAX(description_id) FROM description";
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        Integer vLastId = vJdbcTemplate.queryForObject(vSQL, Integer.TYPE);
        return vLastId;
    }

}
