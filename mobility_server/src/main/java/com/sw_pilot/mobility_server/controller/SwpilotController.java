package com.sw_pilot.mobility_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SwpilotController {

   @GetMapping("/report")
    public String Show_report(){
        return "report";
    }

    @RestController
    public class EchoController {

        @GetMapping("/test")
        public String echo(@RequestParam(name = "msg", required = false, defaultValue = "메시지가 없습니다.") String msg) {
            return msg; // 입력받은 문자열을 그대로 반환
        }
    }

}
