/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 8/1/2023 4:00 PM
 */
package com.slmora.learn.base;

import com.slmora.learn.app.dao.impl.DepartmentDaoImpl;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


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
@Singleton(name = "BaseBean", mappedName = "ejb/BaseBean")
@Startup
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BaseBean {
    private final static Logger LOGGER = LogManager.getLogger(DepartmentDaoImpl.class.getName());

    private static final String LOGGING_PROPERTIES = "D:\\SLMORAWorkSpace\\IntelliJProjects\\slmora-ejb\\mora-ejb-009-20230814001\\mora-ejb-009-20230814001-ejb\\src\\main\\resources\\log4j2.xml";

    /**
     * init for configure log manager with given properties in post construct singleton bean
     *
     */
    @PostConstruct
    public void init() {
        try {
            Configurator.initialize(null, LOGGING_PROPERTIES);
            LOGGER.info("Successfully Configured LogManger");
        } catch (Exception e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @PreDestroy
    public void destroy() {
        LOGGER.info("Destroy BaseBean");
    }
}