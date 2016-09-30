package mesterum.controller;

import java.util.Iterator;
import java.util.List;
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
import mesterum.models.Result;
import mesterum.models.Student;
import mesterum.services.CourseService;
import mesterum.services.ResultService;
import mesterum.services.StudentService;

@Controller @RequestMapping(path = "/results/")
@SessionAttributes("msg")
public class ResultsCtrl {
    @Autowired ResultService dao;
	@Autowired CourseService cdao;
	@Autowired StudentService sdao;
	@RequestMapping(method = RequestMethod.GET, path = "edit")
    public String edit(){return "/results/edit";}
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(String a, Model m) {
        ResultsIter results = null;     String name="result";
        List<Course> courses = null;    Iterator<Student> students = null;
        courses = cdao.select();
        students = sdao.iselect();
        results = new ResultsIter(dao.iselect());
        m.addAttribute("courses", courses);
        m.addAttribute("students", students);
        m.addAttribute("results", results);
        //m.addAttribute("msg","");
        return "/"+name + "s/all";
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

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(String a, String cid, int sid,
    		String marks, Model m) {
        Result result = new Result(); //String cid; int sid;
        if(a==null) a="i";
        //cid = (String) m.asMap().get("cid");
        if(cid==null) return "redirect:";
        result.setCourseId(cid);
        /*cid = request.getParameter("sid");
        try {sid=Integer.parseInt(cid);}
            catch(NumberFormatException ex){return;}*/
        result.setStudentId(sid);
        if(!a.equals("d")){
            try {result.setMarks(Integer.parseInt(marks));}
            catch(NumberFormatException ex){return "redirect:";}
        }
        switch(a){
          case "d": 
            dao.delete(result); break;
          case "u": 
            dao.update(result); break;
          case "i":
          default:
              if(sdao.selectById(result.getStudentId())==null){
            	  m.addAttribute("msg", 
                  "There is no student with such id");break;
              }if(cdao.selectById(result.getCourseId())==null){
            	  m.addAttribute("msg", 
                  "There is no course with such id");break;
              }dao.insert(result);
        } return "redirect:.";
    }

}
