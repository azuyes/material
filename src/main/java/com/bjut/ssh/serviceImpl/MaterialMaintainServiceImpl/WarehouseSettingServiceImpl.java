package com.bjut.ssh.serviceImpl.MaterialMaintainServiceImpl;

import com.bjut.ssh.dao.MaterialMaintainDao.WarehouseSettingDao;
import com.bjut.ssh.entity.Warehouseset;
import com.bjut.ssh.service.MaterialMaintainService.WarehouseSettingService;
import com.bjut.ssh.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseSettingServiceImpl implements WarehouseSettingService {

    @Autowired
    private WarehouseSettingDao warehouseSettingDao;

    @Override
    public List<Warehouseset> getWarehouses(){
        return warehouseSettingDao.getWarehouses();
    }

    @Override
    public Msg addWarehouse(Warehouseset warehouseset){
        if (warehouseSettingDao.addWarehouse(warehouseset)){
            return Msg.success();
        }else {
            Msg msg = new Msg();
            msg.setMsg("添加失败，请检查库房编号是否重复和部门是否存在");
            return msg;
        }
    }

    @Override
    public Msg updateWarehouse(Warehouseset warehouseset) {
        if (warehouseSettingDao.updateWarehouse(warehouseset)){
            return Msg.success();
        }else {
            Msg msg = new Msg();
            msg.setMsg("修改失败，请检部门是否存在");
            return msg;
        }
    }

    @Override
    public Msg deleteWarehouse(String warehouseId){
        if (warehouseSettingDao.deleteWarehouse(warehouseId)){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

}
