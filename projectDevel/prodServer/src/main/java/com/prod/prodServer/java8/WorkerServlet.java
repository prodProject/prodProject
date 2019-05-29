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

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletOutputStream a = resp.getOutputStream();
//        try {
//            String query = "create table users ( id int unsigned auto_increment not null, first_name varchar(32) not null, last_name varchar(32) not null, date_created timestamp default now(), is_admin boolean, num_points int, primary key (id) );";
//            CloudSqlQueryExecutor.createTable(query);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
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
        JSONObject response = m_service.getService(workerInformation);
        PrintWriter out = resp.getWriter();
        out.print(response.toString());
        out.flush();
    }

}
