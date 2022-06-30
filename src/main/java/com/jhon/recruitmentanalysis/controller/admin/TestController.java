package com.jhon.recruitmentanalysis.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/admin/hello")
    public String hello(){
        return "hello";
    }

}
