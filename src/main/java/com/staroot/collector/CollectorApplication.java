package com.staroot.collector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.staroot.collector.dao.ServerInfoRepository;
import com.staroot.collector.dao.WasInstanceRepository;
import com.staroot.collector.domain.ServerInfo;
import com.staroot.collector.domain.WasInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
//@EnableScheduling // 스케줄링 활성화
@EnableAsync
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
}
