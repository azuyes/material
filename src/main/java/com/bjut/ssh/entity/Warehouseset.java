package com.bjut.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Warehouseset {
    private String warehouseCode;
    private String departmentId;
    private String warehouseName;
    private String guard;
    private String inspector;
    private String initialFlag;

    @Id
    @Column(name = "WarehouseCode")
    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    @Basic
    @Column(name = "DepartmentID")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "WarehouseName")
    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    @Basic
    @Column(name = "Guard")
    public String getGuard() {
        return guard;
    }

    public void setGuard(String guard) {
        this.guard = guard;
    }

    @Basic
    @Column(name = "Inspector")
    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    @Basic
    @Column(name = "InitialFlag")
    public String getInitialFlag() {
        return initialFlag;
    }

    public void setInitialFlag(String initialFlag) {
        this.initialFlag = initialFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouseset that = (Warehouseset) o;
        return Objects.equals(warehouseCode, that.warehouseCode) &&
                Objects.equals(departmentId, that.departmentId) &&
                Objects.equals(warehouseName, that.warehouseName) &&
                Objects.equals(guard, that.guard) &&
                Objects.equals(inspector, that.inspector) &&
                Objects.equals(initialFlag, that.initialFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseCode, departmentId, warehouseName, guard, inspector, initialFlag);
    }
}
