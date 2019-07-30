package com.bjut.ssh.serviceImpl.InboundManagementServiceImpl;

import com.bjut.ssh.daoImpl.WarehouseInDaoImpl.MaterialAddDaoImpl;
import com.bjut.ssh.entity.Materialclass;
import com.bjut.ssh.entity.Materialsset;
import com.bjut.ssh.entity.Materialstockin;
import com.bjut.ssh.service.InboundManagementService.MaterialAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MaterialAddServiceImpl implements MaterialAddService {

    @Autowired
    private MaterialAddDaoImpl materialAddDao;

    @Override
    public List<Materialclass> getMaterialClass(Integer level) {
        return materialAddDao.getMaterialClass(level);
    }

    @Override
    public Integer classSize(String id) {
            return materialAddDao.classSize(id);

    }

    @Override
    public List<Materialclass> getAssetClassByIdAndLevel(String id, String level) {
        return materialAddDao.getAssetClassByIdAndLevel(id,level);
    }

    @Override
    public List<Materialsset> getMaterialDetailsById(String id) {
        return materialAddDao.getMaterialDetailsById(id);
    }

    @Override
    public List<String> getMaxMaterialId(String id) {
        return materialAddDao.getMaxMaterialId(id);
    }

    @Override
    public boolean addMaterialDetail(Materialstockin materialstockin) {
        return materialAddDao.addMaterialDetail(materialstockin);
    }

    @Override
    public List<Materialstockin> getMaterialStockIn() {
        return materialAddDao.getMaterialStockIn();
    }
}
