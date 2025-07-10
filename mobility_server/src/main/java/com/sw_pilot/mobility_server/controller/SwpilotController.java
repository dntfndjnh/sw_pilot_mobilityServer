package com.sw_pilot.mobility_server.controller;

import com.sw_pilot.mobility_server.domain.Data;
import com.sw_pilot.mobility_server.domain.ReportItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class SwpilotController {

   //@GetMapping("/report")
    //public String Show_report(){
      //  return "report";
    //}

    @RestController
    public class EchoController {

        @GetMapping("/test")
        public String echo(@RequestParam(name = "msg", required = false, defaultValue = "메시지가 없습니다.") String msg) {
            return msg; // 입력받은 문자열을 그대로 반환
        }
    }



    @GetMapping("/report")
    public String reportPage(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id == null || id.trim().isEmpty()) {
            id = "알 수 없음";
        }

        model.addAttribute("reportTitle", "공정점검보고서");
        model.addAttribute("authorName", id);
        model.addAttribute("writeDate", ""); // JS로 동적 날짜 입력 예정
        model.addAttribute("summary", "여기에 보고서 요약 내용이 들어갑니다.");

        // 상세 항목 예시
        model.addAttribute("details", java.util.List.of(
                new ReportItem("온도", "현재 측정된 온도", "24.5°C"),
                new ReportItem("습도", "현재 측정된 습도", "45%"),
                new ReportItem("CO2", "이산화탄소 농도", "400ppm")
        ));

        return "report"; // src/main/resources/templates/report.html
    }


    @GetMapping("/play")
    public String play() {
        return "play";
    }


    @RestController
    @RequestMapping("/api")
    public class DataController {
        private final List<Data> dataList = new CopyOnWriteArrayList<>();
        private final Map<String, Integer> itemCounts = new ConcurrentHashMap<>();

        @PostMapping("/send")
        public String receiveData(@RequestBody Data data) {
            LocalDateTime now = LocalDateTime.now();
            String time = now.format(DateTimeFormatter.ofPattern("HH시 mm분 ss초"));
            data.setTime(time);
            dataList.add(data);
            itemCounts.merge(data.getItemName(), data.getValue(), Integer::sum);
            return "Received " + data.getAreaName() + " - " + data.getItemName() + " = " + data.getValue() + " at " + time;
        }

        @GetMapping("/zone-totals")
        public List<Data> getZoneTotals() {
            return dataList;
        }

        @GetMapping("/totals")
        public Map<String, Integer> getItemTotals() {
            return itemCounts;
        }
    }




}
