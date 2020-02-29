package org.dmc30.OCprojet6.model.bean;

import java.util.Date;

public class TopoReservation {

    private int id;
    private Date dateReservation;
    private Topo topo;
    private Users user;

    public TopoReservation() {
    }

    public TopoReservation(int id, Date dateReservation, Topo topo, Users user) {
        this.id = id;
        this.dateReservation = dateReservation;
        this.topo = topo;
        this.user = user;
    }

    public TopoReservation(Date dateReservation, Topo topo, Users user) {
        this.dateReservation = dateReservation;
        this.topo = topo;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Topo getTopo() {
        return topo;
    }

    public void setTopo(Topo topo) {
        this.topo = topo;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
