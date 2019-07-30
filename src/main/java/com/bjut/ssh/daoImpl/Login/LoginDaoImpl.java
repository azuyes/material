package com.bjut.ssh.daoImpl.Login;

import com.bjut.ssh.GeneralCRUD.GeneralCRUD;
import com.bjut.ssh.dao.Login.LoginDao;
import com.bjut.ssh.entity.Lspass;
import com.bjut.ssh.entity.Materialsset;
import com.bjut.ssh.entity.Warehouseset;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginDaoImpl implements LoginDao {
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
    public void closeSessionFactory(){
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("虚拟机关闭!释放资源");
                sessionFactory.close();
            }
        }));
    }

    @Override
    public Lspass enter(String userNo, String userPass){
        Session session=null;
        Lspass lspass=null;
        try{
            String hsql="from Lspass where userNo='" +userNo+ "' and userPass='" +userPass+ "'";
            session = getSession();
            Query query = session.createQuery(hsql);
            lspass = (Lspass) query.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(session);
            return lspass;
        }

        //System.out.println(lspass);

    }

    @Override
    public List<Warehouseset> getWarehouse() {
        return generalCRUD.getMethod("Warehouseset");
    }
}
