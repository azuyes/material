package com.bjut.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Materialstockout {
    private String id;
    private String materialName;
    private String stockOutId;
    private String planId;
    private String departmentNo;
    private String customerNo;
    private String materialSpecification;
    private String measureUnit;
    private BigDecimal applyQuantity;
    private BigDecimal factQuantity;
    private Double unitPrice;
    private Double money;
    private String upperMoney;
    private String userPlace;
    private String note;
    private String requisitionUnitPerson;
    private String authority;
    private String llPer;
    private String flPer;
    private String audit;
    private Date stockOutDate;
    private String requisitionDepartmentNo;

    @Id
    @Column(name = "ID", nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MaterialName", nullable = true, length = 50)
    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Basic
    @Column(name = "StockOutID", nullable = true, length = 12)
    public String getStockOutId() {
        return stockOutId;
    }

    public void setStockOutId(String stockOutId) {
        this.stockOutId = stockOutId;
    }

    @Basic
    @Column(name = "PlanID", nullable = true, length = 12)
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "DepartmentNo", nullable = true, length = 9)
    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    @Basic
    @Column(name = "CustomerNo", nullable = true, length = 6)
    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    @Basic
    @Column(name = "MaterialSpecification", nullable = true, length = 30)
    public String getMaterialSpecification() {
        return materialSpecification;
    }

    public void setMaterialSpecification(String materialSpecification) {
        this.materialSpecification = materialSpecification;
    }

    @Basic
    @Column(name = "MeasureUnit", nullable = true, length = 10)
    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    @Basic
    @Column(name = "ApplyQuantity", nullable = true, precision = 4)
    public BigDecimal getApplyQuantity() {
        return applyQuantity;
    }

    public void setApplyQuantity(BigDecimal applyQuantity) {
        this.applyQuantity = applyQuantity;
    }

    @Basic
    @Column(name = "FactQuantity", nullable = true, precision = 4)
    public BigDecimal getFactQuantity() {
        return factQuantity;
    }

    public void setFactQuantity(BigDecimal factQuantity) {
        this.factQuantity = factQuantity;
    }

    @Basic
    @Column(name = "UnitPrice", nullable = true, precision = 0)
    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Basic
    @Column(name = "Money", nullable = true, precision = 0)
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Basic
    @Column(name = "UpperMoney", nullable = true, length = 100)
    public String getUpperMoney() {
        return upperMoney;
    }

    public void setUpperMoney(String upperMoney) {
        this.upperMoney = upperMoney;
    }

    @Basic
    @Column(name = "UserPlace", nullable = true, length = 100)
    public String getUserPlace() {
        return userPlace;
    }

    public void setUserPlace(String userPlace) {
        this.userPlace = userPlace;
    }

    @Basic
    @Column(name = "Note", nullable = true, length = 200)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "RequisitionUnitPerson", nullable = true, length = 10)
    public String getRequisitionUnitPerson() {
        return requisitionUnitPerson;
    }

    public void setRequisitionUnitPerson(String requisitionUnitPerson) {
        this.requisitionUnitPerson = requisitionUnitPerson;
    }

    @Basic
    @Column(name = "Authority", nullable = true, length = 10)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Basic
    @Column(name = "LLPer", nullable = true, length = 10)
    public String getLlPer() {
        return llPer;
    }

    public void setLlPer(String llPer) {
        this.llPer = llPer;
    }

    @Basic
    @Column(name = "FLPer", nullable = true, length = 10)
    public String getFlPer() {
        return flPer;
    }

    public void setFlPer(String flPer) {
        this.flPer = flPer;
    }

    @Basic
    @Column(name = "Audit", nullable = true, length = 50)
    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    @Basic
    @Column(name = "StockOutDate", nullable = true)
    public Date getStockOutDate() {
        return stockOutDate;
    }

    public void setStockOutDate(Date stockOutDate) {
        this.stockOutDate = stockOutDate;
    }

    @Basic
    @Column(name = "RequisitionDepartmentNo", nullable = true, length = 9)
    public String getRequisitionDepartmentNo() {
        return requisitionDepartmentNo;
    }

    public void setRequisitionDepartmentNo(String requisitionDepartmentNo) {
        this.requisitionDepartmentNo = requisitionDepartmentNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materialstockout that = (Materialstockout) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(materialName, that.materialName) &&
                Objects.equals(stockOutId, that.stockOutId) &&
                Objects.equals(planId, that.planId) &&
                Objects.equals(departmentNo, that.departmentNo) &&
                Objects.equals(customerNo, that.customerNo) &&
                Objects.equals(materialSpecification, that.materialSpecification) &&
                Objects.equals(measureUnit, that.measureUnit) &&
                Objects.equals(applyQuantity, that.applyQuantity) &&
                Objects.equals(factQuantity, that.factQuantity) &&
                Objects.equals(unitPrice, that.unitPrice) &&
                Objects.equals(money, that.money) &&
                Objects.equals(upperMoney, that.upperMoney) &&
                Objects.equals(userPlace, that.userPlace) &&
                Objects.equals(note, that.note) &&
                Objects.equals(requisitionUnitPerson, that.requisitionUnitPerson) &&
                Objects.equals(authority, that.authority) &&
                Objects.equals(llPer, that.llPer) &&
                Objects.equals(flPer, that.flPer) &&
                Objects.equals(audit, that.audit) &&
                Objects.equals(stockOutDate, that.stockOutDate) &&
                Objects.equals(requisitionDepartmentNo, that.requisitionDepartmentNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, materialName, stockOutId, planId, departmentNo, customerNo, materialSpecification, measureUnit, applyQuantity, factQuantity, unitPrice, money, upperMoney, userPlace, note, requisitionUnitPerson, authority, llPer, flPer, audit, stockOutDate, requisitionDepartmentNo);
    }
}
