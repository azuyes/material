package com.bjut.ssh.serviceImpl.WarehouseOutServiceImpl;

import com.bjut.ssh.dao.WarehouseOutDao.WarehouseOutDao;
import com.bjut.ssh.entity.Materialstockout;
import com.bjut.ssh.service.WarehouseOutService.WarehouseOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseOutServiceImpl implements WarehouseOutService {
    @Autowired
    private WarehouseOutDao warehouseOutDao;

    @Override
    public List<Materialstockout> getWarehouseOut() {
        return warehouseOutDao.getWarehouseOut();
    }

}
