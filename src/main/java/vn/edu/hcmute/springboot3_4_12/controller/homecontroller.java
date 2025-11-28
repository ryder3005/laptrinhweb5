package vn.edu.hcmute.springboot3_4_12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homecontroller {
    @RequestMapping("home")

    public  String index() {
        return "index";
    }
}
