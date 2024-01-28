package com.staroot.collector;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
public interface WasInstanceRepository extends CrudRepository<WasInstance,String> {
    List<ServerInfo> findByHostname(String hostname);
}


