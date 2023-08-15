/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 8/1/2023 4:07 PM
 */
package com.slmora.learn.app.service.impl;

import com.slmora.learn.app.dao.IDepartmentDaoLocal;
import com.slmora.learn.app.dao.impl.DepartmentDaoImpl;
import com.slmora.learn.app.service.IDepartmentServiceLocal;
import com.slmora.learn.app.service.IDepartmentServiceRemote;
import com.slmora.learn.common.entity.Department;
import com.slmora.learn.common.service.impl.GenericServiceImpl;
import jakarta.ejb.EJB;
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
@Stateless(name = "DepartmentServiceImpl", mappedName = "ejb/DepartmentServiceImpl")
@Local(IDepartmentServiceLocal.class)
@Remote(IDepartmentServiceRemote.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DepartmentServiceImpl extends GenericServiceImpl<Integer, Department> implements IDepartmentServiceLocal,
        IDepartmentServiceRemote
{

    private final static Logger LOGGER = LogManager.getLogger(DepartmentServiceImpl.class.getName());

    @EJB
    private IDepartmentDaoLocal dao;

    @Override
    public Department addDepartment(Department department) {
        return dao.addDepartment(department);
    }

    @Override
    public void updateDepartment(Department department) {
        dao.updateDepartment(department);
    }

    @Override
    public Department getDepartmentById(Integer deptId) {
        return dao.getDepartmentById(deptId);
    }

    @Override
    public void deleteDepartment(Department department) {
        dao.deleteDepartment(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return dao.getAllDepartments();
    }

    @Override
    public List<Department> getAllDepartments(int limit, int offset) {
        return dao.getAllDepartments(limit, offset);
    }

}
