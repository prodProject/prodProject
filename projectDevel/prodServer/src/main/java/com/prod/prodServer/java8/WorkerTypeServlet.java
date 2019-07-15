/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.java8;

import com.prod.prodServer.Handlers.WorkerTypeHandler;
import com.prod.prodServer.QueryBuilders.WorkerTypeQueryBuilder;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "WorkerTypeServlet", urlPatterns = {"/workerTypeMain"})
public class WorkerTypeServlet extends HttpServlet {

    private WorkerTypeHandler m_handler;

    @Override
    public void init() throws ServletException {
        m_handler = new WorkerTypeHandler(new WorkerTypeQueryBuilder());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            out.print(m_handler.getAllWorkerType());
        } catch (Exception ex) {
            Logger.getLogger(WorkerTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
