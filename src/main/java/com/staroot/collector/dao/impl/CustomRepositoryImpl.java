package com.staroot.collector.dao.impl;

import com.staroot.collector.dao.CustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("customRepositoryImpl")
public class CustomRepositoryImpl implements CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> runCustomSelect(String query) {
        return entityManager.createNativeQuery(query).getResultList();
        //.setParameter("minAge", minAge)
    }
}
