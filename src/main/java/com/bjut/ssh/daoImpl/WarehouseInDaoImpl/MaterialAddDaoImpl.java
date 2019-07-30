package com.bjut.ssh.daoImpl.WarehouseInDaoImpl;

import com.bjut.ssh.dao.WarehouseInDao.MaterialAddDao;
import com.bjut.ssh.entity.Materialclass;
import com.bjut.ssh.entity.Materialsset;
import com.bjut.ssh.entity.Materialstockin;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class MaterialAddDaoImpl implements MaterialAddDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return this.sessionFactory.openSession();
    }

    public void close(Session session){
        if(session != null)
            session.close();
    }


    /**
     * @description 获取materialclass表中level相同的所有数据
     * @param level
     * @return
     */
    @Override
    public List<Materialclass> getMaterialClass(Integer level) {
        String hql;
        Session session = null;
        Transaction tx = null;
        List<Materialclass> materialclassList = null;

        try{
            session = getSession();
            tx = session.beginTransaction();

            hql = "from Materialclass where MaterialClassLevel ='"+level+"'";
            Query query = session.createQuery(hql);
            materialclassList = query.list();
            tx.commit();

        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            close(session);
            return materialclassList;
        }

    }

    /**
     * @description 获取materialclass表中id类似(即相同公司)的所有数据个数
     * @param id
     * @return
     */
    public  Integer classSize(String id){
        Session session = null;
        Transaction tx = null;
        List<Materialclass> materialclasslist = null;
        String hql = null;
        try{
            session = getSession();
            hql = "from Materialclass where materialClassId like '" + id + "%'";
            Query query = session.createQuery(hql);
            materialclasslist = query.list();

        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            close(session);
            return materialclasslist.size();

        }

    }


    /**
     * @desciption 本函数查找materialclass表中id和level符合的所有数据
     * @param id
     * @param level
     * @return 符合条件的数据列表
     */
    @Override
    public List<Materialclass> getAssetClassByIdAndLevel(String id, String level) {
        String hql;
        Session session = null;
        Transaction tx = null;
        List<Materialclass> materialclassList = null;

        try{
            session = getSession();
            tx = session.beginTransaction();

            if(level.equals("1")){
                hql = "from Materialclass where materialClassLevel ='"+level+"'";
            }else {
                hql = "from Materialclass where materialClassId like '" + id + "%' and materialClassLevel ='" + level + "'";
            }

            Query query = session.createQuery(hql);
            materialclassList = query.list();
            tx.commit();

        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            close(session);
            return materialclassList;
        }
    }


    /**
     * @description 本函数根据传入的id，到materialset表中查找表中MaterialID字段前6位相同的条目
     * @param id 即物资类别，MaterialID中的前6位
     * @return 符合条件的数据集合
     * @author Su
     * @date 2019/7/15
     */
    @Override
    public List<Materialsset> getMaterialDetailsById(String id) {

        String hql = null;
        Session session = null;
        Transaction tx = null;
        List<Materialsset> materialssetList = null;

        try{
            session = getSession();
            tx = session.beginTransaction();

            hql = "from Materialsset where materialId like '" + id +"%'";

            Query query = session.createQuery(hql);
            materialssetList = query.list();
            tx.commit();

        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            close(session);
            return materialssetList;
        }

    }


    /**
     * @description 添加物资明细时，调用本函数查询当前最后一个物资的编号是多少。目的是达到id自增的效果。
     * @param id 传进来的是substring操作后的materialId
     * @return
     * @author Su
     * @date 2019/7/16
     */
    @Override
    public List<String> getMaxMaterialId(String id) {
        String hql = null;
        Session session = null;
        Transaction tx = null;
        List<String> materialIds = null;

        try{
            session = getSession();
            tx = session.beginTransaction();

            hql = "select MAX(materialId) from Materialsset where materialId like'" + id + "%'";

            Query query = session.createQuery(hql);
            materialIds = query.list();
            tx.commit();

        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            close(session);
            return materialIds;
        }

    }


    /**
     * @description 本函数用于向Materialsset表中插入数据
     * @param materialstockin
     * @return
     */
    @Override
    public boolean addMaterialDetail(Materialstockin materialstockin) {
        Session session = null;
        Transaction tx = null;

        //为对象指定id
        materialstockin.setId(UUID.randomUUID().toString());

        try{
            session = getSession();
            tx = session.beginTransaction();

            session.save(materialstockin);

            tx.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
            return false;
        }finally {
            close(session);
        }
    }


    @Override
    public List<Materialstockin> getMaterialStockIn() {
        Session session = null;
        Transaction tx = null;
        List<Materialstockin> result = null;
        String hql;

        try{
            session = getSession();
            tx = session.beginTransaction();

            hql = "from Materialstockin";

            Query query = session.createQuery(hql);
            result = query.list();
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            close(session);
            return result;
        }


    }
}
