/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesterum.controllers;

import java.io.IOException;
import java.util.Iterator;
//import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;

import mesterum.models.Student;
//import mesterum.services.ServiceDel;
import mesterum.services.StudentService;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 */
@WebServlet(name = "students", urlPatterns = {"/students/"})
@Controller
public class students extends HttpServlet {

    @Autowired StudentService dao;
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    //@RequestMapping(value = "/students", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String action = request.getParameter("a");
        //ServiceDel<Student> dao = new ServiceDel<>(Student.class);
        //Service<Result> resultDAO = new Service<>(Result.class);
        Iterator<Student> students = null;
        Student stud = null; String id,name="student";
        if(action==null) action="a";//while(true)
        switch(action){
          case "d": id = request.getParameter("id");
            if(id!=null){
                short sid = Short.parseShort(id);
                stud = dao.selectById(sid, s->{
                    Hibernate.initialize(s.getCourses());
                });
                if(!stud.getCourses().isEmpty())
                    request.getSession().setAttribute("msg", 
                    "This student can't be deleted because has marks assigned");
                else
                    dao.delete((short)sid);
            } response.sendRedirect(".");break;
            //action="a"; break;
          case "e": id = request.getParameter("id");
            if(id!=null) {
                stud = dao.selectById(Short.parseShort(id));
            } request.setAttribute(name, stud);
            getServletContext().getRequestDispatcher("/"+name + 
                "s/edit.jsp").forward(request, response);
            break;
          case "a": 
          default: students = dao.iselect();
            request.setAttribute(name + "s", students);
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
        //ServiceDel<Student> dao = new ServiceDel<>(Student.class);
        Student stud; String id;
        if(action==null) action="i";
        id = request.getParameter("id");
        if(action.equals("u")){
            if(id==null) return;
            stud = new Student(Short.parseShort(id));}
        else stud = new Student();
        stud.setFirstName(request.getParameter("fName"));
        stud.setLastName(request.getParameter("lName"));
        stud.setGender(request.getParameter("gender"));
        stud.setStartDate(request.getParameter("startD"));
        switch(action){
          case "u": 
            dao.update(stud); break;
          case "i":
          default:
            /*if(dao.selectById((short)stud.getId())!=null){
              request.getSession().setAttribute("msg", 
                "There is already a student with this id");break;
            }*/dao.insert(stud);
        } response.sendRedirect(".");
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
