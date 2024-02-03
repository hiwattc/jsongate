package com.staroot.collector.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public interface CustomRepository {

    List<Object[]> runCustomSelect(@Param("query") String query);
}
