/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 8/1/2023 11:13 AM
 */
package com.slmora.learn.app.dao.impl;

import com.slmora.learn.app.dao.IDepartmentDaoLocal;
import com.slmora.learn.app.dao.IDepartmentDaoRemote;
import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.common.entity.Department;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@Stateless(name = "DepartmentDaoImpl", mappedName = "ejb/DepartmentDaoImpl")
@Local(IDepartmentDaoLocal.class)
@Remote(IDepartmentDaoRemote.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DepartmentDaoImpl extends GenericDaoImpl<Integer, Department> implements IDepartmentDaoLocal,
        IDepartmentDaoRemote
{
    private final static Logger LOGGER = LogManager.getLogger(DepartmentDaoImpl.class.getName());

    public DepartmentDaoImpl() {
        super(Department.class);
    }

    @Override
    public Department addDepartment(Department department) {
        return add(department);
    }

    @Override
    public void updateDepartment(Department department) {
        saveOrUpdate(department);
    }

    @Override
    public Department getDepartmentById(Integer deptId) {
        return getByKey(deptId);
    }

    @Override
    public void deleteDepartment(Department department) {
        delete(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return getAll();
    }

    @Override
    public List<Department> getAllDepartments(int limit, int offset) {
        return getAll(limit, offset);
    }

}
