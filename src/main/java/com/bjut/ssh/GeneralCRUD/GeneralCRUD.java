package com.bjut.ssh.GeneralCRUD;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GeneralCRUD {//extends HibernateDaoSupport {
    @Autowired
    SessionFactory sessionFactory;
    final String entityPath="com.bjut.ssh.entity.";

    public GeneralCRUD(){

    }

    /**
     *    通用查方法，获取所有
     *    参数：session，表名（想从哪张表里查）
     * @param
     * @param entityType
     * @param <T>
     * @return
     */
    public <T> List<T> getMethod(String entityType){
        List<T> list=null;
        Session session=this.sessionFactory.openSession();
        try{
            String sqlStatement="from  "+ entityType +"";
            Query query = sessionFactory.openSession().createQuery(sqlStatement);
            list=query.list();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            return list;
        }
    }

    /**
     * 通用修改或增加
     *
     * @param
     * @param entityType 类型是pojo对应类型
     * @param <T>
     * @return
     */
    public <T> boolean saveOrUpdate(T entityType){
        Transaction ts = null;
        Session session=this.sessionFactory.openSession();
        try{
            ts = session.beginTransaction();
            session.saveOrUpdate(entityType);
            ts.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    public <T> boolean delete(String entity,String primaryKey){
        String path=this.entityPath+""+entity;
        Transaction tx = null;
        Session session=this.sessionFactory.openSession();
        try {
            Class c=Class.forName(path);
            tx=session.beginTransaction();
            T obj= (T) session.get(c,primaryKey);
            session.delete(obj);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }

    }

    /**
     * 通用条件查询功能，只能有一个查询条件，比如where userNo = xxx
     * @param entity
     * @param property
     * @param value
     * @param <T>
     * @return
     */
    public <T> List<T> queryByProperty(String entity,String property,String value){
        String path=this.entityPath+""+entity;
        List<T> list=null;
        Session session=this.sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        try {
            Class c=Class.forName(path);
            list= session.createCriteria(c).add(Restrictions.like(property,value)).list();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
            return list;
        }

    }

    /**
     * 通用的条件查询，当条件比较复杂时，需自己写HQL查询语句
     * @param Hql
     * @param <T>
     * @return
     */
    public <T> List<T> queryByHql(String Hql){
        List<T> list=null;
        Session session=this.sessionFactory.openSession();
        try {
            Query query=session.createQuery(Hql);
            list=query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
            return list;
        }

    }


}
