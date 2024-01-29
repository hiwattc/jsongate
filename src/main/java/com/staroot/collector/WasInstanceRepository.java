package com.staroot.collector;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
public interface WasInstanceRepository extends CrudRepository<WasInstance,String> {
    List<WasInstance> findByHostname(String hostname);
    List<WasInstance> findByDomain(String domain);
    List<WasInstance> findByIp(String ip);
}


