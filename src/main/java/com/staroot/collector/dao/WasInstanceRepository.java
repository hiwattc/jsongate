package com.staroot.collector.dao;

import java.util.List;

import com.staroot.collector.domain.WasInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
//public interface WasInstanceRepository extends CrudRepository<WasInstance,String> {

public interface WasInstanceRepository extends JpaRepository<WasInstance,String> {
    List<WasInstance> findByHostname(String hostname);
    List<WasInstance> findByDomain(String domain);
    List<WasInstance> findByIp(String ip);

}


