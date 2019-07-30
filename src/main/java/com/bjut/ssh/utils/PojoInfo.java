package com.bjut.ssh.utils;

import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.AbstractEntityPersister;

public class PojoInfo {
    SessionFactory sessionFactory;
    public PojoInfo(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    public String getPrimaryKeyName(Class c){
        AbstractEntityPersister classMetadata= (AbstractEntityPersister) this.sessionFactory.getClassMetadata(c);
        String pkPropertyName = classMetadata.getIdentifierPropertyName();
        return pkPropertyName;
    }
}
