/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 8/1/2023 11:10 AM
 */
package com.slmora.learn.common.dao.impl;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.db.EntityManagerUtil;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.List;

/**
 *  This Class created for
 *  <ul>
 *      <li>....</li>
 *  </ul>
 *
 * @since   1.0
 *
 * <blockquote><pre>
 * <br>Version      Date            Editor              Note
 * <br>-------------------------------------------------------
 * <br>1.0          8/1/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public abstract class GenericDaoImpl<PK extends Serializable, T> implements IGenericDao<PK, T>
{

    private final static Logger LOGGER = LogManager.getLogger(GenericDaoImpl.class);

    @Inject
    EntityManagerUtil entityManagerUtil;

//    private EntityManager entityManager;

    private Class<T> entityClass;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public GenericDaoImpl() {
    }

    protected CriteriaQuery<T> createEntityCriteriaQuery(){
        CriteriaBuilder criteriaBuilder = entityManagerUtil.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        return criteriaQuery;
    }

    protected TypedQuery<T> createTypedQuery() {
        CriteriaQuery<T> criteriaQuery = createEntityCriteriaQuery();
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);

        TypedQuery<T> typedQuery = entityManagerUtil.getEntityManager().createQuery(criteriaQuery);
        return typedQuery;
    }

    public void setEntityClass(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    @Override
    public T add(T entity) {
        if(null!=entity) {
//            EntityTransaction transaction = null;
            try {
//                transaction = entityManager.getTransaction();
//                transaction.begin();
                entityManagerUtil.getEntityManager().persist(entity);
//                entityManagerUtil.getEntityManager().flush();
//                transaction.commit();
                return entity;
            } catch (Throwable throwable) {
//                if (transaction != null) {
//                    transaction.rollback();
//                }
                LOGGER.error(ExceptionUtils.getStackTrace(throwable));
            }
//            finally {
//                if(transaction != null && transaction.isActive()){
//                    transaction.commit();
//                }
//            }
        }
        return null;
    }

    @Override
    public void delete(T entity) {
        if(null!=entity) {
//            EntityTransaction transaction = null;
            try {
//                transaction = entityManager.getTransaction();
//                transaction.begin();
                if (!entityManagerUtil.getEntityManager().contains(entity)) {
                    entity = entityManagerUtil.getEntityManager().merge(entity);
                }
                entityManagerUtil.getEntityManager().remove(entity);
//                entityManagerUtil.getEntityManager().flush();
//                transaction.commit();
            } catch (Throwable throwable) {
//                if (transaction != null) {
//                    transaction.rollback();
//                }
                LOGGER.error(ExceptionUtils.getStackTrace(throwable));
            }
//            finally {
//                if(transaction != null && transaction.isActive()){
//                    transaction.commit();
//                }
//            }
        }
    }

    @Override
    public void saveOrUpdate(T entity) {
        if(null!=entity) {
//            EntityTransaction transaction = null;
            try {
//                transaction = entityManager.getTransaction();
//                transaction.begin();
                entityManagerUtil.getEntityManager().merge(entity);
//                entityManagerUtil.getEntityManager().flush();
//                transaction.commit();
            } catch (Throwable throwable) {
//                if (transaction != null) {
//                    transaction.rollback();
//                }
                LOGGER.error(ExceptionUtils.getStackTrace(throwable));
            }
//            finally {
//                if(transaction != null && transaction.isActive()){
//                    transaction.commit();
//                }
//            }
        }
    }

    @Override
    public T getByKey(PK key) {
        if(null!=key) {
//            EntityTransaction transaction = null;
            try {
//                transaction = entityManager.getTransaction();
//                transaction.begin();
                T t = (T)entityManagerUtil.getEntityManager().find(entityClass, key);
//                entityManagerUtil.getEntityManager().flush();
//                transaction.commit();
                return t;
            } catch (Throwable throwable) {
//                if (transaction != null) {
//                    transaction.rollback();
//                }
                LOGGER.error(ExceptionUtils.getStackTrace(throwable));
            }
//            finally {
//                if(transaction != null && transaction.isActive()){
//                    transaction.commit();
//                }
//            }
        }
        return null;
    }

    @Override
    public T getSingleResultByColumn(String column, Object columnValue) {
        if(null!=columnValue) {
            try {
                CriteriaBuilder criteriaBuilder = entityManagerUtil.getEntityManager().getCriteriaBuilder();
                CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
                Root<T> root = criteriaQuery.from(entityClass);
                criteriaQuery.select(root);

                Predicate predicate = criteriaBuilder.equal(root.get(column), columnValue);
                criteriaQuery.where(predicate);

                TypedQuery typedQuery = entityManagerUtil.getEntityManager().createQuery(criteriaQuery);
                T t = (T) typedQuery.getSingleResult();
                return t;
            } catch (Throwable throwable) {
                LOGGER.error(ExceptionUtils.getStackTrace(throwable));
            }
        }
        return null;
    }

    @Override
    public List<T> getAllByColumn(String column, Object columnValue) {
        if(null!=columnValue) {
            try {
                CriteriaBuilder criteriaBuilder = entityManagerUtil.getEntityManager().getCriteriaBuilder();
                CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
                Root<T> root = criteriaQuery.from(entityClass);
                criteriaQuery.select(root);

                Predicate predicate = criteriaBuilder.equal(root.get(column), columnValue);
                criteriaQuery.where(predicate);

                TypedQuery typedQuery = entityManagerUtil.getEntityManager().createQuery(criteriaQuery);
                List<T> result = typedQuery.getResultList();
                return result;
            } catch (Throwable throwable) {
                LOGGER.error(ExceptionUtils.getStackTrace(throwable));
            }
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        try {
            TypedQuery<T> typedQuery = createTypedQuery();
            List<T> result = typedQuery.getResultList();
            return result;
        } catch (Throwable throwable) {
            LOGGER.error(ExceptionUtils.getStackTrace(throwable));
        }
        return null;
    }

    public List<T> getAll(int limit, int offset) {
        try {
            CriteriaQuery<T> criteriaQuery = createEntityCriteriaQuery();
            Root<T> root = criteriaQuery.from(entityClass);
            criteriaQuery.select(root);
            List<T> result = entityManagerUtil.getEntityManager().createQuery(criteriaQuery)
                    .setMaxResults(limit)
                    .setFirstResult(offset)
                    .getResultList();
            return result;
        } catch (Throwable throwable) {
            LOGGER.error(ExceptionUtils.getStackTrace(throwable));
        }
        return null;
    }

    public EntityManager getEntityManager()
    {
        return entityManagerUtil.getEntityManager();
    }

//    public void setEntityManager(EntityManagerUtil entityManagerUtil)
//    {
//        this.entityManager=entityManagerUtil.getEntityManagerFactory().createEntityManager();
//    }
//
//    public void setEntityManager(EntityManager entityManager)
//    {
//        this.entityManager=entityManager;
//    }
}
