package com.bjut.ssh.dao.MaterialMaintainDao;

import com.bjut.ssh.entity.Materialclass;

import java.util.List;

public interface MaterialClassSettingAllDao {
    List<Materialclass> queryMaterialClassByIdAndLevel(String materialClassId, int materialClassLevel);

    boolean addMaterialClass(Materialclass materialclass);

    boolean updateMaterialClass(Materialclass materialclass);

    boolean deleteMaterialClass(String materialclassId);
}
