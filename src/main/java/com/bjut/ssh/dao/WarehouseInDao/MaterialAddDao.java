package com.bjut.ssh.dao.WarehouseInDao;

import com.bjut.ssh.entity.Materialclass;
import com.bjut.ssh.entity.Materialsset;
import com.bjut.ssh.entity.Materialstockin;

import java.util.List;

public interface MaterialAddDao {
    List<Materialclass> getMaterialClass(Integer level);

    List<Materialclass> getAssetClassByIdAndLevel(String id,String level);

    List<Materialsset> getMaterialDetailsById(String id);

    List<String> getMaxMaterialId(String id);

    boolean addMaterialDetail(Materialstockin materialstockin);

    List<Materialstockin> getMaterialStockIn();
}
