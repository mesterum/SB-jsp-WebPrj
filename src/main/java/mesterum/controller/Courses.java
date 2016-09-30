package mesterum.controller;

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

import mesterum.models.Course;
import mesterum.services.CourseService;

@Controller @RequestMapping(path = "/courses/")
@SessionAttributes("msg")
public class Courses {
    @Autowired CourseService dao;
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(String a, @RequestParam  Map<String, String> rParam, Model m) {
        Iterator<Course> courses = null;
        Course stud = null; String id,name="course";
        if(a==null) a="a";
        switch(a){
          case "d": id = rParam.get("id");
            if(id!=null){
                stud = dao.selectById(id, s->{
                    Hibernate.initialize(s.getStudents());
                });
                if(!stud.getStudents().isEmpty())
                    m.addAttribute("msg", 
                    "This course can't be deleted because has marks assigned");
                else
                    dao.delete(id);
            } return "redirect:.";
          case "e": id = rParam.get("id");
            if(id!=null) {
                stud = dao.selectById(id);
            } m.addAttribute("form", stud);
            return "/"+name + "s/edit";
          case "i": m.addAttribute("form", new Course());
          return "/"+name + "s/edit";
        case "a": 
          default: courses = dao.iselect();
          	m.addAttribute(name + "s", courses);
          	//m.addAttribute("msg","");
          	return "/"+name + "s/all";
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(String a, @ModelAttribute("form") @Valid Course stud, BindingResult result) {
        if(a==null) a="i";
        if (result.hasErrors()) {
        	return "/courses/edit";
        }
        switch(a){
          case "u": 
            dao.update(stud); break;
          case "i":
          default:
            dao.insert(stud);
        } return "redirect:.";
    }

}
