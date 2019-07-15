/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.java8;

import com.prod.prodServer.CommonCode.Lists;
import com.prod.prodServer.Enums.WorkerTypeConfigEnum;
import com.prod.prodServer.Handlers.WorkerTypeCategoryHandler;
import com.prod.prodServer.Helpers.WorkerTypeConfigHelper;
import com.prod.prodServer.QueryBuilders.WorkerTypeQueryBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
@WebServlet(name = "WorkerTypeConfigServlet", urlPatterns = {"/workerTypeCategoryMain"})
public class WorkerTypeCategoryServlet extends HttpServlet {

    private WorkerTypeCategoryHandler m_handler;

    @Override
    public void init() throws ServletException {
        m_handler = new WorkerTypeCategoryHandler(new WorkerTypeQueryBuilder(), new WorkerTypeConfigHelper());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<WorkerTypeConfigEnum> typeConfig = new ArrayList<WorkerTypeConfigEnum>();
        String workerTypes = req.getParameter("worker_type");
        for (String wprkerType : workerTypes.split(",")) {
            typeConfig.add(WorkerTypeConfigEnum.valueOf(wprkerType));
        }
        JSONObject response = null;
        try {
            response = m_handler.getWorkersCategory(typeConfig);
        } catch (Exception ex) {
            Logger.getLogger(WorkerTypeCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter out = resp.getWriter();
        out.print(response);
    }

}
