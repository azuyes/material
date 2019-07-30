package com.bjut.ssh.service.InboundManagementService;

import com.bjut.ssh.entity.Materialclass;
import com.bjut.ssh.entity.Materialsset;
import com.bjut.ssh.entity.Materialstockin;

import java.util.List;

public interface MaterialAddService {

    List<Materialclass> getMaterialClass(Integer level);

    Integer classSize(String id);

    List<Materialclass> getAssetClassByIdAndLevel(String id,String level);

    List<Materialsset> getMaterialDetailsById(String id);

    List<String> getMaxMaterialId(String id);

    boolean addMaterialDetail(Materialstockin materialstockin);

    List<Materialstockin> getMaterialStockIn();
}
