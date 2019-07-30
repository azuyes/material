package com.bjut.ssh.service.MaterialMaintainService;

import com.bjut.ssh.entity.Warehouseset;
import com.bjut.ssh.utils.Msg;

import java.util.List;

public interface WarehouseSettingService {
    List<Warehouseset> getWarehouses();

    Msg addWarehouse(Warehouseset warehouseset);

    Msg updateWarehouse(Warehouseset warehouseset);

    Msg deleteWarehouse(String warehouseId);
}
