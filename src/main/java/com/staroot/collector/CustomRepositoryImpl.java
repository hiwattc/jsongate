package com.staroot.collector;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
