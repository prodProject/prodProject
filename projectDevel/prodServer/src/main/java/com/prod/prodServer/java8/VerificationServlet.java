/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.java8;

import com.prod.prodServer.Services.VerificationService;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "VerificationServlet", urlPatterns = {"/verificationMain"})
public class VerificationServlet extends HttpServlet {

    private VerificationService m_service;

    @Override
    public void init() throws ServletException {
        m_service = new VerificationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getQueryString().isEmpty()) {
            return;
        }
        JSONObject res = m_service.getGetService(req.getQueryString());
        PrintWriter out = resp.getWriter();
        if (res == null) {
            out.print("Something Went Wrong,Please Try Again Later Or Your Verrification Link Is Expired");
            out.flush();
        } else {
            if (res.getInt("status_code") == 1) {
                out.print("You are verified");
                out.flush();
            } else {
                out.print("Your Verification Link Is Expired or Something went worng!");
                out.flush();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
