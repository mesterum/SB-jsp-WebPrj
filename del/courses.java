/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Course;
import models.Result;
import models.ResultPK;
import org.hibernate.Hibernate;
import services.Service;
import services.ServiceDel;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 */
@WebServlet(name = "courses", urlPatterns = {"/courses/"})
public class courses extends HttpServlet {

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
        //processRequest(request, response);
        String action = request.getParameter("a");
        ServiceDel<Course> dao = new ServiceDel<>(Course.class);
        Service<Result> resultDAO = new Service<>(Result.class);
        Iterator<Course> courses = null;
        Course course = null; String id,name="course";
        if(action==null) action="a";//while(true)
        switch(action){
          case "d": id = request.getParameter("id");
            if(id!=null)try {
              course = dao.selectById(id, s->{
                Hibernate.initialize(s.getStudents());
              });
              if(!course.getStudents().isEmpty())
                  request.getSession().setAttribute("msg", 
                    "This course can't be deleted because has marks assigned");
              else
                  dao.delete(id);
            } catch (NumberFormatException ex) {
                log(null, ex);
            }response.sendRedirect(".");break;
            //action="a"; break;
          case "e": id = request.getParameter("id");
            if(id!=null)
                course = dao.selectById(id);
            request.setAttribute(name, course);
            getServletContext().getRequestDispatcher("/"+name + 
                "s/edit.jsp").forward(request, response);
            break;
          case "a": 
          default: courses = dao.iselect();
            request.setAttribute(name + "s", courses);
            request.getSession().setAttribute("msg",null);
            getServletContext().getRequestDispatcher("/"+name + 
                "s/all.jsp").forward(request, response);
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
        String action = request.getParameter("a");
        ServiceDel<Course> dao = new ServiceDel<>(Course.class);
        Course course = new Course(); String id;
        if(action==null) action="i";
        id = request.getParameter("id");
        if(id==null) return;
        course.setId(id);
        course.setName(request.getParameter("name"));
        switch(action){
          case "u": 
            dao.update(course); break;
          case "i":
          default:
            if(dao.selectById(course.getId())!=null){
                request.getSession().setAttribute("msg", 
                  "There is already a course with this id");break;
            }dao.insert(course);
        }
        response.sendRedirect(".");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
