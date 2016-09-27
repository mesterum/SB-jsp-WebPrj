/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Course;
import models.Result;
import models.Student;
import services.Service;
import services.ServiceDel;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 */
@WebServlet(name = "results", urlPatterns = {"/results/"})
public class results extends HttpServlet {

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
        Service<Result> dao = new Service<>(Result.class);
        ResultsIter results = null;     String name="result";
        List<Course> courses = null;    Iterator<Student> students = null;
        courses = (new ServiceDel<>(Course.class)).select();
        students = (new ServiceDel<>(Student.class)).iselect();
        results = new ResultsIter(dao.iselect());
        request.setAttribute("courses", courses);
        request.setAttribute("students", students);
        request.setAttribute("results", results);
        request.getSession().setAttribute("msg",null);
        getServletContext().getRequestDispatcher("/"+name + 
            "s/all.jsp").forward(request, response);
    }
public static class ResultsIter{
    private Iterator<Result> results;
    public ResultsIter(Iterator<Result> results) {
        this.results = results;
        if(results.hasNext())next=results.next();
    }

    private Result result, next;
    public String next(Student s, Course c){
        if(next==null||next.getStudentId()!=s.getId()
                ||!next.getCourseId().equals(c.getId())){
            result=null;
        }else{result=next;
            if(results.hasNext())next=results.next();
            else next=null;
        }return getResult();
    }public String getResult() {
        return result==null?"-":result.getMarks()+"";
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
        ServiceDel<Course> courseDAO = new ServiceDel<>(Course.class);
        ServiceDel<Student> studentDAO = new ServiceDel<>(Student.class);
        Service<Result> dao = new Service<>(Result.class);
        Result result = new Result(); String cid; int sid;
        if(action==null) action="i";
        cid = request.getParameter("cid");
        if(cid==null) return;
        result.setCourseId(cid);
        cid = request.getParameter("sid");
        try {sid=Integer.parseInt(cid);}
            catch(NumberFormatException ex){return;}
        if(!action.equals("d")){
            cid = request.getParameter("marks");
            try {result.setMarks(Integer.parseInt(cid));}
                catch(NumberFormatException ex){return;}
        }result.setStudentId(sid);
        switch(action){
          case "d": 
            dao.delete(result); break;
          case "u": 
            dao.update(result); break;
          case "i":
          default:
              if(studentDAO.selectById(result.getStudentId())==null){
                request.getSession().setAttribute("msg", 
                  "There is no student with such id");break;
              }if(courseDAO.selectById(result.getCourseId())==null){
                request.getSession().setAttribute("msg", 
                  "There is no course with such id");break;
              }dao.insert(result);
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
