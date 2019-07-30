package com.bjut.ssh.daoImpl.MaterialMaintainDaoImpl;

import com.bjut.ssh.GeneralCRUD.GeneralCRUD;
import com.bjut.ssh.dao.MaterialMaintainDao.MaterialSettingAllDao;
import com.bjut.ssh.entity.Materialsset;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterialSettingAllDaoImpl implements MaterialSettingAllDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GeneralCRUD generalCRUD;

    public Session getSession(){
        return this.sessionFactory.openSession();
    }

    public void close(Session session){
        if(session != null)
            session.close();
    }

    @Override
    public List<Materialsset> queryMaterialsByClassId(String materialClassId){
        Session session = getSession();
        Transaction tx = null;

        List<Materialsset> materials = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from Materialsset where materialId like '"+materialClassId+"%'");
            materials = query.list();
            tx.commit();
        }catch (RuntimeException e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            return materials;
        }
    }

    /**
     * 获得当前物资种类下的最大物资编号，若当前终类下没有物资则编号为物资种类id+'000000'
     * @param materialClassId
     * @return
     */
    @Override
    public String getMaxMaterialId(String materialClassId){
        Session session = getSession();
        Transaction tx = null;
        String maxMaterialId = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("select max(materialId) from Materialsset where materialId like '"+materialClassId+"%'");
            maxMaterialId = (String) query.uniqueResult();
            if (maxMaterialId==null){
                maxMaterialId = materialClassId+"000000";
            }
            tx.commit();
        }catch (RuntimeException e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            return maxMaterialId;
        }
    }


    @Override
    public boolean addMaterial(Materialsset materialsset){
        Session session = getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(materialsset);
            tx.commit();
            return true;
        }catch (RuntimeException e){
            tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            close(session);
        }
    }

    @Override
    public boolean updateMaterial(Materialsset materialsset){
        Session session = getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(materialsset);
            tx.commit();
            return true;
        }catch (RuntimeException e){
            tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            close(session);
        }
    }

    @Override
    public boolean deleteMaterial(String materialId){
        return generalCRUD.delete("Materialsset",materialId);
    }

}