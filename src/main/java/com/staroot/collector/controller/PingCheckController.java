package com.staroot.collector.controller;

import com.staroot.collector.service.PingCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingCheckController {
    @Autowired
    PingCheckService pingCheckService;
    @GetMapping("/check")
    public String check(){
        pingCheckService.performPingCheckAndSendEmail();
        return "test";

    }
}
