package com.bjut.ssh.daoImpl.HelperDaoImpl;

import com.bjut.ssh.dao.Helper.HelperDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class HelperDaoImpl implements HelperDao {

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
     * @discription 本函数用于判断网页中选择的条目是否为末级，
     *              是才能显示详情，否则提示用户。
     * @param id 用户所选条目的id
     * @return 是否为末级
     */
    @Override
    public boolean isEnd(String id) {
        String idFirst = id.substring(0,2);
        String idSecond = id.substring(2,4);
        String idThird= id.substring(4,6);

        String hql = null;
        Query query = null;
        Session session = null;
        Transaction tx = null;
        Boolean result = false;
        try {
            session = getSession();
            tx = session.beginTransaction();

            if(!idThird.equals("00"))
            {
                //第3级别
                result = true;
            }
            else if ( !idSecond .equals("00")){
                // 第2级别
                hql = "select count(*) from Materialclass where substring(MaterialClassId,1,2)=?";
                query = session.createQuery(hql);
                query.setParameter(0,idFirst);
            }
            else
                {// 第1级别
                hql = "select count(*) from Materialclass where substring(MaterialClassId,1,2)=? and substring(MaterialClassId,3,2) = ?";
                query = session.createQuery(hql);
                query.setParameter(0,id);
                query.setParameter(1,idSecond);
            }

            if(!result) {
                Long count = (Long) query.uniqueResult();
                if (count >= 1l) {
                    result = false;
                } else {
                    result = true;
                }
            }

            tx.commit();
            close(session);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
