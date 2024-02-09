package com.staroot.collector.controller;

import com.staroot.collector.dao.ServerInfoRepository;
import com.staroot.collector.dao.WasInstanceRepository;
import com.staroot.collector.domain.ServerInfo;
import com.staroot.collector.domain.WasInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/was")
public class WasController {
    private static final Logger log = LoggerFactory.getLogger(WasController.class);

    @Autowired
    ServerInfoRepository serverInfoRepository;
    @Autowired
    WasInstanceRepository wasInstanceRepository;

    @GetMapping("/pagelist1")
    public String pageList1(Model model, @RequestParam(defaultValue = "0") int page) {
        log.info("pageList1 called!");

        //sample데이터
        for (int i=0;i<100;i++) {
            wasInstanceRepository.save(new WasInstance("rpt_tomcat(" + i + ")", "staroot01", "192.168.0." + i, "Centos7.9", "Apache Tomcat 9.0.83", "jdk1.8.183", "rpt.staroot.com", "http://192.168.0." + i + ":8080", "100", "2024-01-28", "2024-01-28"));
        }

        // 페이징 처리
        Page<WasInstance> wasPage = wasInstanceRepository.findAll(PageRequest.of(page, 10));

        // 전달할 모델에 데이터 추가
        model.addAttribute("wasList", wasPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", wasPage.getTotalPages());

        return "wasPagelist";
    }

}
