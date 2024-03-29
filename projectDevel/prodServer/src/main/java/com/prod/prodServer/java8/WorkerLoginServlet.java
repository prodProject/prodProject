/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.java8;

import com.google.api.client.util.Preconditions;
import com.prod.prodServer.Services.WorkersService;
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
@WebServlet(name = "WorkerLoginServlet", urlPatterns = {"/WorkerLoginMain"})
public class WorkerLoginServlet extends HttpServlet {

    private WorkersService m_service;

    @Override
    public void init() throws ServletException {
        m_service = new WorkersService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        PrintWriter out = resp.getWriter();
        try {
            out.print( m_service.getuserLogin(req.getParameter("email/phone"),req.getParameter("password")));
        } catch (Exception ex) {
            Logger.getLogger(WorkerLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
