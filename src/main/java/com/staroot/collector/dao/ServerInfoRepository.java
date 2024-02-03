package com.staroot.collector.dao;

import java.util.List;

import com.staroot.collector.domain.ServerInfo;
import org.springframework.data.repository.CrudRepository;
public interface ServerInfoRepository extends CrudRepository<ServerInfo, Long> {
    List<ServerInfo> findByHostname(String hostname);
}


