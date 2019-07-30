package com.bjut.ssh.serviceImpl.MaterialMaintenance;

import com.bjut.ssh.dao.MaterialMaintenance.MaterialSettingDao;
import com.bjut.ssh.entity.Materialclass;
import com.bjut.ssh.service.MaterialMaintenance.MaterialSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

/**
 * 物资设置
 * @author wangqin
 * @Time 2019-7-9
 */
public class MaterialSettingServiceImpl implements MaterialSettingService {
    @Autowired
    private MaterialSettingDao materialSettingDao;

    /**
     * 根据物资类别级数查询物资类别信息
     * @param i 物资类别级数
     * @return 物资类别信息列表
     */
    @Override
    public List<Materialclass> Inquire(int i) {
        return materialSettingDao.Inquire(i);
    }

    /**
     * 添加物资类别信息
     * @param materialclass 物资类别信息
     * @return 是否正确添加物资类别信息
     */
    @Override
    public boolean Add(Materialclass materialclass) {
        return materialSettingDao.Add(materialclass);
    }

    /**
     * 修改物资类别信息
     * @param materialclass 物资类别信息
     * @return 是否正确修改物资类别信息
     */
    @Override
    public boolean Edit(Materialclass materialclass) {
        return materialSettingDao.Edit(materialclass);
    }

    /**
     * 删除物资类别信息
     * @param materialclass 物资类别信息
     * @return 是否正确删除物资类别信息
     */
    @Override
    public boolean Remove(Materialclass materialclass) {
        return materialSettingDao.Remove(materialclass);
    }
}
