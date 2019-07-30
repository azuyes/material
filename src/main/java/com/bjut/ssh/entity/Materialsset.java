package com.bjut.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Materialsset {
    private String materialName;
    private String materialId;
    private String specification2;
    private String measureUnit2;
    private Double planPriceUnit;

    @Basic
    @Column(name = "MaterialName")
    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Id
    @Column(name = "MaterialID")
    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    @Basic
    @Column(name = "Specification2")
    public String getSpecification2() {
        return specification2;
    }

    public void setSpecification2(String specification2) {
        this.specification2 = specification2;
    }

    @Basic
    @Column(name = "MeasureUnit2")
    public String getMeasureUnit2() {
        return measureUnit2;
    }

    public void setMeasureUnit2(String measureUnit2) {
        this.measureUnit2 = measureUnit2;
    }

    @Basic
    @Column(name = "PlanPrice_Unit")
    public Double getPlanPriceUnit() {
        return planPriceUnit;
    }

    public void setPlanPriceUnit(Double planPriceUnit) {
        this.planPriceUnit = planPriceUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materialsset that = (Materialsset) o;
        return Objects.equals(materialName, that.materialName) &&
                Objects.equals(materialId, that.materialId) &&
                Objects.equals(specification2, that.specification2) &&
                Objects.equals(measureUnit2, that.measureUnit2) &&
                Objects.equals(planPriceUnit, that.planPriceUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialName, materialId, specification2, measureUnit2, planPriceUnit);
    }
}
