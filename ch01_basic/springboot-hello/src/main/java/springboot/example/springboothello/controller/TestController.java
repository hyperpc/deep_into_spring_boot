package springboot.example.springboothello.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    
    @RequestMapping("/hello")
    public String getIndex(HashMap<String, String> map){
        map.put("title", "欢迎来到《Deep into Spring Boot》！");
        return "/index";
    }
}
