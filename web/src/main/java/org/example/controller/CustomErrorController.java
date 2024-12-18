package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class CustomErrorController implements ErrorController{


    @RequestMapping("/")
    public String shushi() {
        //返回主页
        return "Main";
    }

    @RequestMapping("/main")
    public String main() {
        //返回主页
        return "Main";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }



}
