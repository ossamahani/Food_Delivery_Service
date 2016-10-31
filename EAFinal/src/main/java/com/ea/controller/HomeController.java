package com.ea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by amanadhikari on 9/26/16.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(){
        System.out.println("*******HOME***********");
        return "index";
    }
}
