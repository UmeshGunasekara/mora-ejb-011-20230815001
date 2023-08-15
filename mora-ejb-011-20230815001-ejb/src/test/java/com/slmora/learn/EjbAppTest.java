/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 6/4/2020 20:17 PM
 */
package com.slmora.learn;


import com.slmora.learn.common.db.EntityManagerUtil;
import com.slmora.learn.common.entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This Test Class created for testing com.slmora.AppTest
 *
 * @Author: SLMORA
 * @DateTime: 6/5/2020 20:17 PM
 * <p>
 * Version      Date            Editor              Note
 * ---------    ----------      ----------------    --------------------------------------------------------------------
 * 1.0          6/4/2020      SLMORA                Initial Code
 */
@DisplayName("Testing com.slmora.morajavafileaccess.FileAccess")
public class EjbAppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testp(){
        EntityTransaction transaction = null;
        try {
            Map<String, String>  props = new HashMap();

//            props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//            props.put(Environment.URL, "jdbc:mysql://localhost:3307/DB_MYDB_03?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC");
//            props.put(Environment.USER, "root");
//            props.put(Environment.PASS, "root");
//            props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
//            props.put(Environment.SHOW_SQL, "true");
//            props.put(Environment.HBM2DDL_AUTO, "update");
//            props.put(Environment.POOL_SIZE, "10");
//            props.put(Environment.FORMAT_SQL, "true");
//            props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");


//            props.put(PersistenceUnitProperties.JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
            props.put(PersistenceUnitProperties.JDBC_DRIVER, "com.mysql.jdbc.Driver");
//            props.put(PersistenceUnitProperties.JDBC_URL, "jdbc:mysql://localhost:3307/DB_MYDB_03?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC");
//            props.put(PersistenceUnitProperties.JDBC_URL, "jdbc:mysql://localhost:3307/DB_MYDB_03?serverTimezone=UTC");
            props.put(PersistenceUnitProperties.JDBC_URL, "jdbc:mysql://localhost:3306/demo_db_v001");
            props.put(PersistenceUnitProperties.JDBC_USER, "root");
            props.put(PersistenceUnitProperties.JDBC_PASSWORD, "root");
            props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            props.put(Environment.SHOW_SQL, "true");
            props.put(Environment.HBM2DDL_AUTO, "update");
//            props.put(Environment.POOL_SIZE, "10");
            props.put(Environment.FORMAT_SQL, "true");
//            props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

//            props.put(Environment.CONNECTION_PROVIDER, "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");

//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-name", props);

//            EntityManagerFactoryBuilder builder = new HibernatePersistenceProvider().getEntityManagerFactoryBuilderOrNull

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencecfg",props);

//            EntityManagerUtil em = new EntityManagerUtil();
//            EntityManager entityManager = em.getEntityManagerFactory().createEntityManager();
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencecfg");
            EntityManager entityManager = emf.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            if(entityManager.isOpen()){
                System.out.println("EntityManager Open");
            }

//            Department d = new Department();
//            d.setDeptId(10);
//            d.setDeptName("AAAA");
//////            Department d = Department.of(10L,"AAAA");
////            entityManager.merge(d);
//            entityManager.persist(d);
            Department d = entityManager.find(Department.class,1);



            entityManager.getTransaction().commit();

            System.out.println("Dep Name: "+ d.getDeptName());

            emf.close();

        } catch (Throwable throwable) {
            if(transaction !=null){
                transaction.rollback();
            }
            throwable.printStackTrace();
        }
    }
}
