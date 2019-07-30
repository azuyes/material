package com.bjut.ssh.dao.MaterialMaintainDao;

import com.bjut.ssh.entity.Materialsset;

import java.util.List;

public interface MaterialSettingAllDao {
    List<Materialsset> queryMaterialsByClassId(String materialClassId);

    String getMaxMaterialId(String materialClassId);

    boolean addMaterial(Materialsset materialsset);

    boolean updateMaterial(Materialsset materialsset);

    boolean deleteMaterial(String materialId);
}
