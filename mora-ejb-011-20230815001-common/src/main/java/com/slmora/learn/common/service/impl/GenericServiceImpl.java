/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 8/1/2023 3:58 PM
 */
package com.slmora.learn.common.service.impl;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.service.IGenericService;

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
public abstract class GenericServiceImpl<PK extends Serializable, T> implements IGenericService<PK, T>
{

    private IGenericDao<PK, T> genericDao;

    public GenericServiceImpl(IGenericDao<PK, T> genericDao) {
        this.genericDao = genericDao;
    }

    public GenericServiceImpl() {
    }

    @Override
    public T add(T entity) {
        return genericDao.add(entity);
    }

    @Override
    public void delete(T entity) {
        genericDao.delete(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        genericDao.saveOrUpdate(entity);
    }

    @Override
    public T getByKey(PK key) {
        return genericDao.getByKey(key);
    }

    @Override
    public T getSingleResultByColumn(String column, Object columnValue)
    {
        return genericDao.getSingleResultByColumn(column,columnValue);
    }

    @Override
    public List<T> getAllByColumn(String column, Object columnValue)
    {
        return genericDao.getAllByColumn(column,columnValue);
    }

    @Override
    public List<T> getAll() {
        return genericDao.getAll();
    }

    @Override
    public List<T> getAll(int limit, int offset) {
        return genericDao.getAll(limit, offset);
    }

}
