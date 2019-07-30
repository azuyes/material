package com.bjut.ssh.serviceImpl.MaterialMaintainServiceImpl;

import com.bjut.ssh.dao.MaterialMaintainDao.MaterialClassSettingAllDao;
import com.bjut.ssh.entity.Materialclass;
import com.bjut.ssh.service.MaterialMaintainService.MaterialClassSettingAllService;
import com.bjut.ssh.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialClassSettingAllServiceImpl implements MaterialClassSettingAllService {

    @Autowired
    private MaterialClassSettingAllDao materialClassSettingAllDao;

    @Override
    public List<Materialclass> queryMaterialClassByIdAndLevel(String materialClassId, int materialClassLevel){
        return materialClassSettingAllDao.queryMaterialClassByIdAndLevel(materialClassId,materialClassLevel);
    }

    @Override
    public Msg addMaterialClass(Materialclass materialclass){
        if (materialClassSettingAllDao.addMaterialClass(materialclass)){
            return Msg.success();
        }else {
            Msg msg = new Msg();
            msg.setMsg("添加失败，请检查房编号是否重复");
            return msg;
        }
    }

    @Override
    public Msg updateMaterialClass(Materialclass materialclass){
        if (materialClassSettingAllDao.updateMaterialClass(materialclass)){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @Override
    public Msg deleteMaterialClass(String materialclassId){
        if (materialClassSettingAllDao.deleteMaterialClass(materialclassId)){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
}
