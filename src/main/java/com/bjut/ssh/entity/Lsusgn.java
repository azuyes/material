package com.bjut.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Lsusgn {
    private int usgnAutoId;
    private String userNo;
    private String menuNo;

    @Id
    @Column(name = "USGN_AutoId")
    public int getUsgnAutoId() {
        return usgnAutoId;
    }

    public void setUsgnAutoId(int usgnAutoId) {
        this.usgnAutoId = usgnAutoId;
    }

    @Basic
    @Column(name = "UserNo")
    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    @Basic
    @Column(name = "MenuNo")
    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lsusgn lsusgn = (Lsusgn) o;
        return usgnAutoId == lsusgn.usgnAutoId &&
                Objects.equals(userNo, lsusgn.userNo) &&
                Objects.equals(menuNo, lsusgn.menuNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usgnAutoId, userNo, menuNo);
    }
}
