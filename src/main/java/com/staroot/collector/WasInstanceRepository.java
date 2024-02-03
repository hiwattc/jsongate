package com.staroot.collector;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WasInstanceRepository extends CrudRepository<WasInstance,String> {
    List<WasInstance> findByHostname(String hostname);
    List<WasInstance> findByDomain(String domain);
    List<WasInstance> findByIp(String ip);

}


