package com.example.backendnh.controller;

import com.example.backendnh.service.FullQueryService;
import com.example.backendnh.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nh/fullquery")
@CrossOrigin(origins = {"http://124.222.139.8:8081","http://localhost:8080"}, maxAge = 3600, allowCredentials="true",allowedHeaders = "*",methods = {RequestMethod.POST,RequestMethod.GET})
public class FullQueryController {
    @Autowired
    FullQueryService fullQueryService;

    @PostMapping("/query")
    ResponseVO fullQuery(@RequestParam String keyword, @RequestParam Integer exactMatch,@RequestParam String username) {
        return fullQueryService.fullQuery(keyword, exactMatch, username);
    }

    @PostMapping("/statisticQuery")
    ResponseVO statisticQuery(){
        return fullQueryService.statisticQuery();
    }

    @PostMapping("/statisticArchives")
    ResponseVO statisticArchives(@RequestParam String strStatisticType){
        return fullQueryService.statisticArchives(strStatisticType);
    }
}
