/*
 * Copyright (c) 2016 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * zylka server: Helps you to develop easily AJAX web applications 
 *               by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/zylka
 * 
 * zylka server is distributed under the MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.daw.control;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.helper.statics.EstadoHelper;
import net.daw.helper.statics.EstadoHelper.Tipo_estado;
import net.daw.helper.statics.JsonMessage;
import net.daw.helper.statics.Log4j;
import net.daw.helper.statics.ParameterCook;
import net.daw.service.publicinterface.ViewServiceInterface;

public class json extends HttpServlet {

    private static final long serialVersionUID = 1L;

//    private void sendResponseJson1(HttpServletRequest request, HttpServletResponse response, String strStatus, String strMessage) throws ServletException, IOException {
//        Map<String, String> data = new HashMap<>();
//        data.put("status", strStatus);
//        data.put("message", strMessage);
//        Gson gson = new Gson();
//        request.setAttribute("contenido", gson.toJson(data));
//        getServletContext().getRequestDispatcher("/jsp/messageAjax.jsp").forward(request, response);
//    }
    private void sendResponseJson(HttpServletRequest request, HttpServletResponse response, String strMessage) throws ServletException, IOException {
        request.setAttribute("contenido", strMessage);
        getServletContext().getRequestDispatcher("/jsp/messageAjax.jsp").forward(request, response);
    }

    private void sendResponseHtml(HttpServletRequest request, HttpServletResponse response, String strTitle, String strMessage) throws ServletException, IOException {
        request.setAttribute("title", strTitle);
        request.setAttribute("message", strMessage);
        getServletContext().getRequestDispatcher("/jsp/message.jsp").forward(request, response);
    }

    private void retardo(Integer iLast) {
        try {
            Thread.sleep(iLast);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {        
        Log4j.infoLog("--------> incio PETICIÓN ");
        Log4j.infoLog(request.getRemoteHost() + ": " + request.getRemoteAddr());
        Log4j.infoLog("URL: " + request.getRequestURL().append('?').append(request.getQueryString()) + ": method: " + request.getMethod() + "");
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception ex) {
                if (EstadoHelper.getTipo_estado() == Tipo_estado.Debug) {
                    request.setAttribute("contenido", JsonMessage.getJsonMsg("500", "ERROR: " + ex.getMessage()));
                    getServletContext().getRequestDispatcher("/jsp/messageAjax.jsp").forward(request, response);
                } else {
                    request.setAttribute("contenido", JsonMessage.getJsonMsg("500", "Applications server error. Please, contact your administrator."));
                    getServletContext().getRequestDispatcher("/jsp/messageAjax.jsp").forward(request, response);
                }
                Log4j.severeLog(ex.toString());
                return;
            }
            if (EstadoHelper.getTipo_estado() == Tipo_estado.Debug) {
                retardo(EstadoHelper.getDelay());    //optional debug delay
            }
            String ob = ParameterCook.prepareObject(request);
            String op = ParameterCook.prepareOperation(request);
            if ("".equals(op) && "".equals(ob)) {
                sendResponseHtml(request, response, "zylka server by rafael aznar", "the server is up and running on " + request.getLocalName() + ":" + request.getLocalPort());
            } else {
                try {
                    String strClassName = "net.daw.service.implementation." + ParameterCook.prepareCamelCaseObject(request) + "Service";
                    ViewServiceInterface oService = (ViewServiceInterface) Class.forName(strClassName).getDeclaredConstructor(HttpServletRequest.class).newInstance(request);
                    Method oMethodService = oService.getClass().getMethod(ParameterCook.prepareOperation(request));
                    String jsonResult = (String) oMethodService.invoke(oService);
                    sendResponseJson(request, response, jsonResult);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    throw new Exception(this.getClass().getName() + ":processRequest ERROR: no such operation");
                }
            }
        } catch (ServletException | IOException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            if (EstadoHelper.getTipo_estado() == Tipo_estado.Debug) {
                sendResponseJson(request, response, JsonMessage.getJsonMsg("500", "ERROR: " + ex.getMessage()));
            } else {
                sendResponseJson(request, response, JsonMessage.getJsonMsg("500", "Applications server error. Please, contact your administrator."));
            }
            Log4j.severeLog(ex.toString());
            return;
        }
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "openAusiàs Servlet";
    }// </editor-fold>
}
