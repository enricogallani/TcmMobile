package com.example.front.db.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private String name;
    private String mail;
    private String office;
    private String password;
    private Boolean isAdm;

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAdm() {
        return isAdm;
    }

    public void setAdm(Boolean adm) {
        isAdm = adm;
    }
}
