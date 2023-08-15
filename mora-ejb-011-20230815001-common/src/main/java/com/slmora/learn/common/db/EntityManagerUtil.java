/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 8/1/2023 2:03 PM
 */
package com.slmora.learn.common.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;


public class EntityManagerUtil {
    final static Logger LOGGER = LogManager.getLogger(EntityManagerUtil.class);

    private EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager = null;

    public EntityManagerUtil(){
        this.setup();
    }

    public EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory;
    }

    private void setup(){
        if(entityManagerFactory == null){
            try {
                LOGGER.info("Setting persistence unit name persistencecfg");
                entityManagerFactory = Persistence.createEntityManagerFactory("persistencecfg");
                entityManager = entityManagerFactory.createEntityManager();
            }catch (Exception e){
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }

    public void shutdown(){
        if(entityManagerFactory.isOpen()){
            entityManagerFactory.close();
        }
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void setPersistenceProperties(){
        if(entityManagerFactory == null) {
            try {
                Map<String, String> props = new HashMap();

//        props.put(PersistenceUnitProperties.JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
                props.put(PersistenceUnitProperties.JDBC_DRIVER, "com.mysql.jdbc.Driver");
//        props.put(PersistenceUnitProperties.JDBC_URL, "jdbc:mysql://localhost:3307/DB_MYDB_03?serverTimezone=UTC");
//        props.put(PersistenceUnitProperties.JDBC_URL, "jdbc:mysql://localhost:3307/DB_MYDB_03");
                props.put(PersistenceUnitProperties.JDBC_URL, "jdbc:mysql://localhost:3306/demo_db_v001");
                props.put(PersistenceUnitProperties.JDBC_USER, "root");
                props.put(PersistenceUnitProperties.JDBC_PASSWORD, "root");
                props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                props.put(Environment.SHOW_SQL, "true");
                props.put(Environment.HBM2DDL_AUTO, "update");
                props.put(Environment.FORMAT_SQL, "true");

                entityManagerFactory = Persistence.createEntityManagerFactory("persistencecfg", props);
            }catch (Exception e){
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

}
