/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.java8;

import com.prod.prodServer.CommonCode.JSONResponses;
import com.prod.prodServer.CommonCode.Strings;
import com.prod.prodServer.DatabaseSchema.WorkerTypeSchema;
import com.prod.prodServer.Services.WorkersService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shubham
 */
@WebServlet(name = "WorkerTypeServlet", urlPatterns = {"/WorkerTypeMain"})
public class WorkerTypeServlet extends HttpServlet {
    
    private WorkersService m_service;
    
    @Override
    public void init() throws ServletException {
        m_service = new WorkersService();
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> map = new HashMap<String, String>();
        for (String schema : WorkerTypeSchema.getSchema()) {
            if (Strings.isEmpty(req.getParameter(schema))) {
                return;
            } else {
                map.put(schema, req.getParameter(schema));
            }
            
        }
        try {
            resp.getWriter().print(m_service.insertWorkerTypeData(map));
        } catch (SQLException ex) {
            Logger.getLogger(WorkerTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
