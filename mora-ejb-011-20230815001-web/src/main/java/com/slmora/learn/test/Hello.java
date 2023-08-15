/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 8/1/2023 4:12 PM
 */
package com.slmora.learn.test;

import com.slmora.learn.app.service.IDepartmentServiceRemote;
import com.slmora.learn.base.servlet.BaseServlet;
import com.slmora.learn.common.entity.Department;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet(name = "hello", urlPatterns = { "/hello" })
public class Hello extends BaseServlet
{

    /**
     *
     */
    private static final long serialVersionUID = 689311975102276106L;
    private final static Logger LOGGER = LogManager.getLogger(Hello.class);

    @EJB
    private IDepartmentServiceRemote departmentService;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        PrintWriter out = response.getWriter();
        out.println("<h1> Hi This is HelloController</h1>");
        LOGGER.info("Loading Hello Controller");
        try{
            Department d = departmentService.getDepartmentById(1);

            out.println("<p>Hello App</p>");
            out.println("<p>"+d.getDeptName()+"</p>");
        }catch(Exception e){
            LOGGER.error(ExceptionUtils.getStackTrace(e));
            out.println("<p> error : "+e.getMessage()+"</p>");
        }
    }
}
