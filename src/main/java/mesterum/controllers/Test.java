package mesterum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test {
    @RequestMapping("/test")
    String getTest(){
    	return "test";
    }

}
