/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 8/1/2023 9:50 AM
 */
package com.slmora.learn.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

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
@Entity
@Table(name = "DEPARTMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
//@NamedQueries({
//        @NamedQuery(name = "Department.findByDeptName", query = "FROM Department WHERE deptName = :name")
//})
public class Department implements Serializable
{
    private final static Logger LOGGER = LogManager.getLogger(Department.class);

    private static final long serialVersionUID = -5285284310949998210L;

    @Id
    @Column(name = "dept_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer deptId;

    @Column(name = "dept_name")
    private String deptName;
}
