package mesterum.controller;

//import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import mesterum.models.Student;
import mesterum.services.StudentService;

@Controller //@RequestMapping(path = "/students/")
@SessionAttributes("msg")
public class Students {
    @Autowired StudentService dao;
    @RequestMapping("/")
    public String root(){
    	return "redirect:/students/";
    }
    @RequestMapping(path = "/students/",method = RequestMethod.GET)
    public String doGet(String a, @RequestParam  Map<String, String> rParam, Model m) {
        //processRequest(request, response);
        //String action = request.getParameter("a");
        //ServiceDel<Student> dao = new ServiceDel<>(Student.class);
        //Service<Result> resultDAO = new Service<>(Result.class);
        Iterator<Student> students = null;
        Student stud = null; String id,name="student";
        if(a==null) a="a";//while(true)
        switch(a){
          case "d": id = rParam.get("id");
            if(id!=null){
                short sid = Short.parseShort(id);
                stud = dao.selectById(sid, s->{
                    Hibernate.initialize(s.getCourses());
                });
                if(!stud.getCourses().isEmpty())
                    m.addAttribute("msg", 
                    "This student can't be deleted because has marks assigned");
                else
                    dao.delete((short)sid);
            } return "redirect:.";//break;
            //action="a"; break;
          case "e": id = rParam.get("id");
            if(id!=null) {
                stud = dao.selectById(Short.parseShort(id));
            } m.addAttribute("form", stud);
            return "/"+name + "s/edit";//break;
          case "i": m.addAttribute("form", new Student());
          return "/"+name + "s/edit";//break;
        case "a": 
          default: students = dao.iselect();
          	m.addAttribute(name + "s", students);
          	//m.addAttribute("msg","");
          	return "/"+name + "s/all";
        }
    }

    @RequestMapping(path = "/students/",method = RequestMethod.POST)
    public String doPost(String a, @ModelAttribute("form") @Valid Student stud, BindingResult result) {
        //String action = request.getParameter("a");
        //ServiceDel<Student> dao = new ServiceDel<>(Student.class);
        if(a==null) a="i";
        /*String id;
        id = rParam.get("id");
        if(a.equals("u")){
            //if(id==null) return;
            stud = new Student(Short.parseShort(id));}
        else stud = new Student();
        stud.setFirstName(request.getParameter("fName"));
        stud.setLastName(request.getParameter("lName"));
        stud.setGender(request.getParameter("gender"));
        stud.setStartDate(request.getParameter("startD"));*/
        if (result.hasErrors()) {
        	return "/students/edit";
        }
        switch(a){
          case "u": 
            dao.update(stud); break;
          case "i":
          default:
            /*if(dao.selectById((short)stud.getId())!=null){
              request.getSession().setAttribute("msg", 
                "There is already a student with this id");break;
            }*/dao.insert(stud);
        } return "redirect:.";
    }

}
