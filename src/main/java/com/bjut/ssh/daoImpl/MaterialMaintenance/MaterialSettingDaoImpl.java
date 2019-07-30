package com.bjut.ssh.daoImpl.MaterialMaintenance;

import com.bjut.ssh.dao.MaterialMaintenance.MaterialSettingDao;
import com.bjut.ssh.entity.Materialclass;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

/**
 * 物资设置
 * @author wangqin
 * @Time 2019-7-9
 */
public class MaterialSettingDaoImpl implements MaterialSettingDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return this.sessionFactory.openSession();
    }

    public void close(Session session) {
        if (session != null)
            session.close();
    }

    /**
     * 根据物资类别级数查询物资类别信息
     * @param i 物资类别级数
     * @return 物资类别信息列表
     */
    @Override
    public List<Materialclass> Inquire(int i) {
        Session session = getSession();
        List<Materialclass> list = new ArrayList<Materialclass>();

        try {
            Query query = session.createQuery("from Materialclass where materialClassLevel = :level");
            query.setInteger("level", i);
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(session);
        }
        return list;
    }

    /**
     * 添加物资类别信息
     * @param materialclass 物资类别信息
     * @return 是否正确添加物资类别信息
     */
    @Override
    public boolean Add(Materialclass materialclass) {
        Session session = getSession();
        Transaction ts = session.beginTransaction();
        boolean bRet = false;

        try {
            session.save(materialclass);
            ts.commit();
            bRet = true;
        } catch (Exception e) {
            e.printStackTrace();
            ts.rollback();
        } finally {
            close(session);
        }
        return bRet;
    }

    /**
     * 修改物资类别信息
     * @param materialclass 物资类别信息
     * @return 是否正确修改物资类别信息
     */
    @Override
    public boolean Edit(Materialclass materialclass) {
        Session session = getSession();
        Transaction ts = session.beginTransaction();
        boolean bRet = false;

        try {
            session.update(materialclass);
            ts.commit();
            bRet = true;
        } catch (Exception e) {
            e.printStackTrace();
            ts.rollback();
        } finally {
            close(session);
        }
        return bRet;
    }

    /**
     * 删除物资类别信息
     * @param materialclass 物资类别信息
     * @return 是否正确删除物资类别信息
     */
    @Override
    public boolean Remove(Materialclass materialclass) {
        Session session = getSession();
        Transaction ts = session.beginTransaction();
        int level = materialclass.getMaterialClassLevel();
        boolean bRet = false;

        try {
            Query query = session.createQuery("from Materialclass where materialClassId like :id and materialClassLevel = :level");
            query.setString("id", materialclass.getMaterialClassId().substring(0, 2) + "%");
            query.setInteger("level", level + 1);
            if(query.list().size() == 0) {        // 该物资类别下一级没有物资类别时才允许删除物资类别信息
                session.delete(materialclass);
                ts.commit();
                bRet = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ts.rollback();
        } finally {
            close(session);
        }
        return bRet;
    }
}
