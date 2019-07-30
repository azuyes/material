package com.bjut.ssh.dao.MaterialMaintainDao;

import com.bjut.ssh.entity.Warehouseset;

import java.util.List;

public interface WarehouseSettingDao {
    List<Warehouseset> getWarehouses();

    boolean updateWarehouse(Warehouseset warehouseset);

    boolean addWarehouse(Warehouseset warehouseset);

    boolean deleteWarehouse(String warehouseId);
}
