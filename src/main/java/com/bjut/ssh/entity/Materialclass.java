package com.bjut.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Materialclass {
    private String materialClassId;
    private String materialClassName;
    private Integer materialClassLevel;

    @Id
    @Column(name = "MaterialClassID")
    public String getMaterialClassId() {
        return materialClassId;
    }

    public void setMaterialClassId(String materialClassId) {
        this.materialClassId = materialClassId;
    }

    @Basic
    @Column(name = "MaterialClassName")
    public String getMaterialClassName() {
        return materialClassName;
    }

    public void setMaterialClassName(String materialClassName) {
        this.materialClassName = materialClassName;
    }

    @Basic
    @Column(name = "MaterialClassLevel")
    public Integer getMaterialClassLevel() {
        return materialClassLevel;
    }

    public void setMaterialClassLevel(Integer materialClassLevel) {
        this.materialClassLevel = materialClassLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materialclass that = (Materialclass) o;
        return Objects.equals(materialClassId, that.materialClassId) &&
                Objects.equals(materialClassName, that.materialClassName) &&
                Objects.equals(materialClassLevel, that.materialClassLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialClassId, materialClassName, materialClassLevel);
    }
}
