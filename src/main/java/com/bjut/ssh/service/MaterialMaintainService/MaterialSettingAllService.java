package com.bjut.ssh.service.MaterialMaintainService;

import com.bjut.ssh.entity.Materialsset;
import com.bjut.ssh.utils.Msg;

import java.util.List;

public interface MaterialSettingAllService {
    List<Materialsset> queryMaterialsByClassId(String materialClassId);

    String getMaxMaterialId(String materialClassId);

    Msg addMaterial(Materialsset materialsset);

    Msg updateMaterial(Materialsset materialsset);

    Msg deleteMaterial(String materialId);
}
