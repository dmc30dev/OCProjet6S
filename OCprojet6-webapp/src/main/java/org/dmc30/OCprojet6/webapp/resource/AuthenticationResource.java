package org.dmc30.OCprojet6.webapp.resource;


import org.dmc30.OCprojet6.model.bean.Users;

import javax.inject.Named;

@Named
public class AuthenticationResource extends AbstractResource{

    public void createUsers (Users pUsers) {
        getManagerFactory().getUsersManager().createUsers(pUsers);
    }

    public Users getUserByName (String pUsername) {
        return getManagerFactory().getUsersManager().getUsersByName(pUsername);
    }

    public int[] rechercheDoublon (Users pUsers) {
        return getManagerFactory().getUsersManager().rechercheDoublon(pUsers);
    }


}