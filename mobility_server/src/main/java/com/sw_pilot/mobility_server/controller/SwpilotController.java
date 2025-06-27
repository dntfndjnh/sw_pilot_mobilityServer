package com.sw_pilot.mobility_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwpilotController {

   @GetMapping("/report")
    public String Show_report(){
        return "report";
    }

}
