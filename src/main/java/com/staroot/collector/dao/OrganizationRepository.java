package com.staroot.collector.dao;

import com.staroot.collector.domain.Organization;
import com.staroot.collector.domain.WasInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface OrganizationRepository extends JpaRepository<Organization,Long> {

}


