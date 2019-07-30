package com.bjut.ssh.dao.MaterialMaintenance;

import com.bjut.ssh.entity.Materialclass;

import java.util.List;

/**
 * 物资设置
 * @author wangqin
 * @Time 2019-7-9
 */
public interface MaterialSettingDao {
    /**
     * 根据物资类别级数查询物资类别信息
     * @param i 物资类别级数
     * @return 物资类别信息列表
     */
    List<Materialclass> Inquire(int i);

    /**
     * 添加物资类别信息
     * @param materialclass 物资类别信息
     * @return 是否正确添加物资类别信息
     */
    boolean Add(Materialclass materialclass);

    /**
     * 修改物资类别信息
     * @param materialclass 物资类别信息
     * @return 是否正确修改物资类别信息
     */
    boolean Edit(Materialclass materialclass);

    /**
     * 删除物资类别信息
     * @param materialclass 物资类别信息
     * @return 是否正确删除物资类别信息
     */
    boolean Remove(Materialclass materialclass);
}
