package com.bcil.twoline.model;

public class Gapscan {
    private int id;
    private String location;
    private String eanno;

    public Gapscan() {

    }

   /* public Gapscan(int id, String location, String eanno) {
        this.id = id;
        this.location = location;
        this.eanno = eanno;
    }*/

    public Gapscan(int id, String location, String eanno) {
        this.id = id;
        this.location = location;
        this.eanno = eanno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEanno() {
        return eanno;
    }

    public void setEanno(String eanno) {
        this.eanno = eanno;
    }
}


