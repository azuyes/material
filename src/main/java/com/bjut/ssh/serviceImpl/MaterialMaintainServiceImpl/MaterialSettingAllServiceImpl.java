package com.bjut.ssh.serviceImpl.MaterialMaintainServiceImpl;

import com.bjut.ssh.dao.MaterialMaintainDao.MaterialSettingAllDao;
import com.bjut.ssh.entity.Materialclass;
import com.bjut.ssh.entity.Materialsset;
import com.bjut.ssh.service.MaterialMaintainService.MaterialSettingAllService;
import com.bjut.ssh.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialSettingAllServiceImpl implements MaterialSettingAllService {

    @Autowired
    private MaterialSettingAllDao materialSettingAllDao;

    @Override
    public List<Materialsset> queryMaterialsByClassId(String materialClassId){
        return materialSettingAllDao.queryMaterialsByClassId(materialClassId);
    }

    @Override
    public String getMaxMaterialId(String materialClassId){
        return materialSettingAllDao.getMaxMaterialId(materialClassId);
    }

    @Override
    public Msg addMaterial(Materialsset materialsset){
        if (materialSettingAllDao.addMaterial(materialsset)){
            return Msg.success();
        }else {
            Msg msg = new Msg();
            msg.setMsg("添加失败，请检编号是否重复");
            return msg;
        }
    }

    @Override
    public Msg updateMaterial(Materialsset materialsset){
        if (materialSettingAllDao.updateMaterial(materialsset)){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @Override
    public Msg deleteMaterial(String materialId){
        if (materialSettingAllDao.deleteMaterial(materialId)){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
}
