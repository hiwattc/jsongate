package com.staroot.collector;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
public interface ServerInfoRepository extends CrudRepository<ServerInfo, Long> {
    List<ServerInfo> findByHostname(String hostname);
}


