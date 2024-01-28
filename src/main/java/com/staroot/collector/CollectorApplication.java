package com.staroot.collector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class CollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectorApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(CollectorApplication.class);
	
    @GetMapping("/hello")
    public List<Map> hello(){
		log.info("hello");
		Map<String,String> map = new HashMap();
		List<Map> list = new ArrayList<>();
		map.put("user", "user1");
		map.put("email", "user1@gmail.com");
		list.add(map);
		return list;
    }
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


	@PostMapping("/was/send")
	public void sendWasInstance(@RequestBody WasInstance wasInstance){
		log.info(wasInstance.toString());
        wasInstanceRepository.save(wasInstance);
	}


}
