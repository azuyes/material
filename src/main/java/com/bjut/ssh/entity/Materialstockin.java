package com.bjut.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Materialstockin {
    private String id;
    private String materialName;
    private Date stockInDate;
    private String stockInCode;
    private String planCode;
    private Date examinationData;
    private String examinationPlace;
    private String contractCode;
    private String materialApplication;
    private String specification2;
    private String measureUnit2;
    private Integer yssl;
    private Integer sssl;
    private Double unitPrice;
    private Double cost;
    private String note2;
    private String guard;
    private String inspector;
    private String salesMan;
    private String supplierUnit;

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
    @Column(name = "StockInDate", nullable = true)
    public Date getStockInDate() {
        return stockInDate;
    }

    public void setStockInDate(Date stockInDate) {
        this.stockInDate = stockInDate;
    }

    @Basic
    @Column(name = "StockInCode", nullable = true, length = 12)
    public String getStockInCode() {
        return stockInCode;
    }

    public void setStockInCode(String stockInCode) {
        this.stockInCode = stockInCode;
    }

    @Basic
    @Column(name = "PlanCode", nullable = true, length = 12)
    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    @Basic
    @Column(name = "ExaminationData", nullable = true)
    public Date getExaminationData() {
        return examinationData;
    }

    public void setExaminationData(Date examinationData) {
        this.examinationData = examinationData;
    }

    @Basic
    @Column(name = "ExaminationPlace", nullable = true, length = 100)
    public String getExaminationPlace() {
        return examinationPlace;
    }

    public void setExaminationPlace(String examinationPlace) {
        this.examinationPlace = examinationPlace;
    }

    @Basic
    @Column(name = "ContractCode", nullable = true, length = 50)
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Basic
    @Column(name = "MaterialApplication", nullable = true, length = 50)
    public String getMaterialApplication() {
        return materialApplication;
    }

    public void setMaterialApplication(String materialApplication) {
        this.materialApplication = materialApplication;
    }

    @Basic
    @Column(name = "Specification2", nullable = true, length = 50)
    public String getSpecification2() {
        return specification2;
    }

    public void setSpecification2(String specification2) {
        this.specification2 = specification2;
    }

    @Basic
    @Column(name = "MeasureUnit2", nullable = true, length = 10)
    public String getMeasureUnit2() {
        return measureUnit2;
    }

    public void setMeasureUnit2(String measureUnit2) {
        this.measureUnit2 = measureUnit2;
    }

    @Basic
    @Column(name = "YSSL", nullable = true)
    public Integer getYssl() {
        return yssl;
    }

    public void setYssl(Integer yssl) {
        this.yssl = yssl;
    }

    @Basic
    @Column(name = "SSSL", nullable = true)
    public Integer getSssl() {
        return sssl;
    }

    public void setSssl(Integer sssl) {
        this.sssl = sssl;
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
    @Column(name = "Cost", nullable = true, precision = 0)
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "Note2", nullable = true, length = 100)
    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    @Basic
    @Column(name = "Guard", nullable = true, length = 10)
    public String getGuard() {
        return guard;
    }

    public void setGuard(String guard) {
        this.guard = guard;
    }

    @Basic
    @Column(name = "Inspector", nullable = true, length = 10)
    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    @Basic
    @Column(name = "SalesMan", nullable = true, length = 10)
    public String getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan;
    }

    @Basic
    @Column(name = "SupplierUnit", nullable = true, length = 100)
    public String getSupplierUnit() {
        return supplierUnit;
    }

    public void setSupplierUnit(String supplierUnit) {
        this.supplierUnit = supplierUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materialstockin that = (Materialstockin) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(materialName, that.materialName) &&
                Objects.equals(stockInDate, that.stockInDate) &&
                Objects.equals(stockInCode, that.stockInCode) &&
                Objects.equals(planCode, that.planCode) &&
                Objects.equals(examinationData, that.examinationData) &&
                Objects.equals(examinationPlace, that.examinationPlace) &&
                Objects.equals(contractCode, that.contractCode) &&
                Objects.equals(materialApplication, that.materialApplication) &&
                Objects.equals(specification2, that.specification2) &&
                Objects.equals(measureUnit2, that.measureUnit2) &&
                Objects.equals(yssl, that.yssl) &&
                Objects.equals(sssl, that.sssl) &&
                Objects.equals(unitPrice, that.unitPrice) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(note2, that.note2) &&
                Objects.equals(guard, that.guard) &&
                Objects.equals(inspector, that.inspector) &&
                Objects.equals(salesMan, that.salesMan) &&
                Objects.equals(supplierUnit, that.supplierUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, materialName, stockInDate, stockInCode, planCode, examinationData, examinationPlace, contractCode, materialApplication, specification2, measureUnit2, yssl, sssl, unitPrice, cost, note2, guard, inspector, salesMan, supplierUnit);
    }
}
