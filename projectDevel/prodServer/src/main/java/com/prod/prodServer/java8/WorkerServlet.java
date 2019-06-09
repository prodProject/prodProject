/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.java8;

import com.prod.prodServer.DatabaseSchema.WorkersTableSchema;
import com.prod.prodServer.Services.WorkersService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
@WebServlet(name = "WorkerServlet", urlPatterns = {"/workerMain"})
public class WorkerServlet extends HttpServlet {

    private WorkersService m_service;

    @Override
    public void init() throws ServletException {
        m_service = new WorkersService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject response = m_service.getGetService(req.getQueryString());
        PrintWriter out = resp.getWriter();
        out.print(response.toString());
        out.flush();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Map<String, String> workerInformation = new HashMap<String,String>();
        

        for (String data : WorkersTableSchema.getWorkersSchema()) {
            if (null==req.getParameter(data)) {
                workerInformation.put(data, "0");
            } else {
                workerInformation.put(data, req.getParameter(data));
            }
        }
        JSONObject response = m_service.getPostService(workerInformation);
        PrintWriter out = resp.getWriter();
        out.print(response.toString());
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
