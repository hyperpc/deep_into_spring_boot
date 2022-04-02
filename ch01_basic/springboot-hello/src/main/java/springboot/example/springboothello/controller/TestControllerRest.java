package springboot.example.springboothello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllerRest {

    @RequestMapping("/test")
    public String testRest(){
        return "欢迎学习《深入浅出Spring Boot》!";
    }
}
