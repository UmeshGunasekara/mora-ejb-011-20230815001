/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 8/1/2023 4:04 PM
 */
package com.slmora.learn.app.service;

import com.slmora.learn.common.entity.Department;
import com.slmora.learn.common.service.IGenericService;
import jakarta.ejb.Local;

import java.util.List;

/**
 * This Interface created for
 * <ul>
 *     <li>....</li>
 * </ul>
 *
 * @since 1.0
 *
 * <blockquote><pre>
 * <br>Version      Date            Editor              Note
 * <br>-------------------------------------------------------
 * <br>1.0          8/1/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Local
public interface IDepartmentServiceLocal extends IGenericService<Integer, Department>
{
    public Department addDepartment(Department department);
    public void updateDepartment(Department department);
    public Department getDepartmentById(Integer deptId);
    public void deleteDepartment(Department department);
    public List<Department> getAllDepartments();
    public List<Department> getAllDepartments(int limit, int offset);
}
