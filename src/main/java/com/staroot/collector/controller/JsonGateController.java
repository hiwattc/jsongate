package com.staroot.collector.controller;

import com.staroot.collector.dao.CustomRepository;
import com.staroot.collector.dao.ServerInfoRepository;
import com.staroot.collector.dao.WasInstanceRepository;
import com.staroot.collector.dao.impl.CustomRepositoryImpl;
import com.staroot.collector.domain.ServerInfo;
import com.staroot.collector.domain.WasInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class JsonGateController {
    private static final Logger log = LoggerFactory.getLogger(JsonGateController.class);

    @Autowired
    ServerInfoRepository serverInfoRepository;
    @Autowired
    WasInstanceRepository wasInstanceRepository;

    @GetMapping("/server/list")
    public List<ServerInfo> serverList(){
        serverInfoRepository.save(new ServerInfo("staroot01","127.0.0.1","uptime","1000d"));
        List<ServerInfo> list = new ArrayList<>();
        for(ServerInfo svrInfo : serverInfoRepository.findAll()){
            log.info(svrInfo.toString());
            list.add(svrInfo);
        }
        log.info(list.toString());
        return list;
    }

    @PostMapping("/server/send")
    public void sendServerInfo(@RequestBody ServerInfo svrInfo){
        log.info(svrInfo.toString());
        serverInfoRepository.save(svrInfo);
    }
    @GetMapping("/was/list")
    public List<WasInstance> wasList(){
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_11","staroot01","192.168.0.101","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.101:8080","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_12","staroot01","192.168.0.101","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.101:8081","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_21","staroot02","192.168.0.102","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.102:8080","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_22","staroot02","192.168.0.102","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.102:8081","100","2024-01-28","2024-01-28"));
        List<WasInstance> list = new ArrayList<>();
        for(WasInstance wasInstance : wasInstanceRepository.findAll()){
            log.info(wasInstance.toString());
            list.add(wasInstance);
        }
        log.info(list.toString());
        return list;
    }
    @GetMapping("/was/list2")
    public List<WasInstance> wasList2(){
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_11","staroot01","192.168.0.101","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.101:8080","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_12","staroot01","192.168.0.101","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.101:8081","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_21","staroot02","192.168.0.102","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.102:8080","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_22","staroot02","192.168.0.102","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.102:8081","100","2024-01-28","2024-01-28"));
        List<WasInstance> list = new ArrayList<>();
        for(WasInstance wasInstance : wasInstanceRepository.findByHostname("staroot01")){
            log.info(wasInstance.toString());
            list.add(wasInstance);
        }
        log.info(list.toString());
        return list;
    }
    @GetMapping("/was/list3")
    public List<WasInstance> wasList3(){
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_11","staroot01","192.168.0.101","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.101:8080","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_12","staroot01","192.168.0.101","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.101:8081","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_21","staroot02","192.168.0.102","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.102:8080","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_22","staroot02","192.168.0.102","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.102:8081","100","2024-01-28","2024-01-28"));
        List<WasInstance> list = new ArrayList<>();
        for(WasInstance wasInstance : wasInstanceRepository.findByDomain("rpt.staroot.com")){
            log.info(wasInstance.toString());
            list.add(wasInstance);
        }
        log.info(list.toString());
        return list;
    }
    @GetMapping("/was/list4")
    public List<WasInstance> wasList4(){
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_11","staroot01","192.168.0.101","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.101:8080","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_12","staroot01","192.168.0.101","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.101:8081","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_21","staroot02","192.168.0.102","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.102:8080","100","2024-01-28","2024-01-28"));
        wasInstanceRepository.save(new WasInstance("rpt_tomcat_22","staroot02","192.168.0.102","Centos7.9","Apache Tomcat 9.0.83","jdk1.8.183","rpt.staroot.com","http://192.168.0.102:8081","100","2024-01-28","2024-01-28"));
        List<WasInstance> list = new ArrayList<>();
        for(WasInstance wasInstance : wasInstanceRepository.findByIp("192.168.0.102")){
            log.info(wasInstance.toString());
            list.add(wasInstance);
        }
        log.info(list.toString());
        return list;
    }

    @PostMapping("/was/send")
    public void sendWasInstance(@RequestBody WasInstance wasInstance){
        log.info(wasInstance.toString());
        wasInstanceRepository.save(wasInstance);
    }

}
