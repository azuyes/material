package com.bjut.ssh.service.MaterialMaintainService;

import com.bjut.ssh.entity.Materialclass;
import com.bjut.ssh.utils.Msg;

import java.util.List;

public interface MaterialClassSettingAllService {
    List<Materialclass> queryMaterialClassByIdAndLevel(String materialClassId, int materialClassLevel);

    Msg addMaterialClass(Materialclass materialclass);

    Msg updateMaterialClass(Materialclass materialclass);

    Msg deleteMaterialClass(String materialclassId);
}
