package org.dmc30.OCprojet6.consumer.impl.dao;

import org.dmc30.OCprojet6.consumer.contract.dao.UsersDao;
import org.dmc30.OCprojet6.consumer.impl.rowmapper.UsersRM;
import org.dmc30.OCprojet6.model.bean.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class UsersDaoImpl extends AbstractDao implements UsersDao {

    @Inject
    UsersRM usersRM;
    PasswordEncoderGenerator passwordEncoderGenerator;

    @Override
    public void createUsers(Users pUsers) {
        String vUserName = pUsers.getUsername();
        PasswordEncoderGenerator encoder = new PasswordEncoderGenerator();
        String vPassword = encoder.passwordEncoder(pUsers.getPassword());
        String vEmail = pUsers.getEmail();
        String vSQL = "INSERT INTO users (username, password, email, enabled) VALUES (?,?,?,?)";
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vUserName, vPassword, vEmail, true);
    }

    @Override
    public Users readUsers(String pUsername) {
        return null;
    }

    @Override
    public List<Users> readAllUsers() {
        String vSQL = "SELECT * FROM users";
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        RowMapper<Users> vRowMapper = new UsersRM();
        List<Users> vListUsers = vJdbcTemplate.query(vSQL, vRowMapper);
        return vListUsers;
    }

    @Override
    public void updateUsers(Users pUsers) {

    }

    @Override
    public void deleteUsers(int pId) {

    }

}