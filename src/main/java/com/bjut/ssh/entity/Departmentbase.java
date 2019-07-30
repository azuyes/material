package com.bjut.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Departmentbase {
    private String departmentId;
    private String departmentName;
    private String departmentProperty;
    private Integer departmentLevel;
    private String accountCategory;
    private String accountItem;
    private String manufactureSystemDepartmentId;
    private Double allocationCoefficient;

    @Id
    @Column(name = "DepartmentID")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "DepartmentName")
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Basic
    @Column(name = "DepartmentProperty")
    public String getDepartmentProperty() {
        return departmentProperty;
    }

    public void setDepartmentProperty(String departmentProperty) {
        this.departmentProperty = departmentProperty;
    }

    @Basic
    @Column(name = "DepartmentLevel")
    public Integer getDepartmentLevel() {
        return departmentLevel;
    }

    public void setDepartmentLevel(Integer departmentLevel) {
        this.departmentLevel = departmentLevel;
    }

    @Basic
    @Column(name = "AccountCategory")
    public String getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(String accountCategory) {
        this.accountCategory = accountCategory;
    }

    @Basic
    @Column(name = "AccountItem")
    public String getAccountItem() {
        return accountItem;
    }

    public void setAccountItem(String accountItem) {
        this.accountItem = accountItem;
    }

    @Basic
    @Column(name = "ManufactureSystemDepartmentID")
    public String getManufactureSystemDepartmentId() {
        return manufactureSystemDepartmentId;
    }

    public void setManufactureSystemDepartmentId(String manufactureSystemDepartmentId) {
        this.manufactureSystemDepartmentId = manufactureSystemDepartmentId;
    }

    @Basic
    @Column(name = "AllocationCoefficient")
    public Double getAllocationCoefficient() {
        return allocationCoefficient;
    }

    public void setAllocationCoefficient(Double allocationCoefficient) {
        this.allocationCoefficient = allocationCoefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departmentbase that = (Departmentbase) o;
        return Objects.equals(departmentId, that.departmentId) &&
                Objects.equals(departmentName, that.departmentName) &&
                Objects.equals(departmentProperty, that.departmentProperty) &&
                Objects.equals(departmentLevel, that.departmentLevel) &&
                Objects.equals(accountCategory, that.accountCategory) &&
                Objects.equals(accountItem, that.accountItem) &&
                Objects.equals(manufactureSystemDepartmentId, that.manufactureSystemDepartmentId) &&
                Objects.equals(allocationCoefficient, that.allocationCoefficient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, departmentName, departmentProperty, departmentLevel, accountCategory, accountItem, manufactureSystemDepartmentId, allocationCoefficient);
    }
}
