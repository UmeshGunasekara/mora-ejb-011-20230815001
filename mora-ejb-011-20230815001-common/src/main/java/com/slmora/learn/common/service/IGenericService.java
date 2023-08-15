/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 8/1/2023 3:57 PM
 */
package com.slmora.learn.common.service;

import javax.ejb.Local;
import java.util.List;

/**
 *  This Interface created for
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
public interface IGenericService<PK, T> {
    public T add(T entity);

    public void delete(T entity);

    public void saveOrUpdate(T entity);

    public T getByKey(PK key);

    public T getSingleResultByColumn(String column, Object columnValue);

    public List<T> getAllByColumn(String column, Object columnValue);

    public List<T> getAll();

    public List<T> getAll(int limit, int offset);
}
