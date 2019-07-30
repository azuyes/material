package com.bjut.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Lsgnbh {
    private String menuNo;
    private String menuName;
    private String subSysNo;
    private String fFpct;
    private String tid;
    private String state;

    @Id
    @Column(name = "MenuNo")
    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    @Basic
    @Column(name = "MenuName")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "SubSysNo")
    public String getSubSysNo() {
        return subSysNo;
    }

    public void setSubSysNo(String subSysNo) {
        this.subSysNo = subSysNo;
    }

    @Basic
    @Column(name = "F_FPCT")
    public String getfFpct() {
        return fFpct;
    }

    public void setfFpct(String fFpct) {
        this.fFpct = fFpct;
    }

    @Basic
    @Column(name = "Tid")
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "State")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lsgnbh lsgnbh = (Lsgnbh) o;
        return Objects.equals(menuNo, lsgnbh.menuNo) &&
                Objects.equals(menuName, lsgnbh.menuName) &&
                Objects.equals(subSysNo, lsgnbh.subSysNo) &&
                Objects.equals(fFpct, lsgnbh.fFpct) &&
                Objects.equals(tid, lsgnbh.tid) &&
                Objects.equals(state, lsgnbh.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuNo, menuName, subSysNo, fFpct, tid, state);
    }
}
