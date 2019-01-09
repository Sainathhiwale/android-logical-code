package com.bcil.gapscan.model;

public class Gapscan {
    private int id;
    private String eanno;

    public Gapscan(){

    }

    public Gapscan(int id,String eanno){
        this.id = id;
        this.eanno = eanno;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEanno() {
        return eanno;
    }

    public void setEanno(String eanno) {
        this.eanno = eanno;
    }
}
