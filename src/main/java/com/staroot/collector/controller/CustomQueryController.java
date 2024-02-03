package com.staroot.collector.controller;

import com.staroot.collector.dao.CustomRepository;
import com.staroot.collector.dao.WasInstanceRepository;
import com.staroot.collector.dao.impl.CustomRepositoryImpl;
import com.staroot.collector.domain.WasInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/custom")
public class CustomQueryController {
    private static final Logger log = LoggerFactory.getLogger(CustomQueryController.class);
    @Autowired
    WasInstanceRepository wasInstanceRepository;
    @Autowired
    CustomRepository customRepository;

    CustomRepositoryImpl customRepositoryImpl;
    @GetMapping("/select")
    @ResponseBody
    public List<Map<String,Object>> selectList(){
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_11","staroot01","192.168.0.101","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.101:8080","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_12","staroot01","192.168.0.101","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.101:8081","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_21","staroot02","192.168.0.102","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.102:8080","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_22","staroot02","192.168.0.102","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.102:8081","100","2024-01-28","2024-01-28"));
        List<Object[]> list = new ArrayList<>();
        String columns = "id,hostname,ip,os,was,lang,domain,uri,uptime,lastlog,moddt";
        String[] cols = columns.split(",");
        list = customRepository.runCustomSelect("select "+columns+" from was_instance");
        //list = customRepository.runCustomSelect("select ip from was_instance");
        log.info(list.toString());
        log.info(list.stream().toList().toString());

        List resultList = list.stream()
                .map(row -> {
                    Map<String, Object> result = new HashMap<>();
                    for (int i = 0; i < row.length; i++) {
                        //String columnName = column" + (i + 1); // 임시로 생성한 컬럼명
                        String columnName = cols[i]; // 임시로 생성한 컬럼명
                        result.put(columnName, row[i]);
                    }
                    return result;
                })
                .collect(Collectors.toList());

        log.info(resultList.toString());


        /*
        List<Map<String, Object>> resultAsHashMap = list.stream()
                .map(entity -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", entity.get("id"));
                    map.put("hostname", entity.get("hostname"));
                    map.put("ip", entity.get("ip"));
                    // Add more columns as needed
                    return map;
                })
                .collect(Collectors.toList());
        log.info(resultAsHashMap.toString());
        return resultAsHashMap;
         */
        return resultList;
    }
}
