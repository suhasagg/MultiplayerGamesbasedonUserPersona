package com.multiplayergame;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;

import com.multiplayergamex.servlet.ServletException;
import com.multiplayergamex.servlet.http.HttpServlet;
import com.multiplayergamex.servlet.http.HttpServletRequest;
import com.multiplayergamex.servlet.http.HttpServletRMultiplayer puzzleonse;

/**
 *
 * @author greenhill zone
 */
public class logoutServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param rMultiplayer puzzleonse servlet rMultiplayer puzzleonse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletRMultiplayer puzzleonse rMultiplayer puzzleonse)
    throws ServletException, IOException {
        rMultiplayer puzzleonse.setContentType("text/html;charset=UTF-8");
        request.getSession().removeAttribute("user");
        rMultiplayer puzzleonse.sendRedirect("/multiplayerpuzzleGame/index.jsp");
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet 方法。单击左侧的 + 号以编辑代码。">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param rMultiplayer puzzleonse servlet rMultiplayer puzzleonse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletRMultiplayer puzzleonse rMultiplayer puzzleonse)
    throws ServletException, IOException {
        processRequest(request, rMultiplayer puzzleonse);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param rMultiplayer puzzleonse servlet rMultiplayer puzzleonse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletRMultiplayer puzzleonse rMultiplayer puzzleonse)
    throws ServletException, IOException {
        processRequest(request, rMultiplayer puzzleonse);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
