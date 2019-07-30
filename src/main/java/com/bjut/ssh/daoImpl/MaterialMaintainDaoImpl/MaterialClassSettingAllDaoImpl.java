package com.bjut.ssh.daoImpl.MaterialMaintainDaoImpl;

import com.bjut.ssh.GeneralCRUD.GeneralCRUD;
import com.bjut.ssh.dao.MaterialMaintainDao.MaterialClassSettingAllDao;
import com.bjut.ssh.entity.Materialclass;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterialClassSettingAllDaoImpl implements MaterialClassSettingAllDao {

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

    /**
     * 根据materialClassId和materialClassLevel查询当前物资类别下的数据
     * @param materialClassId
     * @param materialClassLevel
     * @return
     */
    @Override
    public List<Materialclass> queryMaterialClassByIdAndLevel(String materialClassId,int materialClassLevel){
        Session session = getSession();
        Transaction tx = null;
        String queryCode1 = null;//根据queryCode查询物资类别编号
        String queryCode2 = null;//根据queryCode查询物资类别编号
        List<Materialclass> materialclasses = null;
        try{
            tx = session.beginTransaction();
            if (materialClassLevel == 1){
                queryCode1 = "[0-9][0-9]0000";
                queryCode2 = "[0-9][0-9]0000";
            }else if (materialClassLevel == 2){
                queryCode1 = materialClassId.substring(0,2)+"[1-9][0-9]00";
                queryCode2 = materialClassId.substring(0,2)+"[0-9][1-9]00";
                System.out.println(queryCode1+"-----"+queryCode2);
            }else if (materialClassLevel == 3){
                queryCode1 = materialClassId.substring(0,4)+"[1-9][0-9]";
                queryCode2 = materialClassId.substring(0,4)+"[0-9][1-9]";
            }
            String sql = "select * from Materialclass where materialClassId REGEXP :queryCode1 or materialClassId REGEXP :queryCode2";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Materialclass.class);
            query.setParameter("queryCode1",queryCode1);
            query.setParameter("queryCode2",queryCode2);
            materialclasses = query.list();
            tx.commit();
        }catch (RuntimeException e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            return materialclasses;
        }
    }

    @Override
    public boolean addMaterialClass(Materialclass materialclass){
        Session session = getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(materialclass);
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
    public boolean updateMaterialClass(Materialclass materialclass){
        Session session = getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(materialclass);
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
    public boolean deleteMaterialClass(String materialClassId){
        return generalCRUD.delete("Materialclass",materialClassId);
    }

}
