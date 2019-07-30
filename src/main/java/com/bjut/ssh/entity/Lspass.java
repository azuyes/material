package com.bjut.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Lspass {
    private String userNo;
    private String userPass;
    private String userName;
    private String userId;
    private String userNote;
    private String department;
    private Short fSykm;
    private Short fSybb;
    private String functionauthority;
    private String departId;

    @Id
    @Column(name = "UserNo")
    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    @Basic
    @Column(name = "UserPass")
    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Basic
    @Column(name = "UserName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "UserID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "UserNote")
    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    @Basic
    @Column(name = "Department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "F_SYKM")
    public Short getfSykm() {
        return fSykm;
    }

    public void setfSykm(Short fSykm) {
        this.fSykm = fSykm;
    }

    @Basic
    @Column(name = "F_SYBB")
    public Short getfSybb() {
        return fSybb;
    }

    public void setfSybb(Short fSybb) {
        this.fSybb = fSybb;
    }

    @Basic
    @Column(name = "Functionauthority")
    public String getFunctionauthority() {
        return functionauthority;
    }

    public void setFunctionauthority(String functionauthority) {
        this.functionauthority = functionauthority;
    }

    @Basic
    @Column(name = "DepartId")
    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lspass lspass = (Lspass) o;
        return Objects.equals(userNo, lspass.userNo) &&
                Objects.equals(userPass, lspass.userPass) &&
                Objects.equals(userName, lspass.userName) &&
                Objects.equals(userId, lspass.userId) &&
                Objects.equals(userNote, lspass.userNote) &&
                Objects.equals(department, lspass.department) &&
                Objects.equals(fSykm, lspass.fSykm) &&
                Objects.equals(fSybb, lspass.fSybb) &&
                Objects.equals(functionauthority, lspass.functionauthority) &&
                Objects.equals(departId, lspass.departId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, userPass, userName, userId, userNote, department, fSykm, fSybb, functionauthority, departId);
    }
}
